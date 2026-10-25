package com.example.data

import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.functions.FirebaseFunctionsException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit

object CulqiPayments {
  private const val PUBLIC_KEY = "pk_live_U6Rxa1sMaLcZKpcA"
  private const val PAYMENTS_API_BASE = "https://fernandina-de-jagua-web.web.app/api/payments"
  private const val REGION = "us-central1"

  private val http = OkHttpClient.Builder()
    .callTimeout(40, TimeUnit.SECONDS)
    .build()

  private val jsonType = "application/json; charset=utf-8".toMediaType()

  data class CustomerData(
    val name: String,
    val email: String,
    val phone: String,
    val address: String = "",
    val district: String = "San Miguel",
    val notes: String = ""
  )

  data class OrderItemData(
    val id: String,
    val quantity: Int
  )

  data class OrderResult(
    val orderId: String,
    val orderNumber: String,
    val chargeId: String
  )

  /**
   * Procesa el cobro completo (crea orden en backend y ejecuta cargo con token Culqi).
   */
  suspend fun processFullPayment(
    items: List<OrderItemData>,
    customer: CustomerData,
    cardNumber: String? = null,
    cardMonth: String? = null,
    cardYear: String? = null,
    cardCvv: String? = null,
    yapePhone: String? = null,
    yapeOtp: String? = null,
    amountCents: Int
  ): OrderResult = withContext(Dispatchers.IO) {
    // 1. Obtener token de Culqi (Tarjeta o Yape)
    val token = if (yapePhone != null && yapeOtp != null) {
      postToken(
        url = "https://secure.culqi.com/v2/tokens/yape",
        payload = JSONObject()
          .put("number_phone", yapePhone)
          .put("otp", yapeOtp)
          .put("amount", amountCents.toString())
      )
    } else if (cardNumber != null && cardMonth != null && cardYear != null && cardCvv != null) {
      postToken(
        url = "https://secure.culqi.com/v2/tokens",
        payload = JSONObject()
          .put("card_number", cardNumber)
          .put("cvv", cardCvv)
          .put("expiration_month", cardMonth)
          .put("expiration_year", cardYear)
          .put("email", customer.email)
      )
    } else {
      error("Datos de pago incompletos")
    }

    // 2. Intentar cobrar a través de la API oficial de Fernandina Web
    try {
      val itemsArray = JSONArray()
      items.forEach { item ->
        itemsArray.put(JSONObject().put("id", item.id).put("quantity", item.quantity))
      }

      val customerJson = JSONObject()
        .put("name", customer.name.ifBlank { "Cliente Fernandina" })
        .put("email", customer.email.trim())
        .put("phone", customer.phone.trim().ifBlank { "906591037" })
        .put("address", customer.address)
        .put("district", customer.district)
        .put("notes", customer.notes)

      val createOrderPayload = JSONObject()
        .put("items", itemsArray)
        .put("customer", customerJson)

      val createRequest = Request.Builder()
        .url("$PAYMENTS_API_BASE/orders")
        .post(createOrderPayload.toString().toRequestBody(jsonType))
        .build()

      val (createSuccess, createBody) = http.newCall(createRequest).execute().use { res ->
        Pair(res.isSuccessful, res.body?.string().orEmpty())
      }

      if (createSuccess) {
        val createdJson = JSONObject(createBody.ifBlank { "{}" })
        val orderId = createdJson.optString("orderId")
        val orderNumber = createdJson.optString("orderNumber")

        if (orderId.isNotBlank()) {
          val payPayload = JSONObject().put("token", token)
          val payRequest = Request.Builder()
            .url("$PAYMENTS_API_BASE/orders/$orderId/pay")
            .post(payPayload.toString().toRequestBody(jsonType))
            .build()

          val (paySuccess, payBody) = http.newCall(payRequest).execute().use { res ->
            Pair(res.isSuccessful, res.body?.string().orEmpty())
          }

          val payJson = JSONObject(payBody.ifBlank { "{}" })
          if (paySuccess && payJson.optString("status") == "paid") {
            val chargeId = payJson.optString("chargeId").ifBlank { "chr_web_$orderId" }
            return@withContext OrderResult(orderId, orderNumber, chargeId)
          } else {
            val message = payJson.optString("message").ifBlank { "Culqi no aprobó el pago." }
            error(message)
          }
        }
      }
    } catch (e: Exception) {
      if (e.message != null && e.message?.contains("Culqi no aprobó") == true) {
        throw e
      }
    }

    // 3. Fallback: Firebase Cloud Function callable (si existe sesión activa)
    val chargeId = charge(token, customer.email, amountCents)
    OrderResult("order_${System.currentTimeMillis()}", "FJ-${System.currentTimeMillis() % 1000000}", chargeId)
  }

  suspend fun chargeCard(
    number: String,
    month: String,
    year: String,
    cvv: String,
    email: String,
    amountCents: Int
  ): String {
    val token = postToken(
      url = "https://secure.culqi.com/v2/tokens",
      payload = JSONObject()
        .put("card_number", number)
        .put("cvv", cvv)
        .put("expiration_month", month)
        .put("expiration_year", year)
        .put("email", email)
    )
    return charge(token, email, amountCents)
  }

  suspend fun chargeYape(
    phone: String,
    otp: String,
    email: String,
    amountCents: Int
  ): String {
    val token = postToken(
      url = "https://secure.culqi.com/v2/tokens/yape",
      payload = JSONObject()
        .put("number_phone", phone)
        .put("otp", otp)
        .put("amount", amountCents.toString())
    )
    return charge(token, email, amountCents)
  }

  private suspend fun postToken(url: String, payload: JSONObject): String = withContext(Dispatchers.IO) {
    val request = Request.Builder()
      .url(url)
      .addHeader("Authorization", "Bearer $PUBLIC_KEY")
      .addHeader("Content-Type", "application/json")
      .post(payload.toString().toRequestBody(jsonType))
      .build()
    http.newCall(request).execute().use { response ->
      val body = response.body?.string().orEmpty()
      val json = JSONObject(body.ifBlank { "{}" })
      val id = json.optString("id")
      if (!response.isSuccessful || id.isBlank()) {
        val message = json.optString("user_message").ifBlank {
          json.optString("merchant_message")
        }.ifBlank { "No se pudo preparar el medio de pago." }
        error(message)
      }
      id
    }
  }

  private suspend fun charge(sourceId: String, email: String, amountCents: Int): String {
    return try {
      val result = FirebaseFunctions.getInstance(REGION)
        .getHttpsCallable("createCulqiCharge")
        .call(
          mapOf(
            "sourceId" to sourceId,
            "email" to email,
            "amount" to amountCents,
            "description" to "Pedido Fernandina de Jagua"
          )
        )
        .await()
      val data = result.getData() as? Map<*, *>
      val chargeId = data?.get("chargeId") as? String
      val charged = (data?.get("amount") as? Number)?.toInt()
      if (chargeId.isNullOrBlank() || charged != amountCents) {
        error("El cobro no coincide con el total del pedido.")
      }
      chargeId
    } catch (error: FirebaseFunctionsException) {
      error(error.message ?: "Culqi no aprobó el pago.")
    }
  }
}
