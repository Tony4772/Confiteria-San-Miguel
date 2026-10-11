package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Cookie
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.TipsAndUpdates
import androidx.compose.material.icons.filled.Widgets
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.data.SanMiguelData
import com.example.model.BonbonFlavor
import com.example.model.BoxTier
import com.example.ui.components.SanMiguelImage
import com.example.ui.theme.ChocolatePrimaryContainer
import com.example.ui.theme.CreamBackground
import com.example.ui.theme.CreamSurface
import com.example.ui.theme.CreamSurfaceContainer
import com.example.ui.theme.CreamSurfaceContainerHigh
import com.example.ui.theme.CreamSurfaceContainerLow
import com.example.ui.theme.CreamSurfaceContainerLowest
import com.example.ui.theme.GoldSecondary
import com.example.ui.theme.GoldSecondaryContainer
import com.example.ui.theme.OnGoldSecondaryContainer
import com.example.ui.theme.OnSurfaceDark
import com.example.ui.theme.OnSurfaceVariant
import com.example.ui.theme.OutlineGrey
import com.example.ui.theme.SecondaryFixed
import com.example.ui.theme.SecondaryFixedDim
import com.example.ui.theme.TertiaryBrown
import com.example.viewmodel.ShopUiState

@Composable
fun BoxBuilderScreen(
  uiState: ShopUiState,
  onSelectTier: (BoxTier) -> Unit,
  onModifyFlavorCount: (String, Int) -> Unit,
  onSelectCategory: (String) -> Unit,
  onAutoFill: () -> Unit,
  onConfirmBox: () -> Unit
) {
  val scrollState = rememberScrollState()

  BoxWithConstraints(
    modifier = Modifier
      .fillMaxSize()
      .background(CreamBackground)
  ) {
    val contentMaxWidth = if (maxWidth >= 720.dp) 740.dp else maxWidth

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
    // 1. Atelier Header
    Row(
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier.padding(bottom = 2.dp)
    ) {
      Icon(
        imageVector = Icons.Default.Star,
        contentDescription = null,
        tint = GoldSecondary,
        modifier = Modifier.size(15.dp)
      )
      Spacer(modifier = Modifier.width(4.dp))
      Text(
        text = "ATELIER DE CREACIÓN PERSONAL",
        style = MaterialTheme.typography.labelSmall.copy(
          fontWeight = FontWeight.Bold,
          letterSpacing = 1.sp
        ),
        color = GoldSecondary
      )
    }

    Text(
      text = "Arma tu Cofre Artesanal",
      style = MaterialTheme.typography.headlineMedium.copy(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp
      ),
      color = ChocolatePrimaryContainer
    )

    Spacer(modifier = Modifier.height(4.dp))

    Text(
      text = "Selecciona cada joya de cacao elaborada en nuestro obrador de Madrid según tus predilecciones.",
      style = MaterialTheme.typography.bodyMedium,
      color = OnSurfaceVariant,
      lineHeight = 20.sp
    )

    Spacer(modifier = Modifier.height(20.dp))

    // 2. Step 1: Presentation Size Selector
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = "1. TAMAÑO DE PRESENTACIÓN",
        style = MaterialTheme.typography.labelMedium.copy(
          fontWeight = FontWeight.Bold,
          letterSpacing = 0.8.sp
        ),
        color = OnSurfaceDark
      )
      Text(
        text = "Caja Hecha a Mano",
        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
        color = GoldSecondary
      )
    }

    Spacer(modifier = Modifier.height(10.dp))

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      SanMiguelData.boxTiers.forEach { tier ->
        val isSelected = uiState.selectedBoxTier.size == tier.size
        Box(modifier = Modifier.weight(1f)) {
          BoxTierCard(
            tier = tier,
            isSelected = isSelected,
            onClick = { onSelectTier(tier) }
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(20.dp))

    // 3. Step 2: The Velvet Cavity Showcase (Matrix Display)
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .testTag("velvet_box_showcase"),
      shape = RoundedCornerShape(18.dp),
      colors = CardDefaults.cardColors(containerColor = ChocolatePrimaryContainer),
      elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp)
      ) {
        // Tray Status Bar
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
              modifier = Modifier
                .size(10.dp)
                .background(GoldSecondaryContainer, CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = uiState.selectedBoxTier.name,
              style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
              color = CreamSurface
            )
          }

          Row(verticalAlignment = Alignment.Bottom) {
            Text(
              text = "${uiState.boxCurrentTotal}",
              style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
              color = GoldSecondaryContainer
            )
            Text(
              text = " / ${uiState.boxMaxCapacity} bombones",
              style = MaterialTheme.typography.labelMedium,
              color = CreamSurfaceContainerHigh
            )
          }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Progress Bar
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .height(6.dp)
            .clip(CircleShape)
            .background(Color.Black.copy(alpha = 0.4f))
        ) {
          val progress = (uiState.boxCurrentTotal.toFloat() / uiState.boxMaxCapacity).coerceIn(0f, 1f)
          Box(
            modifier = Modifier
              .fillMaxWidth(progress)
              .height(6.dp)
              .clip(CircleShape)
              .background(
                Brush.horizontalGradient(
                  colors = listOf(GoldSecondary, GoldSecondaryContainer)
                )
              )
          )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Smart Suggestion Pill
        val remaining = uiState.boxMaxCapacity - uiState.boxCurrentTotal
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White.copy(alpha = 0.1f))
            .padding(horizontal = 12.dp, vertical = 8.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
          ) {
            Icon(
              imageVector = Icons.Default.TipsAndUpdates,
              contentDescription = null,
              tint = GoldSecondaryContainer,
              modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = if (remaining > 0) {
                "¡Agrega $remaining más para completar tu ${uiState.selectedBoxTier.name}!"
              } else {
                "¡Tu ${uiState.selectedBoxTier.name} está completo! 🎉"
              },
              style = MaterialTheme.typography.bodySmall,
              color = CreamSurfaceContainerHigh,
              maxLines = 1,
              overflow = TextOverflow.Ellipsis
            )
          }

          if (remaining > 0) {
            Text(
              text = "RELLENAR",
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.8.sp
              ),
              color = GoldSecondaryContainer,
              modifier = Modifier
                .clickable { onAutoFill() }
                .padding(start = 8.dp)
                .testTag("autofill_button")
            )
          }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // The Velvet Tray Grid
        val columns = when (uiState.selectedBoxTier.size) {
          6 -> 3
          24 -> 6
          else -> 4
        }

        // Build list of slots:
        // Expanded list of filled chocolates up to current total
        val filledSlots = mutableListOf<BonbonFlavor>()
        uiState.bonbonCounts.forEach { (flavorId, count) ->
          val flavor = SanMiguelData.bonbonFlavors.find { it.id == flavorId }
          if (flavor != null) {
            repeat(count) {
              if (filledSlots.size < uiState.boxMaxCapacity) {
                filledSlots.add(flavor)
              }
            }
          }
        }

        val totalSlots = uiState.boxMaxCapacity
        val rows = (totalSlots + columns - 1) / columns

        Column(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(TertiaryBrown.copy(alpha = 0.95f))
            .padding(10.dp),
          verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          for (r in 0 until rows) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              for (c in 0 until columns) {
                val index = r * columns + c
                if (index < totalSlots) {
                  val filledFlavor = filledSlots.getOrNull(index)
                  Box(modifier = Modifier.weight(1f)) {
                    if (filledFlavor != null) {
                      // Filled bonbon slot
                      Box(
                        modifier = Modifier
                          .fillMaxWidth()
                          .aspectRatio(1f)
                          .clip(RoundedCornerShape(8.dp))
                          .background(ChocolatePrimaryContainer)
                          .padding(2.dp)
                      ) {
                        SanMiguelImage(
                          model = filledFlavor.bonbonImageUrl,
                          contentDescription = filledFlavor.name,
                          contentScale = ContentScale.Crop,
                          modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(6.dp))
                        )
                        Box(
                          modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .size(16.dp)
                            .background(GoldSecondary, CircleShape),
                          contentAlignment = Alignment.Center
                        ) {
                          Text(
                            text = "${index + 1}",
                            color = ChocolatePrimaryContainer,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold
                          )
                        }
                      }
                    } else {
                      // Empty cavity
                      Box(
                        modifier = Modifier
                          .fillMaxWidth()
                          .aspectRatio(1f)
                          .clip(RoundedCornerShape(8.dp))
                          .background(ChocolatePrimaryContainer.copy(alpha = 0.4f))
                          .border(
                            1.dp,
                            GoldSecondaryContainer.copy(alpha = 0.2f),
                            RoundedCornerShape(8.dp)
                          )
                          .clickable { onAutoFill() },
                        contentAlignment = Alignment.Center
                      ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                          Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Vacío",
                            tint = GoldSecondaryContainer.copy(alpha = 0.7f),
                            modifier = Modifier.size(16.dp)
                          )
                          Text(
                            text = "VACÍO",
                            style = MaterialTheme.typography.labelSmall.copy(fontSize = 7.sp),
                            color = CreamSurfaceContainerHigh.copy(alpha = 0.6f)
                          )
                        }
                      }
                    }
                  }
                } else {
                  Spacer(modifier = Modifier.weight(1f))
                }
              }
            }
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(24.dp))

    // 4. Step 3: Flavor Filter Chips
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = "2. SABORES & CREACIONES",
        style = MaterialTheme.typography.labelMedium.copy(
          fontWeight = FontWeight.Bold,
          letterSpacing = 0.8.sp
        ),
        color = OnSurfaceDark
      )
      Text(
        text = "14 Variedades",
        style = MaterialTheme.typography.bodySmall,
        color = OnSurfaceVariant
      )
    }

    Spacer(modifier = Modifier.height(10.dp))

    Row(
      modifier = Modifier
        .fillMaxWidth()
        .horizontalScroll(rememberScrollState()),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      FlavorFilterChip(
        label = "Todos los Sabores",
        isSelected = uiState.flavorFilterCategory == "all",
        onClick = { onSelectCategory("all") }
      )
      FlavorFilterChip(
        label = "Chocolates Negros",
        isSelected = uiState.flavorFilterCategory == "dark",
        onClick = { onSelectCategory("dark") }
      )
      FlavorFilterChip(
        label = "Con Leche & Pralinés",
        isSelected = uiState.flavorFilterCategory == "milk",
        onClick = { onSelectCategory("milk") }
      )
      FlavorFilterChip(
        label = "Blancos & Frutales",
        isSelected = uiState.flavorFilterCategory == "fruit",
        onClick = { onSelectCategory("fruit") }
      )
      FlavorFilterChip(
        label = "Rellenos Licor & Especia",
        isSelected = uiState.flavorFilterCategory == "liqueur",
        onClick = { onSelectCategory("liqueur") }
      )
    }

    Spacer(modifier = Modifier.height(14.dp))

    // 5. Flavor rows list
    val filteredFlavors = if (uiState.flavorFilterCategory == "all") {
      SanMiguelData.bonbonFlavors
    } else {
      SanMiguelData.bonbonFlavors.filter { it.category == uiState.flavorFilterCategory }
    }

    Column(
      modifier = Modifier.fillMaxWidth(),
      verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      filteredFlavors.forEach { flavor ->
        val count = uiState.bonbonCounts[flavor.id] ?: 0
        FlavorRowCard(
          flavor = flavor,
          count = count,
          onMinus = { onModifyFlavorCount(flavor.id, -1) },
          onPlus = { onModifyFlavorCount(flavor.id, 1) }
        )
      }
    }

    Spacer(modifier = Modifier.height(20.dp))

    // 6. Guarantee banner
    Card(
      modifier = Modifier.fillMaxWidth(),
      shape = RoundedCornerShape(14.dp),
      colors = CardDefaults.cardColors(containerColor = CreamSurfaceContainer)
    ) {
      Row(
        modifier = Modifier.padding(14.dp),
        verticalAlignment = Alignment.Top
      ) {
        Box(
          modifier = Modifier
            .size(36.dp)
            .background(GoldSecondaryContainer.copy(alpha = 0.5f), CircleShape),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Default.WorkspacePremium,
            contentDescription = null,
            tint = GoldSecondary,
            modifier = Modifier.size(20.dp)
          )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column {
          Text(
            text = "Garantía de Frescura & Obrador",
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
            color = OnSurfaceDark
          )
          Spacer(modifier = Modifier.height(2.dp))
          Text(
            text = "Cada cofre se sella con cinta de seda y cera lacrada en nuestro taller de la Plaza Mayor. Consumo preferente dentro de los 28 días de su preparación.",
            style = MaterialTheme.typography.bodySmall,
            color = OnSurfaceVariant,
            lineHeight = 16.sp
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(20.dp))

    // 7. Persistent Action Area
    Button(
      onClick = onAutoFill,
      modifier = Modifier
        .fillMaxWidth()
        .height(44.dp)
        .testTag("btn_quick_fill_favorites"),
      colors = ButtonDefaults.buttonColors(containerColor = CreamSurfaceContainerHigh),
      shape = RoundedCornerShape(22.dp)
    ) {
      Icon(
        imageVector = Icons.Default.AutoAwesome,
        contentDescription = null,
        tint = GoldSecondary,
        modifier = Modifier.size(16.dp)
      )
      Spacer(modifier = Modifier.width(6.dp))
      Text(
        text = "Completar Automáticamente con Favoritos",
        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
        color = OnSurfaceDark
      )
    }

    Spacer(modifier = Modifier.height(10.dp))

    Button(
      onClick = onConfirmBox,
      modifier = Modifier
        .fillMaxWidth()
        .height(54.dp)
        .testTag("confirm_box_button"),
      colors = ButtonDefaults.buttonColors(containerColor = GoldSecondaryContainer),
      shape = RoundedCornerShape(27.dp),
      elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
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
            tint = OnGoldSecondaryContainer,
            modifier = Modifier.size(20.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "Confirmar Cofre",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = OnGoldSecondaryContainer
          )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(
            text = "€${String.format(java.util.Locale.US, "%.2f", uiState.selectedBoxTier.price)}",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            color = OnGoldSecondaryContainer
          )
          Spacer(modifier = Modifier.width(4.dp))
          Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = null,
            tint = OnGoldSecondaryContainer,
            modifier = Modifier.size(18.dp)
          )
        }
      }
    }
    }
  }
}
}

