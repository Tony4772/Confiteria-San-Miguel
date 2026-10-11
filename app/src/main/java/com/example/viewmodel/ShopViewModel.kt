package com.example.viewmodel

import androidx.lifecycle.ViewModel
import com.example.data.SanMiguelData
import com.example.model.BoxTier
import com.example.model.CartItem
import com.example.model.DeliveryMode
import com.example.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

sealed interface CurrentScreen {
  data object Home : CurrentScreen
  data object BoxBuilder : CurrentScreen
  data object Cart : CurrentScreen
  data class ProductDetail(val product: Product) : CurrentScreen
  data object Profile : CurrentScreen
}

enum class PackOption(val id: String, val count: Int, val label: String, val price: Double) {
  SINGLE("single", 1, "Pieza individual", 2.40),
  BOX6("box6", 6, "Estuche Colección 6 uds", 13.50),
  BOX12("box12", 12, "Cofre Prestigio 12 uds", 26.00)
}

data class ShopUiState(
  val currentScreen: CurrentScreen = CurrentScreen.Home,
  val navigationHistory: List<CurrentScreen> = listOf(CurrentScreen.Home),
  val activeFilter: String = "Más vendidos",
  val searchQuery: String = "",
  val isSearchOpen: Boolean = false,
  val isSustainabilityOpen: Boolean = false,
  val isOrderSuccessOpen: Boolean = false,
  val orderPlacedId: String = "",
  val favorites: Set<String> = setOf("bombon_oro", "cofre_real"),

  // Box Builder state
  val selectedBoxTier: BoxTier = SanMiguelData.boxTiers[1], // 12 pieces
  val bonbonCounts: Map<String, Int> = mapOf(
    "pistachio" to 2,
    "ruby" to 1,
    "caramel" to 2,
    "moka" to 1,
    "chuao" to 1,
    "sherry" to 0
  ),
  val flavorFilterCategory: String = "all",

  // Cart state
  val cartItems: List<CartItem> = SanMiguelData.initialCart,
  val deliveryMode: DeliveryMode = DeliveryMode.REFRIGERATED,
  val selectedTimeSlot: String = "Mañana (10:00 - 14:00)",
  val giftWrapEnabled: Boolean = true,
  val giftCardMessage: String = "Para celebrar tus logros con la dulzura que mereces. Con todo mi cariño y admiración siempre.",

  // Product detail state
  val detailSelectedPack: PackOption = PackOption.BOX6,
  val detailQuantity: Int = 1,
  val detailGalleryIndex: Int = 0,
  val detailRibbonSelected: Boolean = true,
  val detailCardSelected: Boolean = false,
  val detailCardMessage: String = "",
  val detailIngredientsExpanded: Boolean = false,
  val detailShippingExpanded: Boolean = false,

  // Toast / feedback message
  val toastMessage: String? = null
) {
  val boxCurrentTotal: Int get() = bonbonCounts.values.sum()
  val boxMaxCapacity: Int get() = selectedBoxTier.size

  val cartSubtotal: Double
    get() = cartItems.sumOf { it.totalPrice }

  val giftWrapPrice: Double
    get() = if (giftWrapEnabled) 3.00 else 0.00

  val shippingPrice: Double
    get() = 0.0 // Always free climate-controlled shipping in this campaign

  val cartTotal: Double
    get() = cartSubtotal + giftWrapPrice + shippingPrice

  val freeShippingGoal: Double = 65.00
  val remainingForFreeShipping: Double
    get() = (freeShippingGoal - cartSubtotal).coerceAtLeast(0.0)

  val freeShippingProgress: Float
    get() = (cartSubtotal / freeShippingGoal).coerceIn(0.0, 1.0).toFloat()
}

class ShopViewModel : ViewModel() {
  private val _uiState = MutableStateFlow(ShopUiState())
  val uiState: StateFlow<ShopUiState> = _uiState.asStateFlow()

  fun navigateTo(screen: CurrentScreen) {
    _uiState.update { state ->
      val newHistory = if (state.currentScreen != screen) {
        state.navigationHistory + screen
      } else {
        state.navigationHistory
      }
      state.copy(
        currentScreen = screen,
        navigationHistory = newHistory
      )
    }
  }

  fun navigateBack(): Boolean {
    val state = _uiState.value
    if (state.navigationHistory.size > 1) {
      val newHistory = state.navigationHistory.dropLast(1)
      _uiState.update {
        it.copy(
          currentScreen = newHistory.last(),
          navigationHistory = newHistory
        )
      }
      return true
    }
    return false
  }

