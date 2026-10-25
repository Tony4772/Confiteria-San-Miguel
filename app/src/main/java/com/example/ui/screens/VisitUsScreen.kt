package com.example.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Directions
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Domain
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.NearMe
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.SanMiguelData
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
import com.example.viewmodel.ShopUiState

@Composable
fun VisitUsScreen(
  uiState: ShopUiState,
  onWhatsAppClick: (String) -> Unit
) {
  val context = LocalContext.current
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
              text = uiState.storefront.visitHeaderTag.uppercase(),
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.2.sp
              ),
              color = OnSurfaceVariant
            )
          }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Title & Description
        Text(
          text = uiState.storefront.visitHeaderTitle,
          style = MaterialTheme.typography.headlineMedium.copy(
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp
          ),
          color = ChocolatePrimaryContainer,
          textAlign = TextAlign.Center,
          modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
          text = uiState.storefront.visitHeaderDesc,
          style = MaterialTheme.typography.bodyMedium,
          color = OnSurfaceVariant,
          textAlign = TextAlign.Center,
          modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(18.dp))

        // Ambient Photo Card
        Card(
          modifier = Modifier.fillMaxWidth(),
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors(containerColor = ChocolatePrimaryContainer),
          elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .height(200.dp)
          ) {
            val ambientImg = uiState.storefront.brandAmbientImageUrl.ifBlank { SanMiguelData.AMBIENT_IMAGE_URL }
            SanMiguelImage(
              model = ambientImg,
              contentDescription = "Atelier Fernandina de Jagua",
              contentScale = ContentScale.Crop,
              modifier = Modifier.fillMaxSize()
            )
            Box(
              modifier = Modifier
                .fillMaxSize()
                .background(
                  androidx.compose.ui.graphics.Brush.verticalGradient(
                    colors = listOf(
                      Color.Transparent,
                      ChocolatePrimaryContainer.copy(alpha = 0.85f)
                    ),
                    startY = 80f
                  )
                )
            )
            Column(
              modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
            ) {
              Text(
                text = "TALLER ARTESANAL",
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, letterSpacing = 1.sp),
                color = GoldSecondaryContainer
              )
              Text(
                text = uiState.storefront.storeAddress,
                style = MaterialTheme.typography.titleMedium.copy(
                  fontFamily = FontFamily.Serif,
                  fontWeight = FontWeight.Bold
                ),
                color = CreamSurface
              )
              Text(
                text = uiState.storefront.brandDistrict,
                style = MaterialTheme.typography.bodySmall,
                color = CreamSurface.copy(alpha = 0.85f)
              )
            }
          }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Address & Company Info Card
        Card(
          modifier = Modifier.fillMaxWidth(),
          shape = RoundedCornerShape(14.dp),
          colors = CardDefaults.cardColors(containerColor = CreamSurfaceContainerLowest),
          elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(
                text = "UBICACIÓN CENTRAL",
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, letterSpacing = 1.sp),
                color = GoldSecondary
              )
              Icon(Icons.Default.LocationOn, contentDescription = null, tint = GoldSecondary, modifier = Modifier.size(18.dp))
            }

            Text(
              text = uiState.storefront.storeAddress,
              style = MaterialTheme.typography.titleMedium.copy(
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold
              ),
              color = ChocolatePrimaryContainer
            )

            Text(
              text = uiState.storefront.brandDistrict,
              style = MaterialTheme.typography.bodyMedium,
              color = OnSurfaceDark
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(Icons.Default.Domain, contentDescription = null, tint = OutlineGrey, modifier = Modifier.size(15.dp))
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = "${uiState.storefront.brandCompanyName} • ${uiState.storefront.brandZone}",
                style = MaterialTheme.typography.labelSmall,
                color = OnSurfaceVariant
              )
            }
          }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Horarios Card
        Card(
          modifier = Modifier.fillMaxWidth(),
          shape = RoundedCornerShape(14.dp),
          colors = CardDefaults.cardColors(containerColor = CreamSurfaceContainerLowest),
          elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(
                text = uiState.storefront.scheduleTitle.uppercase(),
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, letterSpacing = 1.sp),
                color = GoldSecondary
              )
              Icon(Icons.Default.AccessTime, contentDescription = null, tint = GoldSecondary, modifier = Modifier.size(18.dp))
            }

            Text(
              text = uiState.storefront.schedulePlaceholder.ifBlank { uiState.storefront.storeHours },
              style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 22.sp),
              color = OnSurfaceDark,
              fontWeight = FontWeight.Medium
            )

            if (uiState.storefront.scheduleNotice.isNotBlank()) {
              Text(
                text = uiState.storefront.scheduleNotice,
                style = MaterialTheme.typography.bodySmall,
                color = OnSurfaceVariant
              )
            }
          }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Referencias de Llegada (3 cards like in the web)
        Text(
          text = "Referencias de Llegada",
          style = MaterialTheme.typography.titleMedium.copy(
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold
          ),
          color = ChocolatePrimaryContainer
        )

        Spacer(modifier = Modifier.height(10.dp))

        ReferenceItem(
          title = uiState.storefront.ref1Title,
          subtitle = uiState.storefront.ref1Subtitle,
          desc = uiState.storefront.ref1Desc
        )

        Spacer(modifier = Modifier.height(10.dp))

        ReferenceItem(
          title = uiState.storefront.ref2Title,
          subtitle = uiState.storefront.ref2Subtitle,
          desc = uiState.storefront.ref2Desc
        )

        Spacer(modifier = Modifier.height(10.dp))

        ReferenceItem(
          title = uiState.storefront.ref3Title,
          subtitle = uiState.storefront.ref3Subtitle,
          desc = uiState.storefront.ref3Desc
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Navigation Action Buttons
        Text(
          text = "Cómo Llegar y Contacto",
          style = MaterialTheme.typography.titleMedium.copy(
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold
          ),
          color = ChocolatePrimaryContainer
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          Button(
            onClick = {
              openMap(context, uiState.storefront.storeAddress)
            },
            colors = ButtonDefaults.buttonColors(containerColor = ChocolatePrimaryContainer),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.weight(1f)
          ) {
            Icon(Icons.Default.Directions, contentDescription = null, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text("Google Maps", fontSize = 12.sp)
          }

          Button(
            onClick = {
              openWaze(context, uiState.storefront.storeAddress)
            },
            colors = ButtonDefaults.buttonColors(containerColor = GoldSecondary),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.weight(1f)
          ) {
            Icon(Icons.Default.Navigation, contentDescription = null, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text("Waze", fontSize = 12.sp)
          }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // WhatsApp CTA Card
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
              text = uiState.storefront.ctaTitle,
              style = MaterialTheme.typography.titleSmall.copy(
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold
              ),
              color = ChocolatePrimaryContainer
            )
            Text(
              text = uiState.storefront.ctaDesc,
              style = MaterialTheme.typography.bodySmall,
              color = OnSurfaceDark
            )
            Spacer(modifier = Modifier.height(4.dp))
            Button(
              onClick = {
                val msg = "Hola Fernandina de Jagua, deseo coordinar una visita y pedido en su taller de San Miguel."
                onWhatsAppClick(msg)
              },
              colors = ButtonDefaults.buttonColors(containerColor = ChocolatePrimaryContainer),
              shape = RoundedCornerShape(20.dp)
            ) {
              Icon(Icons.AutoMirrored.Filled.Chat, contentDescription = null, tint = CreamSurface, modifier = Modifier.size(16.dp))
              Spacer(modifier = Modifier.width(8.dp))
              Text("Coordinar por WhatsApp", color = CreamSurface, fontWeight = FontWeight.Bold)
            }
          }
        }
      }
    }
  }
}

@Composable
private fun ReferenceItem(
  title: String,
  subtitle: String,
  desc: String
) {
  Card(
    modifier = Modifier.fillMaxWidth(),
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(containerColor = CreamSurfaceContainerLow)
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(14.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Text(
          text = title,
          style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
          color = ChocolatePrimaryContainer
        )
        Text(
          text = subtitle,
          style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Medium),
          color = GoldSecondary
        )
      }
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = desc,
        style = MaterialTheme.typography.bodySmall,
        color = OnSurfaceVariant
      )
    }
  }
}

private fun openMap(context: Context, address: String) {
  try {
    val uri = Uri.parse("geo:0,0?q=" + Uri.encode(address))
    val mapIntent = Intent(Intent.ACTION_VIEW, uri)
    context.startActivity(mapIntent)
  } catch (_: Exception) {
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://maps.google.com/?q=" + Uri.encode(address)))
    context.startActivity(browserIntent)
  }
}

private fun openWaze(context: Context, address: String) {
  try {
    val wazeUri = Uri.parse("https://waze.com/ul?q=" + Uri.encode(address))
    val intent = Intent(Intent.ACTION_VIEW, wazeUri)
    context.startActivity(intent)
  } catch (_: Exception) {
    openMap(context, address)
  }
}
