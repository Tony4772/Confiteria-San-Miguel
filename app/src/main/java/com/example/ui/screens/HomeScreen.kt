package com.example.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Redeem
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
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
import com.example.viewmodel.ShopUiState

@Composable
fun HomeScreen(
  uiState: ShopUiState,
  onNavigateToBuilder: () -> Unit,
  onProductClick: (Product) -> Unit,
  onAddToCart: (Product) -> Unit,
  onToggleFavorite: (String) -> Unit,
  onFilterSelect: (String) -> Unit,
  onOpenSustainability: () -> Unit
) {
  val scrollState = rememberScrollState()

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
        .padding(bottom = 96.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = contentMaxWidth)
      ) {
        // 1. Subtle Ambient Salutation
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column {
            Text(
              text = "MAESTROS CHOCOLATEROS DESDE 1928",
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.2.sp
              ),
              color = GoldSecondary
            )
            Text(
              text = "El arte del cacao puro",
              style = MaterialTheme.typography.headlineSmall.copy(
                fontFamily = FontFamily.Serif,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Medium
              ),
              color = ChocolatePrimaryContainer
            )
          }

          Row(
            modifier = Modifier
              .clip(CircleShape)
              .background(CreamSurfaceContainerHigh)
              .padding(horizontal = 10.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Icon(
              imageVector = Icons.Default.WorkspacePremium,
              contentDescription = null,
              tint = GoldSecondary,
              modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
              text = "Gourmet Selection",
              style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
              color = ChocolatePrimaryContainer
            )
          }
        }

        // 2. Featured Seasonal Hero Banner
        Card(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .testTag("hero_seasonal_banner"),
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors(containerColor = ChocolatePrimaryContainer),
          elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .height(if (isTablet) 300.dp else 260.dp)
          ) {
            // Background Image (Lazy Loaded)
            SanMiguelImage(
              model = SanMiguelData.HERO_IMAGE_URL,
              contentDescription = "Trufas de Avellana del Piamonte",
              contentScale = ContentScale.Crop,
              modifier = Modifier.fillMaxSize()
            )

            // Gradient Scrim
            Box(
              modifier = Modifier
                .fillMaxSize()
                .background(
                  Brush.verticalGradient(
                    colors = listOf(
                      Color.Transparent,
                      ChocolatePrimaryContainer.copy(alpha = 0.75f),
                      ChocolatePrimaryContainer
                    ),
                    startY = 60f
                  )
                )
            )

            // Content Overlay
            Column(
              modifier = Modifier
                .fillMaxSize()
                .padding(if (isTablet) 24.dp else 16.dp),
              verticalArrangement = Arrangement.Bottom
            ) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 6.dp)
              ) {
                Row(
                  modifier = Modifier
                    .clip(CircleShape)
                    .background(GoldSecondaryContainer)
                    .padding(horizontal = 8.dp, vertical = 3.dp),
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = OnGoldSecondaryContainer,
                    modifier = Modifier.size(12.dp)
                  )
                  Spacer(modifier = Modifier.width(3.dp))
                  Text(
                    text = "EDICIÓN LIMITADA",
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                    color = OnGoldSecondaryContainer
                  )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                  text = "Otoño · Invierno",
                  style = MaterialTheme.typography.labelSmall,
                  color = SecondaryFixed.copy(alpha = 0.9f)
                )
              }

              Text(
                text = "Trufas de Avellana del Piamonte & Cacao 72%",
                style = MaterialTheme.typography.headlineMedium.copy(
                  fontFamily = FontFamily.Serif,
                  fontWeight = FontWeight.SemiBold,
                  fontSize = if (isTablet) 26.sp else 22.sp
                ),
                color = CreamSurface,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
              )

              Spacer(modifier = Modifier.height(4.dp))

              Text(
                text = "Cosecha especial con notas tostadas de avellana silvestre y toque sutil de flor de sal atlántica.",
                style = MaterialTheme.typography.bodySmall,
                color = CreamSurfaceContainerHigh,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
              )

              Spacer(modifier = Modifier.height(12.dp))

              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
              ) {
                Column {
                  Text(
                    text = "CAJA EXCLUSIVA",
                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp),
                    color = SecondaryFixedDim
                  )
                  Text(
                    text = "24,90 €",
                    style = MaterialTheme.typography.titleLarge.copy(
                      fontFamily = FontFamily.Serif,
                      fontWeight = FontWeight.Bold
                    ),
                    color = SecondaryFixed
                  )
                }

                Button(
                  onClick = onNavigateToBuilder,
                  colors = ButtonDefaults.buttonColors(containerColor = GoldSecondary),
                  shape = RoundedCornerShape(20.dp),
                  contentPadding = androidx.compose.foundation.layout.PaddingValues(
                    horizontal = 18.dp,
                    vertical = 10.dp
                  ),
                  modifier = Modifier.testTag("explore_collection_button")
                ) {
                  Text(
                    text = "Explorar Colección",
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = CreamSurface
                  )
                  Spacer(modifier = Modifier.width(6.dp))
                  Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    tint = CreamSurface,
                    modifier = Modifier.size(16.dp)
                  )
                }
              }
            }
          }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // 3. Horizontal Category Carousel
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
              modifier = Modifier
                .width(5.dp)
                .height(16.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(GoldSecondary)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = "Categorías de Confitería",
              style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
              color = OnSurfaceDark
            )
          }

          Text(
            text = "Ver todas",
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
            color = GoldSecondary,
            modifier = Modifier.clickable { onNavigateToBuilder() }
          )
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(16.dp),
          contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 16.dp)
        ) {
          items(SanMiguelData.categories) { (categoryName, imgUrl) ->
            Column(
              horizontalAlignment = Alignment.CenterHorizontally,
              modifier = Modifier
                .width(84.dp)
                .clickable { onNavigateToBuilder() }
            ) {
              Box(
                modifier = Modifier
                  .size(68.dp)
                  .clip(CircleShape)
                  .background(CreamSurfaceContainerHigh)
                  .padding(2.dp)
              ) {
                SanMiguelImage(
                  model = imgUrl,
                  contentDescription = categoryName,
                  contentScale = ContentScale.Crop,
                  modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                )
              }

              Spacer(modifier = Modifier.height(6.dp))

              Text(
                text = categoryName,
                style = MaterialTheme.typography.labelSmall.copy(
                  fontWeight = FontWeight.SemiBold,
                  fontSize = 11.sp
                ),
                color = OnSurfaceDark,
                maxLines = 2,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                lineHeight = 14.sp
              )
            }
          }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // 4. Quick Filter Pills
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          FilterPill(
            label = "Más vendidos",
            icon = Icons.Default.AutoAwesome,
            isSelected = uiState.activeFilter == "Más vendidos",
            onClick = { onFilterSelect("Más vendidos") }
          )
          FilterPill(
            label = "Sin gluten",
            icon = Icons.Default.Spa,
            isSelected = uiState.activeFilter == "Sin gluten",
            onClick = { onFilterSelect("Sin gluten") }
          )
          FilterPill(
            label = "Veganos",
            icon = Icons.Default.Eco,
            isSelected = uiState.activeFilter == "Veganos",
            onClick = { onFilterSelect("Veganos") }
          )
          FilterPill(
            label = "Para Regalar",
            icon = Icons.Default.Redeem,
            isSelected = uiState.activeFilter == "Para Regalar",
            onClick = { onFilterSelect("Para Regalar") }
          )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 5. Responsive Star Products Grid ("Creaciones Emblemáticas")
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.Bottom
        ) {
          Text(
            text = "Creaciones Emblemáticas",
            style = MaterialTheme.typography.titleLarge.copy(
              fontFamily = FontFamily.Serif,
              fontWeight = FontWeight.Bold
            ),
            color = ChocolatePrimaryContainer
          )
          Text(
            text = "RECETAS MAGISTRALES",
            style = MaterialTheme.typography.labelSmall.copy(
              fontWeight = FontWeight.Bold,
              letterSpacing = 0.8.sp
            ),
            color = GoldSecondary
          )
        }

        Spacer(modifier = Modifier.height(12.dp))

        val displayProducts = if (uiState.activeFilter == "Más vendidos") {
          SanMiguelData.products
        } else {
          val matches = SanMiguelData.products.filter { it.category == uiState.activeFilter }
          if (matches.isNotEmpty()) matches else SanMiguelData.products
        }

        // Adaptive Responsive Grid Rows (2 on phones, 3 on tablets, 4 on desktop)
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
          verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
          val chunked = displayProducts.chunked(productColumns)
          chunked.forEach { rowItems ->
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
              rowItems.forEach { product ->
                Box(modifier = Modifier.weight(1f)) {
                  ProductCard(
                    product = product,
                    isFavorite = uiState.favorites.contains(product.id),
                    onCardClick = { onProductClick(product) },
                    onAddToCart = { onAddToCart(product) },
                    onToggleFavorite = { onToggleFavorite(product.id) }
                  )
                }
              }
              // Fill remaining spaces in row
              repeat(productColumns - rowItems.size) {
                Spacer(modifier = Modifier.weight(1f))
              }
            }
          }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 6. Interactive Custom Gift Builder Banner
        Card(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .testTag("custom_gift_builder_banner"),
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors(containerColor = CreamSurfaceContainer),
          elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(if (isTablet) 20.dp else 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
          ) {
            Column(modifier = Modifier.weight(1f)) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 4.dp)
              ) {
                Icon(
                  imageVector = Icons.Default.CardGiftcard,
                  contentDescription = null,
                  tint = GoldSecondary,
                  modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                  text = "PERSONALIZACIÓN A MEDIDA",
                  style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                  color = GoldSecondary
                )
              }

              Text(
                text = "¿Buscas un regalo memorable?",
                style = MaterialTheme.typography.titleMedium.copy(
                  fontFamily = FontFamily.Serif,
                  fontWeight = FontWeight.Bold,
                  fontSize = 18.sp
                ),
                color = ChocolatePrimaryContainer
              )

              Spacer(modifier = Modifier.height(4.dp))

              Text(
                text = "Arma tu caja personalizada en 3 simples pasos: elige el estuche, selecciona tus bombones y añade un mensaje en caligrafía dorada.",
                style = MaterialTheme.typography.bodySmall,
                color = OnSurfaceVariant,
                lineHeight = 18.sp
              )

              Spacer(modifier = Modifier.height(12.dp))

              // Stepper Mini Indicators
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
              ) {
                StepChip(step = "1", label = "Tamaño")
                Text(text = "→", fontSize = 11.sp, color = OutlineGrey)
                StepChip(step = "2", label = "Sabores")
                Text(text = "→", fontSize = 11.sp, color = OutlineGrey)
                StepChip(step = "3", label = "Lazo")
              }

              Spacer(modifier = Modifier.height(14.dp))

              Button(
                onClick = onNavigateToBuilder,
                colors = ButtonDefaults.buttonColors(containerColor = ChocolatePrimaryContainer),
                shape = RoundedCornerShape(20.dp),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(
                  horizontal = 18.dp,
                  vertical = 10.dp
                ),
                modifier = Modifier.testTag("design_box_now_button")
              ) {
                Text(
                  text = "Diseñar mi Caja Ahora",
                  style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                  color = CreamSurfaceContainerLowest
                )
                Spacer(modifier = Modifier.width(6.dp))
                Icon(
                  imageVector = Icons.Default.CardGiftcard,
                  contentDescription = null,
                  tint = CreamSurfaceContainerLowest,
                  modifier = Modifier.size(16.dp)
                )
              }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Box(
              modifier = Modifier
                .size(if (isTablet) 72.dp else 56.dp)
                .clip(CircleShape)
                .background(GoldSecondary.copy(alpha = 0.15f)),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = Icons.Default.Redeem,
                contentDescription = null,
                tint = GoldSecondary,
                modifier = Modifier.size(if (isTablet) 36.dp else 30.dp)
              )
            }
          }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 7. Sommelier Review & Fair Trade Seal
        Card(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors(containerColor = CreamSurfaceContainerLowest),
          elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
          Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.Top) {
              SanMiguelImage(
                model = SanMiguelData.PROFILE_URL,
                contentDescription = "Marta Velázquez",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                  .size(52.dp)
                  .clip(CircleShape)
              )

              Spacer(modifier = Modifier.width(14.dp))

              Column {
                Row {
                  repeat(5) {
                    Icon(
                      imageVector = Icons.Default.Star,
                      contentDescription = null,
                      tint = GoldSecondary,
                      modifier = Modifier.size(16.dp)
                    )
                  }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                  text = "\"El balance tánico y la untuosidad de sus bombones de autor son una obra maestra indiscutible en la chocolatería europea.\"",
                  style = MaterialTheme.typography.bodyMedium.copy(
                    fontFamily = FontFamily.Serif,
                    fontStyle = FontStyle.Italic,
                    fontSize = 15.sp
                  ),
                  color = ChocolatePrimaryContainer,
                  lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(6.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                  Text(
                    text = "Marta Velázquez",
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = OnSurfaceDark
                  )
                  Text(
                    text = " · ",
                    color = OutlineGrey,
                    fontSize = 12.sp
                  )
                  Text(
                    text = "Premio Nacional de Gastronomía",
                    style = MaterialTheme.typography.labelSmall,
                    color = OnSurfaceVariant
                  )
                }
              }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Seal bar
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(CreamSurfaceContainerLow)
                .padding(12.dp),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
              ) {
                Box(
                  modifier = Modifier
                    .size(36.dp)
                    .background(SecondaryFixed, CircleShape),
                  contentAlignment = Alignment.Center
                ) {
                  Icon(
                    imageVector = Icons.Default.Verified,
                    contentDescription = null,
                    tint = ChocolatePrimaryContainer,
                    modifier = Modifier.size(20.dp)
                  )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                  Text(
                    text = "Cacao 100% Sostenible",
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = ChocolatePrimaryContainer
                  )
                  Text(
                    text = "Comercio Justo y plantaciones regenerativas",
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.5.sp),
                    color = OnSurfaceVariant
                  )
                }
              }

              TextButton(
                onClick = onOpenSustainability,
                modifier = Modifier.testTag("learn_more_sustainability_button")
              ) {
                Text(
                  text = "CONOCER",
                  style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                  color = GoldSecondary
                )
              }
            }
          }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Footer note
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Box(
            modifier = Modifier
              .width(36.dp)
              .height(2.dp)
              .clip(RoundedCornerShape(1.dp))
              .background(GoldSecondary.copy(alpha = 0.3f))
          )
          Spacer(modifier = Modifier.height(8.dp))
          Text(
            text = "CONFITERÍA SAN MIGUEL · MADRID, 1928",
            style = MaterialTheme.typography.labelSmall.copy(
              letterSpacing = 1.sp,
              fontSize = 10.sp
            ),
            color = OutlineGrey
          )
        }
      }
    }
  }
}

