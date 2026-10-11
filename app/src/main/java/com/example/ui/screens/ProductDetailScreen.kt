package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Loupe
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Redeem
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.SanMiguelData
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
import com.example.ui.theme.SecondaryFixedDim
import com.example.viewmodel.PackOption
import com.example.viewmodel.ShopUiState

@Composable
fun ProductDetailScreen(
  product: Product,
  uiState: ShopUiState,
  onGallerySelect: (Int) -> Unit,
  onPackSelect: (PackOption) -> Unit,
  onChangeQuantity: (Int) -> Unit,
  onToggleRibbon: (Boolean) -> Unit,
  onToggleCard: (Boolean) -> Unit,
  onCardMessageChange: (String) -> Unit,
  onToggleIngredients: () -> Unit,
  onToggleShipping: () -> Unit,
  onToggleFavorite: (String) -> Unit,
  onAddToCart: (Product) -> Unit
) {
  val scrollState = rememberScrollState()
  val isFavorite = uiState.favorites.contains(product.id)
  val currentImage = SanMiguelData.detailGallery.getOrElse(uiState.detailGalleryIndex) { product.imageUrl }
  val totalPackPrice = uiState.detailSelectedPack.price * uiState.detailQuantity

  BoxWithConstraints(
    modifier = Modifier
      .fillMaxSize()
      .background(CreamBackground)
  ) {
    val isTabletOrDesktop = maxWidth >= 640.dp

    if (isTabletOrDesktop) {
      // 2-Pane Side-by-Side Responsive Layout for Tablets/Desktop
      Row(
        modifier = Modifier
          .fillMaxSize()
          .widthIn(max = 960.dp)
          .align(Alignment.Center)
          .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
      ) {
        // Left Column: Media showcase (sticky/pinned)
        Box(
          modifier = Modifier
            .weight(0.46f)
            .align(Alignment.Top)
        ) {
          ProductMediaShowcase(
            product = product,
            currentImage = currentImage,
            galleryIndex = uiState.detailGalleryIndex,
            isFavorite = isFavorite,
            onGallerySelect = onGallerySelect,
            onToggleFavorite = { onToggleFavorite(product.id) }
          )
        }

        // Right Column: Form & specs (scrollable)
        Column(
          modifier = Modifier
            .weight(0.54f)
            .verticalScroll(scrollState)
            .padding(bottom = 60.dp)
        ) {
          ProductInfoForm(
            product = product,
            uiState = uiState,
            totalPackPrice = totalPackPrice,
            onPackSelect = onPackSelect,
            onChangeQuantity = onChangeQuantity,
            onToggleRibbon = onToggleRibbon,
            onToggleCard = onToggleCard,
            onCardMessageChange = onCardMessageChange,
            onToggleIngredients = onToggleIngredients,
            onToggleShipping = onToggleShipping,
            onAddToCart = { onAddToCart(product) }
          )
        }
      }
    } else {
      // Mobile Single-column Vertical Stack
      Column(
        modifier = Modifier
          .fillMaxSize()
          .verticalScroll(scrollState)
          .padding(bottom = 120.dp)
      ) {
        ProductMediaShowcase(
          product = product,
          currentImage = currentImage,
          galleryIndex = uiState.detailGalleryIndex,
          isFavorite = isFavorite,
          onGallerySelect = onGallerySelect,
          onToggleFavorite = { onToggleFavorite(product.id) }
        )

        ProductInfoForm(
          product = product,
          uiState = uiState,
          totalPackPrice = totalPackPrice,
          onPackSelect = onPackSelect,
          onChangeQuantity = onChangeQuantity,
          onToggleRibbon = onToggleRibbon,
          onToggleCard = onToggleCard,
          onCardMessageChange = onCardMessageChange,
          onToggleIngredients = onToggleIngredients,
          onToggleShipping = onToggleShipping,
          onAddToCart = { onAddToCart(product) }
        )
      }
    }
  }
}

