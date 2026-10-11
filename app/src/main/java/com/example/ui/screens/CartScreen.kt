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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.filled.Verified
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
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.CartItem
import com.example.model.DeliveryMode
import com.example.ui.components.SanMiguelImage
import com.example.ui.theme.BizumTeal
import com.example.ui.theme.ChocolatePrimary
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
import com.example.ui.theme.OnSecondaryFixed
import com.example.ui.theme.OnSurfaceDark
import com.example.ui.theme.OnSurfaceVariant
import com.example.ui.theme.OutlineGrey
import com.example.ui.theme.SecondaryFixed
import com.example.ui.theme.SecondaryFixedDim
import com.example.viewmodel.ShopUiState

@Composable
fun CartScreen(
  uiState: ShopUiState,
  onUpdateQuantity: (String, Int) -> Unit,
  onRemoveItem: (String) -> Unit,
  onSelectDeliveryMode: (DeliveryMode) -> Unit,
  onSelectTimeSlot: (String) -> Unit,
  onToggleGiftWrap: (Boolean) -> Unit,
  onUpdateGiftMessage: (String) -> Unit,
  onCheckout: () -> Unit,
  onNavigateToBuilder: () -> Unit
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
    // 1. Header
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.Bottom
    ) {
      Text(
        text = "Mi Bolsa Gourmet",
        style = MaterialTheme.typography.headlineMedium.copy(
          fontFamily = FontFamily.Serif,
          fontWeight = FontWeight.Bold,
          fontSize = 26.sp
        ),
        color = ChocolatePrimaryContainer
      )
      Text(
        text = "${uiState.cartItems.sumOf { it.quantity }} creaciones",
        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
        color = OnSurfaceVariant
      )
    }

    Spacer(modifier = Modifier.height(14.dp))

    // 2. Free Shipping Delight Milestone Card
    Card(
      modifier = Modifier.fillMaxWidth(),
      shape = RoundedCornerShape(14.dp),
      colors = CardDefaults.cardColors(containerColor = SecondaryFixed.copy(alpha = 0.5f))
    ) {
      Column(modifier = Modifier.padding(14.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(
            imageVector = Icons.Default.AcUnit,
            contentDescription = null,
            tint = GoldSecondary,
            modifier = Modifier.size(20.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          if (uiState.remainingForFreeShipping > 0.0) {
            Text(
              text = "¡Añade €${String.format(java.util.Locale.US, "%.2f", uiState.remainingForFreeShipping)} más para Envío Climatizado Gratis!",
              style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium),
              color = OnSecondaryFixed
            )
          } else {
            Text(
              text = "¡Has alcanzado el Envío Climatizado Gratuito!",
              style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
              color = OnSecondaryFixed
            )
          }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Progress bar
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .height(8.dp)
            .clip(CircleShape)
            .background(CreamSurfaceContainerHigh)
        ) {
          Box(
            modifier = Modifier
              .fillMaxWidth(uiState.freeShippingProgress)
              .height(8.dp)
              .clip(CircleShape)
              .background(GoldSecondary)
          )
        }

        Spacer(modifier = Modifier.height(6.dp))

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Text(
            text = "€${String.format(java.util.Locale.US, "%.2f", uiState.cartSubtotal)} actual",
            style = MaterialTheme.typography.labelSmall,
            color = OnSurfaceVariant
          )
          Text(
            text = "Meta: €${String.format(java.util.Locale.US, "%.2f", uiState.freeShippingGoal)}",
            style = MaterialTheme.typography.labelSmall,
            color = OnSurfaceVariant
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(18.dp))

    // 3. Items Cart List
    if (uiState.cartItems.isEmpty()) {
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
            text = "Tu bolsa está vacía",
            style = MaterialTheme.typography.titleMedium.copy(fontFamily = FontFamily.Serif),
            color = ChocolatePrimaryContainer
          )
          Spacer(modifier = Modifier.height(8.dp))
          Button(
            onClick = onNavigateToBuilder,
            colors = ButtonDefaults.buttonColors(containerColor = ChocolatePrimaryContainer),
            shape = RoundedCornerShape(20.dp)
          ) {
            Text("Explorar Obrador", color = CreamSurface)
          }
        }
      }
    } else {
      Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(14.dp)
      ) {
        uiState.cartItems.forEach { item ->
          CartItemCard(
            item = item,
            onMinus = { onUpdateQuantity(item.id, -1) },
            onPlus = { onUpdateQuantity(item.id, 1) },
            onDelete = { onRemoveItem(item.id) }
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(24.dp))

    // 4. Modalidad de Entrega Fina
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = "Modalidad de Entrega Fina",
        style = MaterialTheme.typography.titleMedium.copy(
          fontWeight = FontWeight.Bold,
          fontSize = 17.sp
        ),
        color = ChocolatePrimaryContainer
      )
      Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
          imageVector = Icons.Default.Shield,
          contentDescription = null,
          tint = GoldSecondary,
          modifier = Modifier.size(14.dp)
        )
        Spacer(modifier = Modifier.width(3.dp))
        Text(
          text = "CALIDAD GARANTIZADA",
          style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
          color = GoldSecondary
        )
      }
    }

    Spacer(modifier = Modifier.height(10.dp))

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      // Option A: Refrigerated
      DeliveryOptionCard(
        title = "Envío Refrigerado",
        subtitle = "Control térmico a 16°C",
        highlight = "Sin derretimiento",
        icon = Icons.Default.LocalShipping,
        isSelected = uiState.deliveryMode == DeliveryMode.REFRIGERATED,
        modifier = Modifier.weight(1f),
        onClick = { onSelectDeliveryMode(DeliveryMode.REFRIGERATED) }
      )

      // Option B: Boutique Serrano
      DeliveryOptionCard(
        title = "Boutique Serrano",
        subtitle = "Calle Serrano, Madrid",
        highlight = "Listo en 2h",
        icon = Icons.Default.Storefront,
        isSelected = uiState.deliveryMode == DeliveryMode.PICKUP_SERRANO,
        modifier = Modifier.weight(1f),
        onClick = { onSelectDeliveryMode(DeliveryMode.PICKUP_SERRANO) }
      )
    }

    Spacer(modifier = Modifier.height(12.dp))

    // Schedule Time Slot
    Card(
      modifier = Modifier.fillMaxWidth(),
      shape = RoundedCornerShape(14.dp),
      colors = CardDefaults.cardColors(containerColor = CreamSurfaceContainerLow)
    ) {
      Column(modifier = Modifier.padding(14.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(
            imageVector = Icons.Default.CalendarToday,
            contentDescription = null,
            tint = GoldSecondary,
            modifier = Modifier.size(16.dp)
          )
          Spacer(modifier = Modifier.width(6.dp))
          Text(
            text = "Programar Momento de Entrega",
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
            color = ChocolatePrimaryContainer
          )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
          modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          TimeSlotPill(
            label = "Mañana (10:00 - 14:00)",
            isSelected = uiState.selectedTimeSlot == "Mañana (10:00 - 14:00)",
            showCheck = true,
            onClick = { onSelectTimeSlot("Mañana (10:00 - 14:00)") }
          )
          TimeSlotPill(
            label = "Tarde (16:00 - 20:00)",
            isSelected = uiState.selectedTimeSlot == "Tarde (16:00 - 20:00)",
            showCheck = false,
            onClick = { onSelectTimeSlot("Tarde (16:00 - 20:00)") }
          )
          TimeSlotPill(
            label = "Fecha Especial",
            isSelected = uiState.selectedTimeSlot == "Fecha Especial",
            icon = Icons.Default.Cake,
            showCheck = false,
            onClick = { onSelectTimeSlot("Fecha Especial") }
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(20.dp))

    // 5. Gift Experience Packaging Option
    Card(
      modifier = Modifier.fillMaxWidth(),
      shape = RoundedCornerShape(14.dp),
      colors = CardDefaults.cardColors(containerColor = CreamSurfaceContainerLowest),
      elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
      Column(modifier = Modifier.padding(14.dp)) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          verticalAlignment = Alignment.Top
        ) {
          Checkbox(
            checked = uiState.giftWrapEnabled,
            onCheckedChange = onToggleGiftWrap,
            colors = CheckboxDefaults.colors(
              checkedColor = GoldSecondary,
              uncheckedColor = OutlineGrey
            ),
            modifier = Modifier.testTag("gift_toggle_checkbox")
          )

          Spacer(modifier = Modifier.width(4.dp))

          Column(modifier = Modifier.weight(1f)) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              Text(
                text = "Preparar para Regalo de Lujo",
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                color = ChocolatePrimaryContainer
              )
              Text(
                text = "+ €3.00",
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                color = GoldSecondary
              )
            }

            Spacer(modifier = Modifier.height(2.dp))

            Text(
              text = "Lazo de satén dorado hecho a mano, caja precintada con lacre de San Miguel y dedicatoria caligráfica.",
              style = MaterialTheme.typography.bodySmall,
              color = OnSurfaceVariant,
              lineHeight = 16.sp
            )
          }
        }

        AnimatedVisibility(visible = uiState.giftWrapEnabled) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(top = 12.dp)
              .clip(RoundedCornerShape(10.dp))
              .background(CreamSurfaceContainerLow)
              .padding(12.dp)
          ) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                  imageVector = Icons.Default.EditNote,
                  contentDescription = null,
                  tint = GoldSecondary,
                  modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                  text = "Mensaje para la tarjeta personalizada",
                  style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                  color = ChocolatePrimaryContainer
                )
              }
              Text(
                text = "${uiState.giftCardMessage.length}/200",
                style = MaterialTheme.typography.labelSmall,
                color = OnSurfaceVariant
              )
            }

            Spacer(modifier = Modifier.height(6.dp))

            OutlinedTextField(
              value = uiState.giftCardMessage,
              onValueChange = { if (it.length <= 200) onUpdateGiftMessage(it) },
              modifier = Modifier
                .fillMaxWidth()
                .testTag("gift_card_message_input"),
              maxLines = 3,
              colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = CreamSurfaceContainerLowest,
                unfocusedContainerColor = CreamSurfaceContainerLowest,
                focusedBorderColor = GoldSecondary,
                unfocusedBorderColor = CreamSurfaceContainerHigh
              ),
              shape = RoundedCornerShape(8.dp)
            )
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(20.dp))

    // 6. Express Payment Shortcuts (Google Pay & Bizum)
    Text(
      text = "PAGO RÁPIDO EN 1-TOQUE",
      style = MaterialTheme.typography.labelSmall.copy(
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.sp
      ),
      color = OnSurfaceVariant,
      modifier = Modifier.align(Alignment.CenterHorizontally)
    )

    Spacer(modifier = Modifier.height(10.dp))

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      Button(
        onClick = onCheckout,
        modifier = Modifier
          .weight(1f)
          .height(48.dp)
          .testTag("gpay_button"),
        colors = ButtonDefaults.buttonColors(containerColor = ChocolatePrimary),
        shape = RoundedCornerShape(24.dp)
      ) {
        Text(
          text = "G Pay",
          style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
          color = Color.White
        )
      }

      Button(
        onClick = onCheckout,
        modifier = Modifier
          .weight(1f)
          .height(48.dp)
          .testTag("bizum_button"),
        colors = ButtonDefaults.buttonColors(containerColor = BizumTeal),
        shape = RoundedCornerShape(24.dp)
      ) {
        Text(
          text = "bizum",
          style = MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp
          ),
          color = Color.White
        )
      }
    }

    Spacer(modifier = Modifier.height(12.dp))

    Row(
      modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Box(
        modifier = Modifier
          .weight(1f)
          .height(1.dp)
          .background(CreamSurfaceContainerHigh)
      )
      Text(
        text = " O CONTINUAR CON RESUMEN ",
        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
        color = OutlineGrey
      )
      Box(
        modifier = Modifier
          .weight(1f)
          .height(1.dp)
          .background(CreamSurfaceContainerHigh)
      )
    }

    Spacer(modifier = Modifier.height(14.dp))

    // 7. Price Summary Breakdown
    Card(
      modifier = Modifier.fillMaxWidth(),
      shape = RoundedCornerShape(14.dp),
      colors = CardDefaults.cardColors(containerColor = CreamSurfaceContainerLowest),
      elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
      Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Text(text = "Subtotal confitería", color = OnSurfaceVariant, style = MaterialTheme.typography.bodyMedium)
          Text(
            text = "€${String.format(java.util.Locale.US, "%.2f", uiState.cartSubtotal)}",
            fontWeight = FontWeight.SemiBold,
            color = OnSurfaceDark
          )
        }

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Envío Especial Climatizado (16°C)", color = OnSurfaceVariant, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.width(4.dp))
            Icon(Icons.Default.Info, contentDescription = null, tint = GoldSecondary, modifier = Modifier.size(14.dp))
          }
          Text(text = "Gratis", fontWeight = FontWeight.Bold, color = GoldSecondary)
        }

        if (uiState.giftWrapEnabled) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Text(text = "Empaque de Lujo y Tarjeta Artesanal", color = OnSurfaceVariant, style = MaterialTheme.typography.bodyMedium)
            Text(text = "€3.00", fontWeight = FontWeight.SemiBold, color = OnSurfaceDark)
          }
        }

        Box(
          modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(CreamSurfaceContainer)
        )

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column {
            Text(
              text = "Importe Total",
              style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
              color = ChocolatePrimaryContainer
            )
            Text(
              text = "IVA e impuestos incluidos",
              style = MaterialTheme.typography.labelSmall,
              color = OnSurfaceVariant
            )
          }

          Text(
            text = "€${String.format(java.util.Locale.US, "%.2f", uiState.cartTotal)}",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            color = ChocolatePrimaryContainer
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(14.dp))

    // Assurance Badges
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceEvenly,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(Icons.Default.Verified, contentDescription = null, tint = GoldSecondary, modifier = Modifier.size(15.dp))
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = "Maestría 1924", style = MaterialTheme.typography.labelSmall, color = OnSurfaceVariant)
      }

      Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(Icons.Default.Thermostat, contentDescription = null, tint = GoldSecondary, modifier = Modifier.size(15.dp))
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = "Garantía Frío", style = MaterialTheme.typography.labelSmall, color = OnSurfaceVariant)
      }

      Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(Icons.Default.Lock, contentDescription = null, tint = GoldSecondary, modifier = Modifier.size(15.dp))
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = "Pago Blindado SSL", style = MaterialTheme.typography.labelSmall, color = OnSurfaceVariant)
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Primary CTA Button
    Button(
      onClick = onCheckout,
      modifier = Modifier
        .fillMaxWidth()
        .height(56.dp)
        .testTag("checkout_order_cta"),
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
          Icon(Icons.Default.Lock, contentDescription = null, tint = GoldSecondary, modifier = Modifier.size(20.dp))
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "Tramitar Pedido",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = CreamSurface
          )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(
            text = "€${String.format(java.util.Locale.US, "%.2f", uiState.cartTotal)}",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            color = GoldSecondary
          )
          Spacer(modifier = Modifier.width(6.dp))
          Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, tint = CreamSurface, modifier = Modifier.size(18.dp))
        }
      }
    }
    }
  }
}
}

