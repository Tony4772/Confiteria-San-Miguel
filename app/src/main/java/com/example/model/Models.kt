package com.example.model

data class Product(
  val id: String,
  val name: String,
  val subtitle: String,
  val price: Double,
  val imageUrl: String,
  val detailImageUrl: String = "",
  val rating: Double = 4.9,
  val reviewsCount: Int = 88,
  val badge: String? = null,
  val badgeColor: String = "primary",
  val category: String = "puras",
  val categoryLabel: String = "Barras Puras",
  val origin: String = "Cacao Peruano Seleccionado · San Miguel, Lima",
  val description: String = "",
  val shortDesc: String = "",
  val fullDesc: String = "",
  val ingredients: String = "",
  val netWeight: String = "80 gramos",
  val aromaProfile: String = "",
  val conservation: String = "16°C – 19°C · Lugar fresco y seco",
  val intensity: Double = 4.0,
  val intensityDesc: String = "",
  val fruitNotes: Double = 3.5,
  val fruitNotesDesc: String = "",
  val persistence: Double = 4.5,
  val persistenceDesc: String = "",
  val sensoryProfile: SensoryProfile = SensoryProfile(),
  val visible: Boolean = true
)

data class SensoryProfile(
  val cocoaIntensity: Int = 4, // out of 5
  val sweetnessLevel: Int = 2,  // out of 5
  val aromaNotes: List<String> = listOf("Tostado Intenso", "Vainilla de selva", "Toque Floral", "Sal de Maras")
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
  PICKUP_SERRANO // Recojo en Taller (San Miguel)
}

enum class PaymentMethod {
  YAPE,
  CARD
}

data class CardCheckout(
  val number: String,
  val month: String,
  val year: String,
  val cvv: String
)

data class YapeCheckout(
  val phone: String,
  val otp: String
)

data class ShopCategory(
  val id: String,
  val name: String,
  val imageUrl: String = "",
  val sort: Int = 0
)

