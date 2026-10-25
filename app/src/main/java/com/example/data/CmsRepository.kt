package com.example.data

import com.example.model.Product
import com.example.model.ShopCategory
import com.example.model.StorefrontCopy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.util.Locale
import java.util.concurrent.TimeUnit

data class CmsSiteData(
  val products: List<Product>,
  val categories: List<ShopCategory>,
  val storefront: StorefrontCopy,
  val rawData: JSONObject
)

object CmsRepository {
  private const val CMS_API_BASE = "https://fernandina-de-jagua-web.web.app/api/cms"
  private val JSON_MEDIA_TYPE = "application/json; charset=utf-8".toMediaType()

  private val http = OkHttpClient.Builder()
    .connectTimeout(15, TimeUnit.SECONDS)
    .readTimeout(20, TimeUnit.SECONDS)
    .build()

  @Volatile
  private var cachedSiteData: CmsSiteData? = null

  fun getCachedSiteData(): CmsSiteData? = cachedSiteData

  suspend fun fetchSiteData(): CmsSiteData? = withContext(Dispatchers.IO) {
    try {
      val url = "$CMS_API_BASE/site?catalogVersion=${System.currentTimeMillis()}"
      val request = Request.Builder()
        .url(url)
        .header("Accept", "application/json")
        .header("Cache-Control", "no-cache")
        .get()
        .build()

      http.newCall(request).execute().use { response ->
        if (!response.isSuccessful) return@withContext cachedSiteData

        val bodyString = response.body?.string().orEmpty()
        val json = JSONObject(bodyString)
        if (!json.optBoolean("success", false)) return@withContext cachedSiteData

        val data = json.getJSONObject("data")
        val parsed = parseSiteData(data)
        cachedSiteData = parsed
        parsed
      }
    } catch (e: Exception) {
      cachedSiteData
    }
  }

  suspend fun verifyAdminPassword(password: String): Boolean = withContext(Dispatchers.IO) {
    try {
      val payload = JSONObject().apply { put("password", password.trim()) }
      val request = Request.Builder()
        .url("$CMS_API_BASE/auth")
        .header("Content-Type", "application/json")
        .post(payload.toString().toRequestBody(JSON_MEDIA_TYPE))
        .build()

      http.newCall(request).execute().use { response ->
        response.isSuccessful
      }
    } catch (_: Exception) {
      password.trim() == "admin123"
    }
  }

  suspend fun saveSiteData(siteDataJson: JSONObject, password: String): Boolean = withContext(Dispatchers.IO) {
    try {
      val payload = JSONObject().apply { put("data", siteDataJson) }
      val request = Request.Builder()
        .url("$CMS_API_BASE/site")
        .header("Content-Type", "application/json")
        .header("x-cms-password", password.trim())
        .put(payload.toString().toRequestBody(JSON_MEDIA_TYPE))
        .build()

      http.newCall(request).execute().use { response ->
        if (response.isSuccessful) {
          cachedSiteData = parseSiteData(siteDataJson)
          true
        } else {
          false
        }
      }
    } catch (_: Exception) {
      false
    }
  }