@Composable
private fun CartItemCard(
  item: CartItem,
  onMinus: () -> Unit,
  onPlus: () -> Unit,
  onDelete: () -> Unit
) {
  var isExpanded by remember { mutableStateOf(false) }

  Card(
    modifier = Modifier
      .fillMaxWidth()
      .testTag("cart_item_${item.id}"),
    shape = RoundedCornerShape(14.dp),
    colors = CardDefaults.cardColors(containerColor = CreamSurfaceContainerLowest),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
  ) {
    Column(modifier = Modifier.padding(12.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
      ) {
        SanMiguelImage(
          model = item.imageUrl,
          contentDescription = item.title,
          contentScale = ContentScale.Crop,
          modifier = Modifier
            .size(76.dp)
            .clip(RoundedCornerShape(10.dp))
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
          ) {
            Text(
              text = item.title,
              style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
              color = ChocolatePrimaryContainer,
              modifier = Modifier.weight(1f)
            )
            IconButton(
              onClick = onDelete,
              modifier = Modifier
                .size(28.dp)
                .testTag("delete_${item.id}")
            ) {
              Icon(
                imageVector = Icons.Default.DeleteOutline,
                contentDescription = "Eliminar",
                tint = OutlineGrey,
                modifier = Modifier.size(18.dp)
              )
            }
          }

          Text(
            text = item.subtitle,
            style = MaterialTheme.typography.bodySmall,
            color = OnSurfaceVariant
          )

          Spacer(modifier = Modifier.height(8.dp))

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            // Stepper
            Row(
              modifier = Modifier
                .clip(CircleShape)
                .background(CreamSurfaceContainerLow)
                .padding(horizontal = 6.dp, vertical = 2.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(
                text = "−",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = OnSurfaceVariant,
                modifier = Modifier
                  .clickable { onMinus() }
                  .padding(horizontal = 6.dp)
                  .testTag("minus_${item.id}")
              )
              Text(
                text = "${item.quantity}",
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                color = ChocolatePrimaryContainer,
                modifier = Modifier.padding(horizontal = 4.dp)
              )
              Text(
                text = "+",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = OnSurfaceVariant,
                modifier = Modifier
                  .clickable { onPlus() }
                  .padding(horizontal = 6.dp)
                  .testTag("plus_${item.id}")
              )
            }

            Text(
              text = "€${String.format(java.util.Locale.US, "%.2f", item.totalPrice)}",
              style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
              color = ChocolatePrimaryContainer
            )
          }
        }
      }

      // Expandable flavors accordion if custom box
      if (item.customFlavors.isNotEmpty()) {
        Spacer(modifier = Modifier.height(10.dp))
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(CreamSurfaceContainerLow)
            .clickable { isExpanded = !isExpanded }
            .padding(10.dp)
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(
                imageVector = Icons.Default.MenuBook,
                contentDescription = null,
                tint = GoldSecondary,
                modifier = Modifier.size(15.dp)
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = "Ver sabores elegidos (${item.customFlavors.sumOf { it.second }} uds)",
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                color = GoldSecondary
              )
            }

            Icon(
              imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
              contentDescription = null,
              tint = GoldSecondary,
              modifier = Modifier.size(18.dp)
            )
          }

          AnimatedVisibility(visible = isExpanded) {
            Column(
              modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
              verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
              item.customFlavors.forEach { (flavorName, count) ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                  Box(
                    modifier = Modifier
                      .size(5.dp)
                      .background(GoldSecondary, CircleShape)
                  )
                  Spacer(modifier = Modifier.width(6.dp))
                  Text(
                    text = "${count}x $flavorName",
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
                    color = OnSurfaceVariant
                  )
                }
              }
            }
          }
        }
      }
    }
  }
}