  fun setFilter(filter: String) {
    _uiState.update { it.copy(activeFilter = filter) }
  }

  fun setSearchQuery(query: String) {
    _uiState.update { it.copy(searchQuery = query) }
  }

  fun toggleSearch(isOpen: Boolean) {
    _uiState.update { it.copy(isSearchOpen = isOpen) }
  }

  fun toggleSustainability(isOpen: Boolean) {
    _uiState.update { it.copy(isSustainabilityOpen = isOpen) }
  }

  fun toggleFavorite(productId: String) {
    _uiState.update { state ->
      val newFavorites = if (state.favorites.contains(productId)) {
        state.favorites - productId
      } else {
        state.favorites + productId
      }
      val isAdded = newFavorites.contains(productId)
      state.copy(
        favorites = newFavorites,
        toastMessage = if (isAdded) "Añadido a tus favoritos" else "Eliminado de favoritos"
      )
    }
  }

  fun clearToast() {
    _uiState.update { it.copy(toastMessage = null) }
  }

  // --- Box Builder ---
  fun selectBoxTier(tier: BoxTier) {
    _uiState.update { it.copy(selectedBoxTier = tier) }
  }

  fun setFlavorCategory(category: String) {
    _uiState.update { it.copy(flavorFilterCategory = category) }
  }

  fun modifyBonbonCount(flavorId: String, delta: Int) {
    _uiState.update { state ->
      val currentCount = state.bonbonCounts[flavorId] ?: 0
      val currentTotal = state.bonbonCounts.values.sum()
      val max = state.selectedBoxTier.size

      if (delta > 0 && currentTotal >= max) {
        return@update state.copy(toastMessage = "¡Has completado la capacidad de este cofre!")
      }
      if (delta < 0 && currentCount <= 0) {
        return@update state
      }

      val newCounts = state.bonbonCounts.toMutableMap()
      newCounts[flavorId] = (currentCount + delta).coerceAtLeast(0)
      state.copy(bonbonCounts = newCounts)
    }
  }

  fun autoFillFavorites() {
    _uiState.update { state ->
      val max = state.selectedBoxTier.size
      val currentTotal = state.bonbonCounts.values.sum()
      val needed = max - currentTotal
      if (needed <= 0) {
        return@update state.copy(toastMessage = "La caja ya está completa.")
      }

      val newCounts = state.bonbonCounts.toMutableMap()
      val pistachiosToAdd = (needed + 1) / 2
      val caramelsToAdd = needed - pistachiosToAdd

      newCounts["pistachio"] = (newCounts["pistachio"] ?: 0) + pistachiosToAdd
      newCounts["caramel"] = (newCounts["caramel"] ?: 0) + caramelsToAdd

      state.copy(
        bonbonCounts = newCounts,
        toastMessage = "¡Cofre completado con nuestros bombones galardonados!"
      )
    }
  }

  fun confirmBoxToCart() {
    val state = _uiState.value
    val flavorsList = state.bonbonCounts.filter { it.value > 0 }.map { entry ->
      val name = SanMiguelData.bonbonFlavors.find { it.id == entry.key }?.name ?: entry.key
      Pair(name, entry.value)
    }

    val newItem = CartItem(
      id = "custom_box_${System.currentTimeMillis()}",
      title = "${state.selectedBoxTier.name} Personalizado",
      subtitle = "${state.selectedBoxTier.size} bombones de autor",
      unitPrice = state.selectedBoxTier.price,
      quantity = 1,
      imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDe0nxgRYMACpNF-JW2Q_CL1Ozlcn0_LwsG0lbLN9VLd_6u2CZt5EXg-y5Jkgp4LrgF8gMfCVtSqyjnCU1toyvZTjMehd_dGzg44MjpjrlNW41QpyoOyAWg7Ix0Iigxn-t8CE-QfUB7QRqd9vRi69VsRvGGHFNsJOqiXs0R_j5kTymd9HQX_M9fQrQvBQUDExfqTaPv1quJiiU5IWZtwIxWESTpy_iOb0_pXanzpVhq3cMZfuesqU6tPw",
      customFlavors = flavorsList
    )

    _uiState.update {
      it.copy(
        cartItems = it.cartItems + newItem,
        toastMessage = "¡${state.selectedBoxTier.name} añadido a la bolsa!",
        currentScreen = CurrentScreen.Cart
      )
    }
  }