@Composable
private fun BoxTierCard(
  tier: BoxTier,
  isSelected: Boolean,
  onClick: () -> Unit
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(12.dp))
      .clickable(onClick = onClick)
      .testTag("tier_btn_${tier.size}"),
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(
      containerColor = if (isSelected) ChocolatePrimaryContainer else CreamSurfaceContainerLow
    ),
    border = if (isSelected) androidx.compose.foundation.BorderStroke(1.5.dp, GoldSecondaryContainer) else null
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp, horizontal = 6.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      if (tier.isFavorite) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.padding(bottom = 2.dp)
        ) {
          Icon(
            imageVector = Icons.Default.LocalFireDepartment,
            contentDescription = null,
            tint = GoldSecondaryContainer,
            modifier = Modifier.size(12.dp)
          )
          Text(
            text = "FAVORITO",
            style = MaterialTheme.typography.labelSmall.copy(
              fontSize = 8.sp,
              fontWeight = FontWeight.Bold
            ),
            color = GoldSecondaryContainer
          )
        }
      } else {
        Icon(
          imageVector = Icons.Default.Widgets,
          contentDescription = null,
          tint = if (isSelected) CreamSurfaceContainerHigh else OutlineGrey,
          modifier = Modifier
            .size(16.dp)
            .padding(bottom = 2.dp)
        )
      }

      Text(
        text = tier.subtitle,
        style = MaterialTheme.typography.titleSmall.copy(
          fontWeight = FontWeight.Bold,
          fontSize = 13.sp
        ),
        color = if (isSelected) CreamSurface else OnSurfaceDark
      )

      Text(
        text = tier.name,
        style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.sp),
        color = if (isSelected) CreamSurfaceContainerHigh else OnSurfaceVariant
      )

      Spacer(modifier = Modifier.height(4.dp))

      Text(
        text = "€${String.format(java.util.Locale.US, "%.2f", tier.price)}",
        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
        color = if (isSelected) GoldSecondaryContainer else ChocolatePrimaryContainer
      )
    }
  }
}

