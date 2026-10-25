package com.example.data

import android.net.Uri
import com.example.model.Product
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.util.UUID

object CatalogRepository {
  private const val DATABASE_ID = "tienda"

  private fun products() = try {
    FirebaseFirestore.getInstance(DATABASE_ID).collection("products")
  } catch (_: Exception) {
    FirebaseFirestore.getInstance().collection("products")
  }

  fun observe(includeHidden: Boolean): Flow<List<Product>> = callbackFlow {
    try {
      val query = if (includeHidden) {
        products()
      } else {
        products().whereEqualTo("visible", true)
      }
      val registration: ListenerRegistration = query.addSnapshotListener { snapshot, error ->
        if (error != null || snapshot == null) {
          trySend(SanMiguelData.products)
          return@addSnapshotListener
        }
        val items = snapshot.documents.mapNotNull { document -> document.toProduct() }
        trySend(if (items.isNotEmpty()) items else SanMiguelData.products)
      }
      awaitClose { registration.remove() }
    } catch (_: Exception) {
      trySend(SanMiguelData.products)
      awaitClose {}
    }
  }

  suspend fun save(product: Product) {
    products().document(product.id).set(product.toDocument()).await()
  }

  suspend fun delete(productId: String) {
    products().document(productId).delete().await()
  }

  suspend fun uploadImage(productId: String, uri: Uri): String {
    val fileName = "${UUID.randomUUID()}.jpg"
    val reference = FirebaseStorage.getInstance().reference.child("products/$productId/$fileName")
    reference.putFile(uri).await()
    return reference.downloadUrl.await().toString()
  }
}

private fun DocumentSnapshot.toProduct(): Product? {
  val name = getString("name") ?: return null
  val priceValue = get("price")
  val price = if (priceValue is Number) priceValue.toDouble() else 0.0
  val badge = getString("badge").orEmpty()
  val intensityVal = get("intensity")
  val fruitVal = get("fruitNotes")
  val persistVal = get("persistence")

  return Product(
    id = id,
    name = name,
    subtitle = getString("subtitle").orEmpty(),
    price = price,
    imageUrl = getString("imageUrl").orEmpty(),
    detailImageUrl = getString("detailImageUrl").orEmpty(),
    badge = badge.ifBlank { null },
    badgeColor = getString("badgeColor").orEmpty().ifBlank { "primary" },
    category = getString("category").orEmpty().ifBlank { "puras" },
    categoryLabel = getString("categoryLabel").orEmpty().ifBlank { "Barras Puras" },
    origin = getString("origin").orEmpty().ifBlank { "Cacao Peruano Seleccionado · San Miguel, Lima" },
    description = getString("description").orEmpty(),
    shortDesc = getString("shortDesc").orEmpty(),
    fullDesc = getString("fullDesc").orEmpty(),
    ingredients = getString("ingredients").orEmpty(),
    netWeight = getString("netWeight").orEmpty().ifBlank { "80 gramos" },
    aromaProfile = getString("aromaProfile").orEmpty(),
    conservation = getString("conservation").orEmpty().ifBlank { "16°C – 19°C · Lugar fresco y seco" },
    intensity = if (intensityVal is Number) intensityVal.toDouble() else 4.0,
    intensityDesc = getString("intensityDesc").orEmpty(),
    fruitNotes = if (fruitVal is Number) fruitVal.toDouble() else 3.5,
    fruitNotesDesc = getString("fruitNotesDesc").orEmpty(),
    persistence = if (persistVal is Number) persistVal.toDouble() else 4.5,
    persistenceDesc = getString("persistenceDesc").orEmpty(),
    visible = getBoolean("visible") ?: true
  )
}

private fun Product.toDocument(): Map<String, Any> {
  return mapOf(
    "name" to name,
    "subtitle" to subtitle,
    "price" to price,
    "imageUrl" to imageUrl,
    "detailImageUrl" to detailImageUrl,
    "description" to description,
    "shortDesc" to shortDesc,
    "fullDesc" to fullDesc,
    "ingredients" to ingredients,
    "netWeight" to netWeight,
    "aromaProfile" to aromaProfile,
    "conservation" to conservation,
    "intensity" to intensity,
    "intensityDesc" to intensityDesc,
    "fruitNotes" to fruitNotes,
    "fruitNotesDesc" to fruitNotesDesc,
    "persistence" to persistence,
    "persistenceDesc" to persistenceDesc,
    "category" to category,
    "categoryLabel" to categoryLabel,
    "origin" to origin,
    "badge" to badge.orEmpty(),
    "badgeColor" to badgeColor,
    "visible" to visible
  )
}