@Composable
private fun ProductMediaShowcase(
  product: Product,
  currentImage: String,
  galleryIndex: Int,
  isFavorite: Boolean,
  onGallerySelect: (Int) -> Unit,
  onToggleFavorite: () -> Unit
) {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(16.dp))
      .background(CreamSurfaceContainerLow)
      .padding(14.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .aspectRatio(4f / 5f)
        .clip(RoundedCornerShape(14.dp))
        .background(CreamSurfaceContainerLowest)
    ) {
      SanMiguelImage(
        model = currentImage,
        contentDescription = product.name,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
      )

      // Floating badge top left
      Row(
        modifier = Modifier
          .align(Alignment.TopStart)
          .padding(10.dp)
          .clip(CircleShape)
          .background(ChocolatePrimaryContainer.copy(alpha = 0.85f))
          .padding(horizontal = 10.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Icon(
          imageVector = Icons.Default.AutoAwesome,
          contentDescription = null,
          tint = GoldSecondaryContainer,
          modifier = Modifier.size(13.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
          text = "EDICIÓN COLECCIÓN 2024",
          style = MaterialTheme.typography.labelSmall.copy(
            fontSize = 9.sp,
            fontWeight = FontWeight.Bold
          ),
          color = GoldSecondaryContainer
        )
      }

      // Heart Icon top right
      IconButton(
        onClick = onToggleFavorite,
        modifier = Modifier
          .align(Alignment.TopEnd)
          .padding(10.dp)
          .size(36.dp)
          .clip(CircleShape)
          .background(CreamSurfaceContainerLowest.copy(alpha = 0.9f))
          .testTag("detail_fav_button")
      ) {
        Icon(
          imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
          contentDescription = "Guardar en favoritos",
          tint = if (isFavorite) ErrorRed else OnSurfaceDark,
          modifier = Modifier.size(20.dp)
        )
      }

      // Zoom guidance pill bottom right
      Row(
        modifier = Modifier
          .align(Alignment.BottomEnd)
          .padding(10.dp)
          .clip(CircleShape)
          .background(CreamSurfaceContainerLowest.copy(alpha = 0.85f))
          .padding(horizontal = 8.dp, vertical = 3.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Icon(
          imageVector = Icons.Default.Loupe,
          contentDescription = null,
          tint = OnSurfaceVariant,
          modifier = Modifier.size(13.dp)
        )
        Spacer(modifier = Modifier.width(3.dp))
        Text(
          text = "Zoom",
          style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
          color = OnSurfaceVariant
        )
      }
    }

    Spacer(modifier = Modifier.height(14.dp))

    // Gallery Thumbnail Rail
    Row(
      horizontalArrangement = Arrangement.spacedBy(12.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      val galleryThumbnails = listOf(
        SanMiguelData.detailGallery[1],
        SanMiguelData.detailGallery[2],
        SanMiguelData.detailGallery[3]
      )

      galleryThumbnails.forEachIndexed { index, thumbUrl ->
        val isSelected = galleryIndex == (index + 1) % SanMiguelData.detailGallery.size
        Box(
          modifier = Modifier
            .size(56.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(CreamSurfaceContainer)
            .border(
              width = if (isSelected) 2.dp else 0.dp,
              color = if (isSelected) GoldSecondary else Color.Transparent,
              shape = RoundedCornerShape(10.dp)
            )
            .clickable { onGallerySelect((index + 1) % SanMiguelData.detailGallery.size) }
            .padding(2.dp)
        ) {
          SanMiguelImage(
            model = thumbUrl,
            contentDescription = "Miniatura $index",
            contentScale = ContentScale.Crop,
            modifier = Modifier
              .fillMaxSize()
              .clip(RoundedCornerShape(8.dp))
          )
        }
      }
    }
  }
}

@Composable
private fun ProductInfoForm(
  product: Product,
  uiState: ShopUiState,
  totalPackPrice: Double,
  onPackSelect: (PackOption) -> Unit,
  onChangeQuantity: (Int) -> Unit,
  onToggleRibbon: (Boolean) -> Unit,
  onToggleCard: (Boolean) -> Unit,
  onCardMessageChange: (String) -> Unit,
  onToggleIngredients: () -> Unit,
  onToggleShipping: () -> Unit,
  onAddToCart: () -> Unit
) {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 14.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // Header: Origin and Title
    Column {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
          imageVector = Icons.Default.WorkspacePremium,
          contentDescription = null,
          tint = GoldSecondary,
          modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
          text = "OBRADOR SAN MIGUEL · LOTE EXCLUSIVO",
          style = MaterialTheme.typography.labelSmall.copy(
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
          ),
          color = GoldSecondary
        )
      }

      Spacer(modifier = Modifier.height(4.dp))

      Text(
        text = "Bombón de Autor: Praliné Avellana Dorada & Fleur de Sel",
        style = MaterialTheme.typography.headlineMedium.copy(
          fontFamily = FontFamily.Serif,
          fontWeight = FontWeight.Bold,
          fontSize = 23.sp
        ),
        color = ChocolatePrimaryContainer,
        lineHeight = 29.sp
      )

      Spacer(modifier = Modifier.height(4.dp))

      Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
          text = "€${String.format(java.util.Locale.US, "%.2f", uiState.detailSelectedPack.price)}",
          style = MaterialTheme.typography.titleLarge.copy(
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold
          ),
          color = GoldSecondary
        )
        Spacer(modifier = Modifier.width(6.dp))
        val perUd = (uiState.detailSelectedPack.price / uiState.detailSelectedPack.count)
        Text(
          text = "(Pack de ${uiState.detailSelectedPack.count} unidades · €${String.format(java.util.Locale.US, "%.2f", perUd)}/ud)",
          style = MaterialTheme.typography.bodySmall,
          color = OnSurfaceVariant
        )
      }
    }

    // Origin Badges Strip
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      OriginBadge(label = "70% Cacao Ecuador", icon = Icons.Default.Eco)
      OriginBadge(label = "Avellana IGP Piamonte", icon = Icons.Default.Verified)
      OriginBadge(label = "Elaboración Manual", icon = Icons.Default.Handshake)
    }

    // Ficha de Cata Sensorial
    Card(
      modifier = Modifier.fillMaxWidth(),
      shape = RoundedCornerShape(14.dp),
      colors = CardDefaults.cardColors(containerColor = CreamSurfaceContainerLow)
    ) {
      Column(modifier = Modifier.padding(14.dp)) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
              imageVector = Icons.Default.Psychology,
              contentDescription = null,
              tint = ChocolatePrimaryContainer,
              modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = "Ficha de Cata Sensorial",
              style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
              color = OnSurfaceDark
            )
          }
          Text(
            text = "NOTAS DE OBRADOR",
            style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp),
            color = OnSurfaceVariant
          )
        }

        Spacer(modifier = Modifier.height(12.dp))

        MeterBar(
          label = "Intensidad de Cacao",
          ratingText = "4 de 5",
          filledCount = 4
        )

        Spacer(modifier = Modifier.height(10.dp))

        MeterBar(
          label = "Nivel de Dulzor Equilibrado",
          ratingText = "2 de 5",
          filledCount = 2
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
          text = "Perfil Aromático y Textura",
          style = MaterialTheme.typography.bodySmall,
          color = OnSurfaceVariant
        )

        Spacer(modifier = Modifier.height(6.dp))

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          AromaChip(text = "Tostado Intenso")
          AromaChip(text = "Vainilla Bourbon")
        }
        Spacer(modifier = Modifier.height(6.dp))
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          AromaChip(text = "Toque Floral")
          AromaChip(text = "Sal Marina Crunch")
        }
      }
    }

    // Formato de Presentación
    Column {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = "Formato de Presentación",
          style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
          color = OnSurfaceDark
        )
        Text(
          text = "Seleccione estuche",
          style = MaterialTheme.typography.bodySmall,
          color = OnSurfaceVariant
        )
      }

      Spacer(modifier = Modifier.height(8.dp))

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        PackOptionCard(
          option = PackOption.SINGLE,
          isSelected = uiState.detailSelectedPack == PackOption.SINGLE,
          modifier = Modifier.weight(1f),
          onClick = { onPackSelect(PackOption.SINGLE) }
        )

        PackOptionCard(
          option = PackOption.BOX6,
          isSelected = uiState.detailSelectedPack == PackOption.BOX6,
          hasStar = true,
          modifier = Modifier.weight(1f),
          onClick = { onPackSelect(PackOption.BOX6) }
        )

        PackOptionCard(
          option = PackOption.BOX12,
          isSelected = uiState.detailSelectedPack == PackOption.BOX12,
          modifier = Modifier.weight(1f),
          onClick = { onPackSelect(PackOption.BOX12) }
        )
      }
    }

    // Stepper Quantity Control
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(12.dp))
        .background(CreamSurfaceContainerLow)
        .padding(14.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Column {
        Text(
          text = "Número de Paquetes",
          style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
          color = OnSurfaceDark
        )
        Text(
          text = "Unidades del formato elegido",
          style = MaterialTheme.typography.bodySmall,
          color = OnSurfaceVariant
        )
      }

      Row(
        modifier = Modifier
          .clip(CircleShape)
          .background(CreamSurfaceContainerLowest)
          .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        IconButton(
          onClick = { onChangeQuantity(-1) },
          modifier = Modifier
            .size(32.dp)
            .testTag("detail_minus_qty")
        ) {
          Icon(Icons.Default.Remove, contentDescription = "Menos", tint = OnSurfaceDark, modifier = Modifier.size(16.dp))
        }

        Text(
          text = "${uiState.detailQuantity}",
          style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
          color = ChocolatePrimaryContainer,
          modifier = Modifier.padding(horizontal = 8.dp)
        )

        IconButton(
          onClick = { onChangeQuantity(1) },
          modifier = Modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(ChocolatePrimaryContainer)
            .testTag("detail_plus_qty")
        ) {
          Icon(Icons.Default.Add, contentDescription = "Más", tint = CreamSurface, modifier = Modifier.size(16.dp))
        }
      }
    }

    // Gift Presentation Options Card
    Card(
      modifier = Modifier.fillMaxWidth(),
      shape = RoundedCornerShape(14.dp),
      colors = CardDefaults.cardColors(containerColor = CreamSurfaceContainerLow)
    ) {
      Column(modifier = Modifier.padding(14.dp)) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Redeem, contentDescription = null, tint = GoldSecondary, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = "Presentación de Regalo San Miguel",
              style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
              color = OnSurfaceDark
            )
          }
          Text(
            text = "CORTESÍA",
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
            color = GoldSecondary
          )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Option 1: Satin ribbon
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(CreamSurfaceContainer)
            .clickable { onToggleRibbon(!uiState.detailRibbonSelected) }
            .padding(10.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Checkbox(
            checked = uiState.detailRibbonSelected,
            onCheckedChange = onToggleRibbon,
            colors = CheckboxDefaults.colors(checkedColor = ChocolatePrimaryContainer)
          )
          Spacer(modifier = Modifier.width(6.dp))
          Column(modifier = Modifier.weight(1f)) {
            Text(
              text = "Lazo artesanal de satén burdeos",
              style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
              color = OnSurfaceDark
            )
            Text(
              text = "Atado a mano con sello dorado tradicional",
              style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
              color = OnSurfaceVariant
            )
          }
          Text(text = "Gratis", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold), color = GoldSecondary)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Option 2: Handwritten Card
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(CreamSurfaceContainer)
            .padding(10.dp)
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Checkbox(
              checked = uiState.detailCardSelected,
              onCheckedChange = onToggleCard,
              colors = CheckboxDefaults.colors(checkedColor = ChocolatePrimaryContainer)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Column(modifier = Modifier.weight(1f)) {
              Text(
                text = "Tarjeta con caligrafía manual",
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                color = OnSurfaceDark
              )
              Text(
                text = "Redactada con pluma estilográfica de tinta nogal",
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
                color = OnSurfaceVariant
              )
            }
            Text(text = "Incluido", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold), color = GoldSecondary)
          }

          AnimatedVisibility(visible = uiState.detailCardSelected) {
            Column(modifier = Modifier.padding(top = 8.dp)) {
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
              ) {
                Text(text = "Texto de la dedicatoria", style = MaterialTheme.typography.labelSmall, color = OnSurfaceVariant)
                Text(text = "${uiState.detailCardMessage.length} / 120", style = MaterialTheme.typography.labelSmall, color = OnSurfaceVariant)
              }
              Spacer(modifier = Modifier.height(4.dp))
              OutlinedTextField(
                value = uiState.detailCardMessage,
                onValueChange = { if (it.length <= 120) onCardMessageChange(it) },
                placeholder = { Text("Escriba aquí sus palabras de cariño...") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 3,
                colors = OutlinedTextFieldDefaults.colors(
                  focusedContainerColor = CreamSurfaceContainerLowest,
                  unfocusedContainerColor = CreamSurfaceContainerLowest,
                  focusedBorderColor = GoldSecondary,
                  unfocusedBorderColor = CreamSurfaceContainerHigh
                )
              )
            }
          }
        }
      }
    }

    // Collapsible Accordions
    Card(
      modifier = Modifier.fillMaxWidth(),
      shape = RoundedCornerShape(12.dp),
      colors = CardDefaults.cardColors(containerColor = CreamSurfaceContainerLow)
    ) {
      Column(modifier = Modifier.padding(14.dp)) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .clickable { onToggleIngredients() },
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.MenuBook, contentDescription = null, tint = OnSurfaceVariant, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = "Ingredientes Selectos & Alérgenos",
              style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Medium),
              color = OnSurfaceDark
            )
          }
          Icon(
            imageVector = if (uiState.detailIngredientsExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
            contentDescription = null,
            tint = OnSurfaceVariant
          )
        }

        AnimatedVisibility(visible = uiState.detailIngredientsExpanded) {
          Column(modifier = Modifier.padding(top = 10.dp)) {
            Text(
              text = "Cobertura de chocolate negro 70% (habas de cacao de Ecuador, azúcar de caña virgen, manteca de cacao pura), praliné tostado (avellana IGP Piamonte 55%, azúcar caramelizado a fuego lento), nata pasteurizada fresca, mantequilla de caserío clarificada, flor de sal marina de Cádiz (0.4%), vainilla Bourbon natural de Madagascar.",
              style = MaterialTheme.typography.bodySmall,
              color = OnSurfaceVariant,
              lineHeight = 18.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
              modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(CreamSurfaceContainerHigh)
                .padding(8.dp)
            ) {
              Text(
                text = "Alérgenos: Contiene avellana (fruto de cáscara) y lácteos. Puede contener trazas de almendra, pistacho y soja sin gluten.",
                style = MaterialTheme.typography.labelSmall,
                color = ChocolatePrimaryContainer
              )
            }
          }
        }
      }
    }

    Card(
      modifier = Modifier.fillMaxWidth(),
      shape = RoundedCornerShape(12.dp),
      colors = CardDefaults.cardColors(containerColor = CreamSurfaceContainerLow)
    ) {
      Column(modifier = Modifier.padding(14.dp)) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .clickable { onToggleShipping() },
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.AcUnit, contentDescription = null, tint = GoldSecondary, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = "Envío en Frío Controlado 24h",
              style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Medium),
              color = OnSurfaceDark
            )
          }
          Icon(
            imageVector = if (uiState.detailShippingExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
            contentDescription = null,
            tint = OnSurfaceVariant
          )
        }

        AnimatedVisibility(visible = uiState.detailShippingExpanded) {
          Column(modifier = Modifier.padding(top = 10.dp)) {
            Text(
              text = "Para preservar la atemperación óptima de la manteca de cacao y la emulsión viva del praliné, todos nuestros envíos se despachan en embalaje isotérmico reciclable con acumuladores de frío seco a 14°C - 18°C.",
              style = MaterialTheme.typography.bodySmall,
              color = OnSurfaceVariant,
              lineHeight = 18.sp
            )
            Spacer(modifier = Modifier.height(6.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(Icons.Default.LocalShipping, contentDescription = null, tint = GoldSecondary, modifier = Modifier.size(16.dp))
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = "Entrega garantizada antes de las 13:30h del día hábil siguiente.",
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                color = OnSurfaceDark
              )
            }
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(8.dp))

    // Bottom Add to Cart CTA Button
    Button(
      onClick = onAddToCart,
      modifier = Modifier
        .fillMaxWidth()
        .height(56.dp)
        .testTag("detail_add_to_cart_btn"),
      colors = ButtonDefaults.buttonColors(containerColor = ChocolatePrimaryContainer),
      shape = RoundedCornerShape(28.dp),
      elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(
            imageVector = Icons.Default.ShoppingBag,
            contentDescription = null,
            tint = GoldSecondaryContainer,
            modifier = Modifier.size(20.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Column {
            Text(
              text = "Añadir a la Bolsa",
              style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
              color = CreamSurface
            )
            Text(
              text = "${uiState.detailSelectedPack.label} (${uiState.detailQuantity}x)",
              style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
              color = GoldSecondaryContainer
            )
          }
        }

        Text(
          text = "€${String.format(java.util.Locale.US, "%.2f", totalPackPrice)}",
          style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
          color = GoldSecondaryContainer
        )
      }
    }
  }
}

