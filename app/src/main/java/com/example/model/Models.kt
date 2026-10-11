package com.example.model

data class Product(
  val id: String,
  val name: String,
  val subtitle: String,
  val price: Double,
  val imageUrl: String,
  val rating: Double = 4.9,
  val reviewsCount: Int = 88,
  val badge: String? = null,
  val category: String = "Bombones",
  val origin: String = "Ecuador & Piamonte",
  val description: String = "",
  val sensoryProfile: SensoryProfile = SensoryProfile()
)

data class SensoryProfile(
  val cocoaIntensity: Int = 4, // out of 5
  val sweetnessLevel: Int = 2,  // out of 5
  val aromaNotes: List<String> = listOf("Tostado Intenso", "Vainilla Bourbon", "Toque Floral", "Sal Marina Crunch")
)

data class BonbonFlavor(
  val id: String,
  val name: String,
  val description: String,
  val notes: String,
  val category: String, // "dark", "milk", "fruit", "liqueur"
  val imageUrl: String,
  val bonbonImageUrl: String,
  val tag: String? = null,
  val alcoholTag: String? = null
)

data class BoxTier(
  val size: Int,
  val name: String,
  val subtitle: String,
  val price: Double,
  val isFavorite: Boolean = false
)

data class CartItem(
  val id: String,
  val title: String,
  val subtitle: String,
  val unitPrice: Double,
  val quantity: Int,
  val imageUrl: String,
  val customFlavors: List<Pair<String, Int>> = emptyList() // flavor name to count
) {
  val totalPrice: Double get() = unitPrice * quantity
}

enum class DeliveryMode {
  REFRIGERATED,
  PICKUP_SERRANO
}

data class OrderSummary(
  val orderId: String,
  val date: String,
  val total: Double,
  val itemsCount: Int,
  val status: String
)