data class StorefrontCopy(
  val eyebrow: String = "SAN MIGUEL · LIMA",
  val headline: String = "Fernandina de Jagua",
  val cornerBadge: String = "Chocolatería Artesanal",
  val heroBadge: String = "Grano seleccionado",
  val heroSeason: String = "Tostado pausado",
  val heroTitle: String = "Fernandina de Jagua · Chocolates",
  val heroBody: String = "Elaboraciones cuidadas con pureza de origen, molienda paciente y devoción por los perfiles sensoriales de nuestro cacao.",
  val heroPriceCaption: String = "DESDE",
  val heroPrice: String = "S/ 16.00",
  val heroButton: String = "Explorar Colección",
  val heroImageUrl: String = "",
  val categoriesHeading: String = "Nuestras Colecciones",
  val productsHeading: String = "Nuestras Creaciones",
  val productsNote: String = "CACAO SELECCIONADO",
  val boxKicker: String = "COMPRA EN LÍNEA",
  val boxTitle: String = "Elige, paga y coordina",
  val boxBody: String = "Compra directamente desde nuestro catálogo, paga con tarjeta o Yape y coordinamos contigo la entrega o recojo en nuestro atelier.",
  val boxStep1: String = "Elige",
  val boxStep2: String = "Paga",
  val boxStep3: String = "Coordina",
  val boxButton: String = "Ver Catálogo",
  val quote: String = "",
  val quoteName: String = "",
  val quoteRole: String = "",
  val sealTitle: String = "Cacao 100% Peruano",
  val sealBody: String = "Grano seleccionado de Piura, Cusco, San Martín y Junín",
  val sealButton: String = "CONOCER MÁS",
  val footerLine: String = "FERNANDINA DE JAGUA CHOCOLATES · SAN MIGUEL, LIMA",
  val deliveryTitle: String = "Modalidad de Entrega",
  val deliveryDetail: String = "Envío a domicilio en Lima o recojo en taller en San Miguel",
  val giftTitle: String = "Empaquetado Fino",
  val giftDetail: String = "Estuche de presentación artesanal con opción de dedicatoria",
  val profileTagline: String = "Chocolatería artesanal en San Miguel",
  val trustCraft: String = "Cacao Peruano",
  val trustCold: String = "Tostado Pausado",
  val trustPay: String = "Pago Seguro Culqi",
  val tierPetitPrice: Double = 15.00,
  val tierClasicoPrice: Double = 28.00,
  val tierGranReservaPrice: Double = 52.00,
  val storeAddress: String = "Jr. Comandante Juan Guillermo Moore 296, San Miguel, Lima",
  val storeHours: String = "Lunes a Sábado: 10:00 am – 7:00 pm | Domingos y feriados: 11:00 am – 5:00 pm",
  val storePhone: String = "+51 906 591 037",

  // Sincronización en tiempo real con CMS de la web
  val brandName: String = "Fernandina de Jagua",
  val brandSubName: String = "Chocolates",
  val brandTagline: String = "Chocolatería artesanal en San Miguel",
  val brandCompanyName: String = "EBYZOM E.I.R.L.",
  val brandDistrict: String = "San Miguel, Lima — Perú",
  val brandZone: String = "San Miguel • Lima Oeste",
  val brandWhatsappNumber: String = "906 591 037",
  val brandWhatsappUrl: String = "https://wa.me/51906591037",
  val brandGoogleMapsUrl: String = "https://maps.google.com/?q=Jr.+Comandante+Juan+Guillermo+Moore+296,+San+Miguel,+Lima,+Peru",
  val brandLogoUrl: String = "",
  val brandAmbientImageUrl: String = "",

  val catalogOverline: String = "Catálogo Exclusivo · San Miguel, Lima",
  val catalogTitle: String = "Nuestra Colección de Chocolates",
  val catalogNotice: String = "Puedes pagar en línea con tarjeta o Yape desde esta misma web, o coordinar tu pedido por nuestra aplicación oficial de Android o por WhatsApp.",
  val orderBannerTitle: String = "¿Cómo ordenar tus chocolates favoritos?",
  val orderBannerDesc: String = "Compra en línea con tarjeta o Yape y coordinamos la entrega contigo, o atiéndete de forma personalizada por nuestro WhatsApp oficial al 906 591 037 y nuestra aplicación oficial de Android.",

  val visitHeaderTag: String = "Atelier & Cacao en Lima",
  val visitHeaderTitle: String = "Encuéntranos en San Miguel",
  val visitHeaderDesc: String = "Un enclave en la costa limeña concebido para el encuentro personal, el retiro de encargos finos y la exploración de notas aromáticas de nuestra chocolatería.",
  val scheduleTitle: String = "Horario de atención",
  val schedulePlaceholder: String = "Lunes a Sábado: 10:00 am – 7:00 pm | Domingos y feriados: 11:00 am – 5:00 pm",
  val scheduleNotice: String = "Horarios sujetos a cambio en temporadas especiales. Para visitas privadas y retiro de encargos finos, contáctenos previamente por WhatsApp.",
  val ref1Title: String = "Eje Costero",
  val ref1Subtitle: String = "Costa Verde & Av. Costanera",
  val ref1Desc: String = "Accesible rápidamente desde el circuito de playas y la avenida Costanera, ascendiendo por las vías transversales hacia el corazón tradicional de San Miguel.",
  val ref2Title: String = "Avenidas Principales",
  val ref2Subtitle: String = "Av. La Paz & Av. Libertad",
  val ref2Desc: String = "Ubicado a pocas cuadras del corredor de la Av. La Paz, en un entorno residencial sereno que permite estacionamiento rápido para retiros breves.",
  val ref3Title: String = "Entorno de Recepción",
  val ref3Subtitle: String = "Numeración & Fachada 296",
  val ref3Desc: String = "Identifique el número 296 sobre el Jr. Comandante Juan Guillermo Moore. Para retiros de lotes o colecciones reservadas, toque el timbre correspondiente.",
  val ctaTitle: String = "¿Desea coordinar una selección previa a su visita?",
  val ctaDesc: String = "Puede hacernos llegar sus preferencias o solicitar asesoría para regalos institucionales y personales con antelación a través de nuestro WhatsApp oficial."
)

data class OrderSummary(
  val orderId: String,
  val date: String,
  val total: Double,
  val itemsCount: Int,
  val status: String
)