@Composable
private fun OriginBadge(label: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
  Row(
    modifier = Modifier
      .clip(CircleShape)
      .background(CreamSurfaceContainerHigh)
      .padding(horizontal = 8.dp, vertical = 5.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Icon(icon, contentDescription = null, tint = GoldSecondary, modifier = Modifier.size(13.dp))
    Spacer(modifier = Modifier.width(4.dp))
    Text(
      text = label,
      style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
      color = OnSurfaceDark
    )
  }
}

@Composable
private fun MeterBar(label: String, ratingText: String, filledCount: Int) {
  Column {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Text(text = label, style = MaterialTheme.typography.bodySmall, color = OnSurfaceDark)
      Text(text = ratingText, style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold), color = GoldSecondary)
    }
    Spacer(modifier = Modifier.height(4.dp))
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
      for (i in 1..5) {
        Box(
          modifier = Modifier
            .weight(1f)
            .height(6.dp)
            .clip(CircleShape)
            .background(if (i <= filledCount) ChocolatePrimaryContainer else CreamSurfaceContainerHigh)
        )
      }
    }
  }
}

@Composable
private fun AromaChip(text: String) {
  Row(
    modifier = Modifier
      .clip(CircleShape)
      .background(CreamSurfaceContainerLowest)
      .padding(horizontal = 10.dp, vertical = 5.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Box(
      modifier = Modifier
        .size(6.dp)
        .background(GoldSecondary, CircleShape)
    )
    Spacer(modifier = Modifier.width(6.dp))
    Text(text = text, style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.5.sp), color = OnSurfaceDark)
  }
}

@Composable
private fun PackOptionCard(
  option: PackOption,
  isSelected: Boolean,
  hasStar: Boolean = false,
  modifier: Modifier = Modifier,
  onClick: () -> Unit
) {
  Card(
    modifier = modifier
      .clip(RoundedCornerShape(12.dp))
      .clickable(onClick = onClick)
      .testTag("pack_option_${option.id}"),
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(
      containerColor = if (isSelected) ChocolatePrimaryContainer else CreamSurfaceContainerLow
    )
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = if (hasStar) "COFRE" else "Formato",
          style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp, fontWeight = FontWeight.Bold),
          color = if (isSelected) SecondaryFixedDim else OnSurfaceVariant
        )
        if (hasStar) {
          Icon(Icons.Default.Star, contentDescription = null, tint = GoldSecondaryContainer, modifier = Modifier.size(13.dp))
        }
      }

      Spacer(modifier = Modifier.height(2.dp))

      Text(
        text = "${option.count} uds",
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
        color = if (isSelected) CreamSurface else OnSurfaceDark
      )

      Text(
        text = "€${String.format(java.util.Locale.US, "%.2f", option.price)}",
        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
        color = if (isSelected) SecondaryFixed else GoldSecondary
      )
    }
  }
}
