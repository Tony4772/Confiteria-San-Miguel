package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Product
import com.example.ui.components.SanMiguelImage
import com.example.ui.theme.ChocolatePrimaryContainer
import com.example.ui.theme.CreamBackground
import com.example.ui.theme.CreamSurface
import com.example.ui.theme.CreamSurfaceContainer
import com.example.ui.theme.CreamSurfaceContainerHigh
import com.example.ui.theme.CreamSurfaceContainerLow
import com.example.ui.theme.CreamSurfaceContainerLowest
import com.example.ui.theme.ErrorRed
import com.example.ui.theme.GoldSecondary
import com.example.ui.theme.GoldSecondaryContainer
import com.example.ui.theme.OnGoldSecondaryContainer
import com.example.ui.theme.OnSurfaceDark
import com.example.ui.theme.OnSurfaceVariant
import com.example.ui.theme.OutlineGrey
import com.example.ui.theme.SecondaryFixed
import com.example.viewmodel.ShopUiState

@Composable
fun CatalogScreen(
  uiState: ShopUiState,
  onProductClick: (Product) -> Unit,
  onAddToCart: (Product) -> Unit,
  onToggleFavorite: (String) -> Unit,
  onFilterSelect: (String) -> Unit,
  onWhatsAppClick: (String) -> Unit
) {
  val scrollState = rememberScrollState()
  val filterScrollState = rememberScrollState()
  var searchQuery by remember { mutableStateOf("") }
  val focusManager = LocalFocusManager.current

  val categoryOptions = listOf(
    "Todos" to "Todas las colecciones",
    "puras" to "Barras Puras",
    "bombones" to "Bombones & Trufas",
    "frutas" to "Cacao & Frutas",
    "especialidades" to "Colecciones Especiales"
  )

  val filteredProducts = uiState.catalog.filter { product ->
    val matchesCategory = when (uiState.activeFilter) {
      "Todos" -> true
      "puras", "Barras Puras" -> product.category == "puras" || product.categoryLabel == "Barras Puras"
      "bombones", "Bombones & Trufas" -> product.category == "bombones" || product.categoryLabel == "Bombones & Trufas"
      "frutas", "Cacao & Frutas" -> product.category == "frutas" || product.categoryLabel == "Cacao & Frutas"
      "especialidades", "Colecciones Especiales" -> product.category == "especialidades" || product.categoryLabel == "Colecciones Especiales"
      else -> product.category.equals(uiState.activeFilter, ignoreCase = true) || product.categoryLabel.equals(uiState.activeFilter, ignoreCase = true)
    }

    val q = searchQuery.trim().lowercase()
    val matchesQuery = q.isEmpty() ||
      product.name.lowercase().contains(q) ||
      product.subtitle.lowercase().contains(q) ||
      product.shortDesc.lowercase().contains(q) ||
      product.origin.lowercase().contains(q) ||
      product.ingredients.lowercase().contains(q)

    matchesCategory && matchesQuery
  }

  BoxWithConstraints(
    modifier = Modifier
      .fillMaxSize()
      .background(CreamBackground)
  ) {
    val isTablet = maxWidth >= 600.dp
    val isDesktopOrUltraWide = maxWidth >= 900.dp
    val productColumns = if (isDesktopOrUltraWide) 4 else if (isTablet) 3 else 2
    val contentMaxWidth = if (isDesktopOrUltraWide) 920.dp else if (isTablet) 720.dp else maxWidth

    Column(
      modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(scrollState)
        .padding(bottom = 120.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = contentMaxWidth)
          .padding(horizontal = 16.dp, vertical = 12.dp)
      ) {
        // Tag Header
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.Center
        ) {
          Row(
            modifier = Modifier
              .clip(CircleShape)
              .background(CreamSurfaceContainerHigh)
              .padding(horizontal = 12.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Box(
              modifier = Modifier
                .size(6.dp)
                .clip(CircleShape)
                .background(GoldSecondary)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = uiState.storefront.catalogOverline.uppercase(),
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.2.sp
              ),
              color = OnSurfaceVariant
            )
          }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Title
        Text(
          text = uiState.storefront.catalogTitle,
          style = MaterialTheme.typography.headlineMedium.copy(
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp
          ),
          color = ChocolatePrimaryContainer,
          textAlign = TextAlign.Center,
          modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
          text = uiState.storefront.catalogNotice,
          style = MaterialTheme.typography.bodyMedium,
          color = OnSurfaceVariant,
          textAlign = TextAlign.Center,
          modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(18.dp))

        // Search Bar
        OutlinedTextField(
          value = searchQuery,
          onValueChange = { searchQuery = it },
          modifier = Modifier
            .fillMaxWidth()
            .testTag("catalog_search_field"),
          placeholder = { Text("Buscar por nombre, origen o notas...") },
          leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = null, tint = GoldSecondary)
          },
          trailingIcon = {
            if (searchQuery.isNotEmpty()) {
              IconButton(onClick = { searchQuery = "" }) {
                Icon(Icons.Default.Clear, contentDescription = "Limpiar")
              }
            }
          },
          singleLine = true,
          shape = RoundedCornerShape(14.dp),
          colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = CreamSurfaceContainerLowest,
            unfocusedContainerColor = CreamSurfaceContainerLowest,
            focusedBorderColor = GoldSecondary,
            unfocusedBorderColor = CreamSurfaceContainerHigh
          ),
          keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
          keyboardActions = KeyboardActions(onSearch = { focusManager.clearFocus() })
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Horizontal Category Filter Chips
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(filterScrollState),
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          categoryOptions.forEach { (catKey, catLabel) ->
            val isSelected = (uiState.activeFilter == catKey || uiState.activeFilter == catLabel || (catKey == "Todos" && uiState.activeFilter == "Todos"))
            Box(
              modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(if (isSelected) ChocolatePrimaryContainer else CreamSurfaceContainerLowest)
                .border(
                  width = 1.dp,
                  color = if (isSelected) ChocolatePrimaryContainer else CreamSurfaceContainerHigh,
                  shape = RoundedCornerShape(20.dp)
                )
                .clickable {
                  onFilterSelect(catKey)
                }
                .padding(horizontal = 14.dp, vertical = 8.dp)
            ) {
              Text(
                text = catLabel,
                style = MaterialTheme.typography.labelSmall.copy(
                  fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                  fontSize = 11.5.sp
                ),
                color = if (isSelected) CreamSurface else OnSurfaceDark
              )
            }
          }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Results count
        Text(
          text = "${filteredProducts.size} creación(es) disponible(s)",
          style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
          color = GoldSecondary,
          modifier = Modifier.padding(bottom = 12.dp)
        )

        // Products Grid
        if (filteredProducts.isEmpty()) {
          Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = CreamSurfaceContainerLowest)
          ) {
            Column(
              modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
              horizontalAlignment = Alignment.CenterHorizontally
            ) {
              Text(
                text = "No se encontraron chocolates",
                style = MaterialTheme.typography.titleMedium.copy(fontFamily = FontFamily.Serif),
                color = ChocolatePrimaryContainer
              )
              Spacer(modifier = Modifier.height(6.dp))
              Text(
                text = "Prueba con otra palabra o selecciona otra categoría.",
                style = MaterialTheme.typography.bodySmall,
                color = OnSurfaceVariant
              )
            }
          }
        } else {
          Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(14.dp)
          ) {
            val chunked = filteredProducts.chunked(productColumns)
            chunked.forEach { rowItems ->
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(14.dp)
              ) {
                rowItems.forEach { product ->
                  Box(modifier = Modifier.weight(1f)) {
                    CatalogProductCard(
                      product = product,
                      isFavorite = uiState.favorites.contains(product.id),
                      onCardClick = { onProductClick(product) },
                      onAddToCart = { onAddToCart(product) },
                      onToggleFavorite = { onToggleFavorite(product.id) }
                    )
                  }
                }
                repeat(productColumns - rowItems.size) {
                  Spacer(modifier = Modifier.weight(1f))
                }
              }
            }
          }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // WhatsApp Assistance Banner
        Card(
          modifier = Modifier.fillMaxWidth(),
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors(containerColor = SecondaryFixed.copy(alpha = 0.45f)),
          border = androidx.compose.foundation.BorderStroke(1.dp, GoldSecondaryContainer)
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            Text(
              text = uiState.storefront.orderBannerTitle,
              style = MaterialTheme.typography.titleMedium.copy(
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold
              ),
              color = ChocolatePrimaryContainer
            )
            Text(
              text = uiState.storefront.orderBannerDesc,
              style = MaterialTheme.typography.bodySmall,
              color = OnSurfaceDark
            )
            Spacer(modifier = Modifier.height(4.dp))
            Button(
              onClick = {
                val msg = "Hola Fernandina de Jagua, quisiera hacer una consulta sobre los chocolates del catálogo."
                onWhatsAppClick(msg)
              },
              colors = ButtonDefaults.buttonColors(containerColor = ChocolatePrimaryContainer),
              shape = RoundedCornerShape(20.dp)
            ) {
              Icon(Icons.AutoMirrored.Filled.Chat, contentDescription = null, tint = CreamSurface, modifier = Modifier.size(16.dp))
              Spacer(modifier = Modifier.width(8.dp))
              Text("Escribir a WhatsApp", color = CreamSurface, fontWeight = FontWeight.Bold)
            }
          }
        }
      }
    }
  }
}