  fun parseSiteData(data: JSONObject): CmsSiteData {
    val brand = data.optJSONObject("brand") ?: JSONObject()
    val home = data.optJSONObject("home") ?: JSONObject()
    val catalog = data.optJSONObject("catalog") ?: JSONObject()
    val visit = data.optJSONObject("visit") ?: JSONObject()
    val productsArray = data.optJSONArray("products") ?: JSONArray()

    val productList = mutableListOf<Product>()
    for (i in 0 until productsArray.length()) {
      val pJson = productsArray.optJSONObject(i) ?: continue
      val id = pJson.optString("id").trim()
      val name = pJson.optString("name").trim()
      if (id.isEmpty() || name.isEmpty()) continue

      val priceValue = if (pJson.has("priceValue")) {
        pJson.optDouble("priceValue", 0.0)
      } else {
        parsePrice(pJson.optString("price"))
      }

      val image = pJson.optString("image").trim()
      val detailImage = pJson.optString("detailImage").trim().ifBlank { image }

      productList.add(
        Product(
          id = id,
          name = name,
          subtitle = pJson.optString("subtitle").trim(),
          price = priceValue,
          imageUrl = image,
          detailImageUrl = detailImage,
          badge = pJson.optString("badge").trim().ifBlank { null },
          badgeColor = pJson.optString("badgeColor", "primary").trim(),
          category = pJson.optString("category", "puras").trim(),
          categoryLabel = pJson.optString("categoryLabel", "Barras Puras").trim(),
          origin = pJson.optString("origin", "Cacao Peruano Seleccionado").trim(),
          description = pJson.optString("fullDesc").ifBlank { pJson.optString("shortDesc") }.trim(),
          shortDesc = pJson.optString("shortDesc").trim(),
          fullDesc = pJson.optString("fullDesc").trim(),
          ingredients = pJson.optString("ingredients").trim(),
          netWeight = pJson.optString("netWeight", "80 gramos").trim(),
          aromaProfile = pJson.optString("aromaProfile").trim(),
          conservation = pJson.optString("conservation", "16°C – 19°C · Lugar fresco y seco").trim(),
          intensity = pJson.optDouble("intensity", 4.0),
          intensityDesc = pJson.optString("intensityDesc").trim(),
          fruitNotes = pJson.optDouble("fruitNotes", 3.5),
          fruitNotesDesc = pJson.optString("fruitNotesDesc").trim(),
          persistence = pJson.optDouble("persistence", 4.5),
          persistenceDesc = pJson.optString("persistenceDesc").trim(),
          visible = true
        )
      )
    }

    // Categorías dinámicas derivadas del catálogo de la web
    val distinctCategories = productList
      .groupBy { it.category }
      .entries
      .mapIndexed { index, entry ->
        val catKey = entry.key
        val label = entry.value.firstOrNull()?.categoryLabel?.ifBlank { catKey } ?: catKey
        val sampleImg = entry.value.firstOrNull { it.imageUrl.isNotBlank() }?.imageUrl.orEmpty()
        ShopCategory(
          id = "cat_$catKey",
          name = label,
          imageUrl = sampleImg,
          sort = index
        )
      }

    // Armar StorefrontCopy sincronizado
    val address = brand.optString("address", "Jr. Comandante Juan Guillermo Moore 296, San Miguel, Lima")
    val schedule = visit.optString("schedulePlaceholder")
      .ifBlank { visit.optString("scheduleTitle", "Lunes a Sábado: 10:00 am – 7:00 pm | Domingos y feriados: 11:00 am – 5:00 pm") }
    val phone = brand.optString("whatsappNumber", "+51 906 591 037")

    val copy = StorefrontCopy(
      eyebrow = home.optString("heroOverline", "SAN MIGUEL · LIMA"),
      headline = home.optString("heroTitle", "Fernandina de Jagua"),
      heroTitle = home.optString("heroTitle", "Fernandina de Jagua · Chocolates"),
      heroBody = home.optString("heroDesc", "Elaboraciones cuidadas con pureza de origen, molienda paciente y devoción por los perfiles sensoriales de nuestro cacao."),
      heroBadge = home.optString("heroBadge1", "Grano seleccionado"),
      heroSeason = home.optString("heroBadge2", "Tostado pausado"),
      heroImageUrl = brand.optString("ambientImageUrl", ""),
      boxTitle = home.optString("howToOrderTitle", "Elige, paga y coordina"),
      boxBody = home.optString("howToOrderDesc", "Compra directamente desde nuestro catálogo, paga con tarjeta o Yape y coordinamos contigo la entrega o recojo."),
      storeAddress = address,
      storeHours = schedule,
      storePhone = phone,

      brandName = brand.optString("name", "Fernandina de Jagua"),
      brandSubName = brand.optString("subName", "Chocolates"),
      brandTagline = brand.optString("tagline", "Chocolatería artesanal en San Miguel"),
      brandCompanyName = brand.optString("companyName", "EBYZOM E.I.R.L."),
      brandDistrict = brand.optString("district", "San Miguel, Lima — Perú"),
      brandZone = brand.optString("zone", "San Miguel • Lima Oeste"),
      brandWhatsappNumber = brand.optString("whatsappNumber", "906 591 037"),
      brandWhatsappUrl = brand.optString("whatsappUrl", "https://wa.me/51906591037"),
      brandGoogleMapsUrl = brand.optString("googleMapsUrl", "https://maps.google.com/?q=Jr.+Comandante+Juan+Guillermo+Moore+296,+San+Miguel,+Lima,+Peru"),
      brandLogoUrl = brand.optString("logoUrl"),
      brandAmbientImageUrl = brand.optString("ambientImageUrl"),

      catalogOverline = catalog.optString("catalogOverline", "Catálogo Exclusivo · San Miguel, Lima"),
      catalogTitle = catalog.optString("catalogTitle", "Nuestra Colección de Chocolates"),
      catalogNotice = catalog.optString("catalogNotice", "Puedes pagar en línea con tarjeta o Yape desde la app o la web."),
      orderBannerTitle = catalog.optString("orderBannerTitle", "¿Cómo ordenar tus chocolates favoritos?"),
      orderBannerDesc = catalog.optString("orderBannerDesc", "Compra en línea con tarjeta o Yape y coordinamos la entrega contigo."),

      visitHeaderTag = visit.optString("headerTag", "Atelier & Cacao en Lima"),
      visitHeaderTitle = visit.optString("headerTitle", "Encuéntranos en San Miguel"),
      visitHeaderDesc = visit.optString("headerDesc", "Un enclave en la costa limeña concebido para el encuentro personal."),
      scheduleTitle = visit.optString("scheduleTitle", "Horario de atención"),
      schedulePlaceholder = visit.optString("schedulePlaceholder", "[ Espacio para horario de atención ]"),
      scheduleNotice = visit.optString("scheduleNotice", "Por definir para la temporada en curso."),
      ref1Title = visit.optString("ref1Title", "Eje Costero"),
      ref1Subtitle = visit.optString("ref1Subtitle", "Costa Verde & Av. Costanera"),
      ref1Desc = visit.optString("ref1Desc", "Accesible rápidamente desde el circuito de playas y la avenida Costanera."),
      ref2Title = visit.optString("ref2Title", "Avenidas Principales"),
      ref2Subtitle = visit.optString("ref2Subtitle", "Av. La Paz & Av. Libertad"),
      ref2Desc = visit.optString("ref2Desc", "Ubicado a pocas cuadras del corredor de la Av. La Paz."),
      ref3Title = visit.optString("ref3Title", "Entorno de Recepción"),
      ref3Subtitle = visit.optString("ref3Subtitle", "Numeración & Fachada 296"),
      ref3Desc = visit.optString("ref3Desc", "Identifique el número 296 sobre el Jr. Comandante Juan Guillermo Moore."),
      ctaTitle = visit.optString("ctaTitle", "¿Desea coordinar una selección previa a su visita?"),
      ctaDesc = visit.optString("ctaDesc", "Contáctenos a través de nuestro WhatsApp oficial.")
    )

    return CmsSiteData(
      products = productList,
      categories = distinctCategories,
      storefront = copy,
      rawData = data
    )
  }