@Composable
private fun FlavorFilterChip(
  label: String,
  isSelected: Boolean,
  onClick: () -> Unit
) {
  Box(
    modifier = Modifier
      .clip(CircleShape)
      .background(if (isSelected) ChocolatePrimaryContainer else CreamSurfaceContainer)
      .clickable(onClick = onClick)
      .padding(horizontal = 14.dp, vertical = 7.dp)
  ) {
    Text(
      text = label,
      style = MaterialTheme.typography.labelMedium.copy(
        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
      ),
      color = if (isSelected) CreamSurface else OnSurfaceDark
    )
  }
}

@Composable
private fun FlavorRowCard(
  flavor: BonbonFlavor,
  count: Int,
  onMinus: () -> Unit,
  onPlus: () -> Unit
) {
  Card(
    modifier = Modifier.fillMaxWidth(),
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(containerColor = CreamSurfaceContainerLowest),
    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Row(
        modifier = Modifier.weight(1f),
        verticalAlignment = Alignment.CenterVertically
      ) {
        SanMiguelImage(
          model = flavor.imageUrl,
          contentDescription = flavor.name,
          contentScale = ContentScale.Crop,
          modifier = Modifier
            .size(54.dp)
            .clip(RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.width(10.dp))

        Column(modifier = Modifier.weight(1f)) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
              text = flavor.name,
              style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
              color = OnSurfaceDark,
              maxLines = 1,
              overflow = TextOverflow.Ellipsis,
              modifier = Modifier.weight(1f, fill = false)
            )

            flavor.tag?.let { tagText ->
              Spacer(modifier = Modifier.width(4.dp))
              Box(
                modifier = Modifier
                  .clip(CircleShape)
                  .background(GoldSecondaryContainer.copy(alpha = 0.4f))
                  .padding(horizontal = 6.dp, vertical = 2.dp)
              ) {
                Text(
                  text = tagText,
                  style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp),
                  color = OnGoldSecondaryContainer,
                  fontWeight = FontWeight.Bold
                )
              }
            }

            flavor.alcoholTag?.let { tagText ->
              Spacer(modifier = Modifier.width(4.dp))
              Box(
                modifier = Modifier
                  .clip(CircleShape)
                  .background(CreamSurfaceContainerHigh)
                  .padding(horizontal = 6.dp, vertical = 2.dp)
              ) {
                Text(
                  text = tagText,
                  style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp),
                  color = GoldSecondary,
                  fontWeight = FontWeight.Bold
                )
              }
            }
          }

          Text(
            text = flavor.description,
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.5.sp),
            color = OnSurfaceVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
          )

          Text(
            text = flavor.notes,
            style = MaterialTheme.typography.labelSmall.copy(
              fontSize = 10.5.sp,
              fontWeight = FontWeight.Medium
            ),
            color = GoldSecondary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
          )
        }
      }

      Spacer(modifier = Modifier.width(8.dp))

      // Stepper
      Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(
          onClick = onMinus,
          modifier = Modifier
            .size(30.dp)
            .clip(CircleShape)
            .background(CreamSurfaceContainerHigh)
            .testTag("minus_flavor_${flavor.id}")
        ) {
          Icon(
            imageVector = Icons.Default.Remove,
            contentDescription = "Restar",
            tint = OnSurfaceDark,
            modifier = Modifier.size(15.dp)
          )
        }

        Text(
          text = "$count",
          style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
          color = ChocolatePrimaryContainer,
          modifier = Modifier
            .width(26.dp)
            .testTag("count_flavor_${flavor.id}"),
          textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )

        IconButton(
          onClick = onPlus,
          modifier = Modifier
            .size(30.dp)
            .clip(CircleShape)
            .background(ChocolatePrimaryContainer)
            .testTag("plus_flavor_${flavor.id}")
        ) {
          Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Sumar",
            tint = CreamSurface,
            modifier = Modifier.size(15.dp)
          )
        }
      }
    }
  }
}