@Composable
private fun CatalogProductCard(
  product: Product,
  isFavorite: Boolean,
  onCardClick: () -> Unit,
  onAddToCart: () -> Unit,
  onToggleFavorite: () -> Unit
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(14.dp))
      .clickable(onClick = onCardClick)
      .testTag("catalog_card_${product.id}"),
    shape = RoundedCornerShape(14.dp),
    colors = CardDefaults.cardColors(containerColor = CreamSurfaceContainerLowest),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
  ) {
    Column(modifier = Modifier.padding(10.dp)) {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .aspectRatio(4f / 5f)
          .clip(RoundedCornerShape(10.dp))
          .background(CreamSurfaceContainer)
      ) {
        SanMiguelImage(
          model = product.imageUrl,
          contentDescription = product.name,
          contentScale = ContentScale.Crop,
          modifier = Modifier.fillMaxSize()
        )

        // Badge
        product.badge?.let { badgeText ->
          Box(
            modifier = Modifier
              .align(Alignment.TopStart)
              .padding(6.dp)
              .clip(CircleShape)
              .background(if (product.badgeColor == "secondary") GoldSecondaryContainer else ChocolatePrimaryContainer)
              .padding(horizontal = 8.dp, vertical = 3.dp)
          ) {
            Text(
              text = badgeText,
              style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold
              ),
              color = if (product.badgeColor == "secondary") OnGoldSecondaryContainer else CreamSurface
            )
          }
        }

        IconButton(
          onClick = onToggleFavorite,
          modifier = Modifier
            .align(Alignment.TopEnd)
            .padding(4.dp)
            .size(30.dp)
            .clip(CircleShape)
            .background(CreamSurfaceContainerLowest.copy(alpha = 0.85f))
        ) {
          Icon(
            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = "Favorito",
            tint = if (isFavorite) ErrorRed else OnSurfaceVariant,
            modifier = Modifier.size(16.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(8.dp))

      Text(
        text = product.origin.ifBlank { product.categoryLabel },
        style = MaterialTheme.typography.labelSmall.copy(
          fontWeight = FontWeight.Medium,
          fontSize = 10.sp
        ),
        color = GoldSecondary,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
      )

      Spacer(modifier = Modifier.height(3.dp))

      Text(
        text = product.name,
        style = MaterialTheme.typography.titleSmall.copy(
          fontFamily = FontFamily.Serif,
          fontWeight = FontWeight.Bold,
          fontSize = 13.5.sp
        ),
        color = ChocolatePrimaryContainer,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        lineHeight = 17.sp
      )

      Text(
        text = product.subtitle,
        style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
        color = OutlineGrey,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
      )

      Spacer(modifier = Modifier.height(8.dp))

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column {
          Text(
            text = "S/ ${String.format(java.util.Locale.US, "%.2f", product.price)}",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = ChocolatePrimaryContainer
          )
          Text(
            text = product.netWeight.ifBlank { "Artesanal" },
            style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp),
            color = OutlineGrey
          )
        }

        IconButton(
          onClick = onAddToCart,
          modifier = Modifier
            .size(34.dp)
            .clip(CircleShape)
            .background(ChocolatePrimaryContainer)
            .testTag("catalog_add_${product.id}")
        ) {
          Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Añadir al carrito",
            tint = CreamSurfaceContainerLowest,
            modifier = Modifier.size(18.dp)
          )
        }
      }
    }
  }
}