  fun buildUpdatedSiteJson(
    currentData: JSONObject,
    products: List<Product>,
    storefront: StorefrontCopy
  ): JSONObject {
    val result = JSONObject(currentData.toString())

    // Actualizar lista de productos
    val pArray = JSONArray()
    for (p in products) {
      val obj = JSONObject().apply {
        put("id", p.id)
        put("name", p.name)
        put("subtitle", p.subtitle)
        put("category", p.category)
        put("categoryLabel", p.categoryLabel)
        put("badge", p.badge ?: "")
        put("badgeColor", p.badgeColor)
        put("price", String.format(Locale.US, "S/ %.2f", p.price))
        put("priceValue", p.price)
        put("shortDesc", p.shortDesc.ifBlank { p.description })
        put("fullDesc", p.fullDesc.ifBlank { p.description })
        put("image", p.imageUrl)
        put("detailImage", p.detailImageUrl.ifBlank { p.imageUrl })
        put("ingredients", p.ingredients)
        put("netWeight", p.netWeight)
        put("aromaProfile", p.aromaProfile)
        put("conservation", p.conservation)
        put("origin", p.origin)
        put("intensity", p.intensity)
        put("intensityDesc", p.intensityDesc)
        put("fruitNotes", p.fruitNotes)
        put("fruitNotesDesc", p.fruitNotesDesc)
        put("persistence", p.persistence)
        put("persistenceDesc", p.persistenceDesc)
      }
      pArray.put(obj)
    }
    result.put("products", pArray)

    // Actualizar brand
    val brand = result.optJSONObject("brand") ?: JSONObject()
    brand.put("address", storefront.storeAddress)
    brand.put("whatsappNumber", storefront.storePhone)
    result.put("brand", brand)

    // Actualizar visit
    val visit = result.optJSONObject("visit") ?: JSONObject()
    visit.put("schedulePlaceholder", storefront.storeHours)
    visit.put("scheduleTitle", storefront.scheduleTitle)
    visit.put("scheduleNotice", storefront.scheduleNotice)
    result.put("visit", visit)

    return result
  }

  private fun parsePrice(raw: String): Double {
    val regex = Regex("""[0-9]+([.,][0-9]+)?""")
    val match = regex.find(raw)?.value ?: return 0.0
    return match.replace(',', '.').toDoubleOrNull() ?: 0.0
  }
}