@Composable
private fun FilterPill(
  label: String,
  icon: ImageVector,
  isSelected: Boolean,
  onClick: () -> Unit
) {
  Row(
    modifier = Modifier
      .clip(CircleShape)
      .background(if (isSelected) ChocolatePrimaryContainer else CreamSurfaceContainerLow)
      .clickable(onClick = onClick)
      .padding(horizontal = 14.dp, vertical = 8.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Icon(
      imageVector = icon,
      contentDescription = null,
      tint = if (isSelected) SecondaryFixed else OutlineGrey,
      modifier = Modifier.size(15.dp)
    )
    Spacer(modifier = Modifier.width(6.dp))
    Text(
      text = label,
      style = MaterialTheme.typography.labelMedium.copy(
        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
      ),
      color = if (isSelected) CreamSurfaceContainerLowest else OnSurfaceDark
    )
  }
}

@Composable
private fun StepChip(step: String, label: String) {
  Row(
    modifier = Modifier
      .clip(CircleShape)
      .background(CreamSurfaceContainerLowest)
      .padding(horizontal = 8.dp, vertical = 4.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Box(
      modifier = Modifier
        .size(16.dp)
        .background(GoldSecondary, CircleShape),
      contentAlignment = Alignment.Center
    ) {
      Text(
        text = step,
        color = CreamSurface,
        fontSize = 10.sp,
        fontWeight = FontWeight.Bold
      )
    }
    Spacer(modifier = Modifier.width(5.dp))
    Text(
      text = label,
      style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
      color = ChocolatePrimaryContainer
    )
  }
}

@Composable
private fun ProductCard(
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
      .testTag("product_card_${product.id}"),
    shape = RoundedCornerShape(14.dp),
    colors = CardDefaults.cardColors(containerColor = CreamSurfaceContainerLowest),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
  ) {
    Column(modifier = Modifier.padding(10.dp)) {
      // 4/5 Aspect Ratio Image with Badge and Favorite Icon (Lazy Loaded)
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
          val isFavBadge = badgeText == "Favorito"
          Box(
            modifier = Modifier
              .align(Alignment.TopStart)
              .padding(6.dp)
              .clip(CircleShape)
              .background(
                if (isFavBadge) GoldSecondary else CreamSurfaceContainerHigh.copy(alpha = 0.9f)
              )
              .padding(horizontal = 8.dp, vertical = 3.dp)
          ) {
            Text(
              text = badgeText,
              style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold
              ),
              color = if (isFavBadge) CreamSurface else ChocolatePrimaryContainer
            )
          }
        }

        // Heart Icon
        IconButton(
          onClick = onToggleFavorite,
          modifier = Modifier
            .align(Alignment.TopEnd)
            .padding(4.dp)
            .size(30.dp)
            .clip(CircleShape)
            .background(CreamSurfaceContainerLowest.copy(alpha = 0.85f))
            .testTag("fav_btn_${product.id}")
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

      // Rating
      Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
          imageVector = Icons.Default.Star,
          contentDescription = null,
          tint = GoldSecondary,
          modifier = Modifier.size(13.dp)
        )
        Spacer(modifier = Modifier.width(3.dp))
        Text(
          text = "${product.rating} (${product.reviewsCount})",
          style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
          color = OnSurfaceVariant
        )
      }

      Spacer(modifier = Modifier.height(4.dp))

      // Title
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

      // Price and Add Button
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column {
          Text(
            text = "€${String.format(java.util.Locale.US, "%.2f", product.price)}",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = ChocolatePrimaryContainer
          )
          Text(
            text = if (product.price > 10.0) "Estuche de lujo" else "Por unidad",
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
            .testTag("add_product_${product.id}")
        ) {
          Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Añadir a la bolsa",
            tint = CreamSurfaceContainerLowest,
            modifier = Modifier.size(18.dp)
          )
        }
      }
    }
  }
}