@Composable
private fun DeliveryOptionCard(
  title: String,
  subtitle: String,
  highlight: String,
  icon: androidx.compose.ui.graphics.vector.ImageVector,
  isSelected: Boolean,
  modifier: Modifier = Modifier,
  onClick: () -> Unit
) {
  Card(
    modifier = modifier
      .clip(RoundedCornerShape(12.dp))
      .clickable(onClick = onClick)
      .testTag("delivery_${title.take(5)}"),
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(
      containerColor = if (isSelected) ChocolatePrimaryContainer else CreamSurfaceContainerLowest
    ),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
  ) {
    Column(modifier = Modifier.padding(12.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Box(
          modifier = Modifier
            .size(32.dp)
            .background(
              if (isSelected) GoldSecondaryContainer.copy(alpha = 0.3f) else CreamSurfaceContainer,
              CircleShape
            ),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (isSelected) GoldSecondaryContainer else OnSurfaceVariant,
            modifier = Modifier.size(18.dp)
          )
        }

        RadioButton(
          selected = isSelected,
          onClick = onClick,
          colors = RadioButtonDefaults.colors(
            selectedColor = GoldSecondaryContainer,
            unselectedColor = OutlineGrey
          )
        )
      }

      Spacer(modifier = Modifier.height(8.dp))

      Text(
        text = title,
        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
        color = if (isSelected) CreamSurface else OnSurfaceDark
      )

      Text(
        text = subtitle,
        style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
        color = if (isSelected) CreamSurfaceContainerHigh else OnSurfaceVariant
      )

      Spacer(modifier = Modifier.height(2.dp))

      Text(
        text = highlight,
        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
        color = GoldSecondary
      )
    }
  }
}

@Composable
private fun TimeSlotPill(
  label: String,
  isSelected: Boolean,
  icon: androidx.compose.ui.graphics.vector.ImageVector? = null,
  showCheck: Boolean = false,
  onClick: () -> Unit
) {
  Row(
    modifier = Modifier
      .clip(CircleShape)
      .background(if (isSelected) ChocolatePrimaryContainer else CreamSurfaceContainerLowest)
      .clickable(onClick = onClick)
      .padding(horizontal = 12.dp, vertical = 7.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    icon?.let {
      Icon(it, contentDescription = null, tint = if (isSelected) CreamSurface else OnSurfaceVariant, modifier = Modifier.size(14.dp))
      Spacer(modifier = Modifier.width(4.dp))
    }
    Text(
      text = label,
      style = MaterialTheme.typography.labelMedium.copy(
        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
      ),
      color = if (isSelected) CreamSurface else OnSurfaceDark
    )
    if (showCheck && isSelected) {
      Spacer(modifier = Modifier.width(4.dp))
      Icon(
        imageVector = Icons.Default.CheckCircle,
        contentDescription = null,
        tint = GoldSecondary,
        modifier = Modifier.size(14.dp)
      )
    }
  }
}