  // --- Cart actions ---
  fun updateCartQuantity(itemId: String, delta: Int) {
    _uiState.update { state ->
      val updatedList = state.cartItems.mapNotNull { item ->
        if (item.id == itemId) {
          val newQty = item.quantity + delta
          if (newQty > 0) item.copy(quantity = newQty) else null
        } else item
      }
      state.copy(cartItems = updatedList)
    }
  }

  fun removeCartItem(itemId: String) {
    _uiState.update { state ->
      state.copy(
        cartItems = state.cartItems.filterNot { it.id == itemId },
        toastMessage = "Producto eliminado de la bolsa"
      )
    }
  }

  fun setDeliveryMode(mode: DeliveryMode) {
    _uiState.update { it.copy(deliveryMode = mode) }
  }

  fun setTimeSlot(slot: String) {
    _uiState.update { it.copy(selectedTimeSlot = slot) }
  }

  fun setGiftWrap(enabled: Boolean) {
    _uiState.update { it.copy(giftWrapEnabled = enabled) }
  }

  fun setGiftCardMessage(message: String) {
    _uiState.update { it.copy(giftCardMessage = message) }
  }

  fun addProductToCart(product: Product) {
    _uiState.update { state ->
      val existing = state.cartItems.find { it.title.startsWith(product.name.take(15)) }
      val newItems = if (existing != null) {
        state.cartItems.map {
          if (it.id == existing.id) it.copy(quantity = it.quantity + 1) else it
        }
      } else {
        state.cartItems + CartItem(
          id = "prod_${System.currentTimeMillis()}",
          title = product.name,
          subtitle = product.subtitle,
          unitPrice = product.price,
          quantity = 1,
          imageUrl = product.imageUrl
        )
      }
      state.copy(
        cartItems = newItems,
        toastMessage = "¡${product.name} añadido a tu bolsa!"
      )
    }
  }

  fun processOrder() {
    val orderId = "SM-${(1000..9999).random()}"
    _uiState.update {
      it.copy(
        isOrderSuccessOpen = true,
        orderPlacedId = orderId
      )
    }
  }

  fun dismissOrderSuccess() {
    _uiState.update {
      it.copy(
        isOrderSuccessOpen = false,
        cartItems = emptyList(),
        currentScreen = CurrentScreen.Home
      )
    }
  }

  // --- Product Detail ---
  fun openProductDetail(product: Product) {
    _uiState.update {
      it.copy(
        detailSelectedPack = PackOption.BOX6,
        detailQuantity = 1,
        detailGalleryIndex = 0,
        detailIngredientsExpanded = false,
        detailShippingExpanded = false
      )
    }
    navigateTo(CurrentScreen.ProductDetail(product))
  }

  fun setDetailPack(pack: PackOption) {
    _uiState.update { it.copy(detailSelectedPack = pack) }
  }

  fun changeDetailQuantity(delta: Int) {
    _uiState.update {
      val next = (it.detailQuantity + delta).coerceIn(1, 20)
      it.copy(detailQuantity = next)
    }
  }

  fun setDetailGalleryIndex(index: Int) {
    _uiState.update { it.copy(detailGalleryIndex = index) }
  }

  fun toggleDetailRibbon(selected: Boolean) {
    _uiState.update { it.copy(detailRibbonSelected = selected) }
  }

  fun toggleDetailCard(selected: Boolean) {
    _uiState.update { it.copy(detailCardSelected = selected) }
  }

  fun setDetailCardMessage(msg: String) {
    _uiState.update { it.copy(detailCardMessage = msg) }
  }

  fun toggleDetailIngredients() {
    _uiState.update { it.copy(detailIngredientsExpanded = !it.detailIngredientsExpanded) }
  }

  fun toggleDetailShipping() {
    _uiState.update { it.copy(detailShippingExpanded = !it.detailShippingExpanded) }
  }

  fun addDetailToCart(product: Product) {
    val state = _uiState.value
    val pack = state.detailSelectedPack
    val item = CartItem(
      id = "detail_${System.currentTimeMillis()}",
      title = "${product.name} · ${pack.count} uds",
      subtitle = pack.label,
      unitPrice = pack.price,
      quantity = state.detailQuantity,
      imageUrl = product.imageUrl
    )

    _uiState.update {
      it.copy(
        cartItems = it.cartItems + item,
        toastMessage = "¡Añadido con éxito al cofre!",
        currentScreen = CurrentScreen.Cart
      )
    }
  }
}
