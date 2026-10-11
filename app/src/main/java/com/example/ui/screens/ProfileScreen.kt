package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.viewmodel.ShopUiState

@Composable
fun ProfileScreen(
  uiState: ShopUiState,
  onProductClick: (Product) -> Unit,
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
    // User Profile Header Card
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .testTag("profile_card"),
      shape = RoundedCornerShape(18.dp),
      colors = CardDefaults.cardColors(containerColor = ChocolatePrimaryContainer),
      elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        SanMiguelImage(
          model = SanMiguelData.PROFILE_URL,
          contentDescription = "Marta Velázquez",
          contentScale = ContentScale.Crop,
          modifier = Modifier
            .size(68.dp)
            .clip(CircleShape)
            .border(2.dp, GoldSecondaryContainer, CircleShape)
        )

        Spacer(modifier = Modifier.width(14.dp))

        Column(modifier = Modifier.weight(1f)) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
              imageVector = Icons.Default.WorkspacePremium,
              contentDescription = null,
              tint = GoldSecondaryContainer,
              modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
              text = "CLUB CACAO · GRAN RESERVA",
              style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.8.sp
              ),
              color = GoldSecondaryContainer
            )
          }

          Spacer(modifier = Modifier.height(2.dp))

          Text(
            text = "Marta Velázquez",
            style = MaterialTheme.typography.titleLarge.copy(
              fontFamily = FontFamily.Serif,
              fontWeight = FontWeight.Bold
            ),
            color = CreamSurface
          )

          Text(
            text = "Socia nº SM-1928-884 · Madrid",
            style = MaterialTheme.typography.bodySmall,
            color = CreamSurfaceContainerHigh
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(14.dp))

    // Loyalty Points Bar
    Card(
      modifier = Modifier.fillMaxWidth(),
      shape = RoundedCornerShape(14.dp),
      colors = CardDefaults.cardColors(containerColor = GoldSecondaryContainer)
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(
            imageVector = Icons.Default.Star,
            contentDescription = null,
            tint = OnGoldSecondaryContainer,
            modifier = Modifier.size(22.dp)
          )
          Spacer(modifier = Modifier.width(10.dp))
          Column {
            Text(
              text = "1.450 Puntos de Cacao",
              style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
              color = OnGoldSecondaryContainer
            )
            Text(
              text = "Próxima cata exclusiva en 50 puntos",
              style = MaterialTheme.typography.bodySmall,
              color = OnGoldSecondaryContainer.copy(alpha = 0.85f)
            )
          }
        }
        Icon(
          imageVector = Icons.Default.ChevronRight,
          contentDescription = null,
          tint = OnGoldSecondaryContainer
        )
      }
    }

    Spacer(modifier = Modifier.height(20.dp))

    // Favoritos Guardados
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
          imageVector = Icons.Default.Favorite,
          contentDescription = null,
          tint = ErrorRed,
          modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
          text = "Mis Joyas Favoritas",
          style = MaterialTheme.typography.titleMedium.copy(
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold
          ),
          color = ChocolatePrimaryContainer
        )
      }
      Text(
        text = "${uiState.favorites.size} guardados",
        style = MaterialTheme.typography.labelSmall,
        color = OnSurfaceVariant
      )
    }

    Spacer(modifier = Modifier.height(10.dp))

    val favoriteProducts = SanMiguelData.products.filter { uiState.favorites.contains(it.id) }
    if (favoriteProducts.isEmpty()) {
      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = CreamSurfaceContainerLowest)
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Text(
            text = "No tienes favoritos guardados aún",
            style = MaterialTheme.typography.bodyMedium,
            color = OnSurfaceVariant
          )
        }
      }
    } else {
      Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        favoriteProducts.forEach { product ->
          Card(
            modifier = Modifier
              .fillMaxWidth()
              .clickable { onProductClick(product) },
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = CreamSurfaceContainerLowest)
          ) {
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              SanMiguelImage(
                model = product.imageUrl,
                contentDescription = product.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                  .size(50.dp)
                  .clip(RoundedCornerShape(8.dp))
              )
              Spacer(modifier = Modifier.width(12.dp))
              Column(modifier = Modifier.weight(1f)) {
                Text(
                  text = product.name,
                  style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                  color = ChocolatePrimaryContainer
                )
                Text(
                  text = product.subtitle,
                  style = MaterialTheme.typography.bodySmall,
                  color = OnSurfaceVariant
                )
              }
              Text(
                text = "€${String.format(java.util.Locale.US, "%.2f", product.price)}",
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                color = GoldSecondary
              )
            }
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(20.dp))

    // Preferences & Settings List
    Text(
      text = "PREFERENCIAS DE DEGUSTACIÓN",
      style = MaterialTheme.typography.labelSmall.copy(
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.sp
      ),
      color = OnSurfaceVariant
    )

    Spacer(modifier = Modifier.height(8.dp))

    Card(
      modifier = Modifier.fillMaxWidth(),
      shape = RoundedCornerShape(14.dp),
      colors = CardDefaults.cardColors(containerColor = CreamSurfaceContainerLowest)
    ) {
      Column {
        PreferenceRow(
          icon = Icons.Default.Place,
          title = "Boutique Habitual",
          subtitle = "Boutique Serrano (Calle Serrano, Madrid)"
        )
        Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(CreamSurfaceContainerHigh))
        PreferenceRow(
          icon = Icons.Default.LocalShipping,
          title = "Modo de Entrega Fina",
          subtitle = "Transporte Refrigerado Climatizado a 16°C"
        )
        Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(CreamSurfaceContainerHigh))
        PreferenceRow(
          icon = Icons.Default.CardGiftcard,
          title = "Empaquetado de Cortesía",
          subtitle = "Caja precintada con cera lacrada y dedicatoria"
        )
      }
    }

    Spacer(modifier = Modifier.height(24.dp))

    // Atelier heritage seal
    Column(
      modifier = Modifier.fillMaxWidth(),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(
        text = "Confitería San Miguel · Taller Plaza Mayor, Madrid",
        style = MaterialTheme.typography.labelSmall,
        color = OutlineGrey
      )
      Text(
        text = "Maestría en Bombones y Trufas desde 1928",
        style = MaterialTheme.typography.bodySmall.copy(fontFamily = FontFamily.Serif),
        color = GoldSecondary
      )
    }
    }
  }
}
}

@Composable
private fun PreferenceRow(
  icon: androidx.compose.ui.graphics.vector.ImageVector,
  title: String,
  subtitle: String
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(14.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Box(
      modifier = Modifier
        .size(36.dp)
        .background(CreamSurfaceContainer, CircleShape),
      contentAlignment = Alignment.Center
    ) {
      Icon(icon, contentDescription = null, tint = GoldSecondary, modifier = Modifier.size(18.dp))
    }
    Spacer(modifier = Modifier.width(12.dp))
    Column(modifier = Modifier.weight(1f)) {
      Text(
        text = title,
        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
        color = OnSurfaceDark
      )
      Text(
        text = subtitle,
        style = MaterialTheme.typography.bodySmall,
        color = OnSurfaceVariant
      )
    }
    Icon(
      imageVector = Icons.Default.ChevronRight,
      contentDescription = null,
      tint = OutlineGrey,
      modifier = Modifier.size(18.dp)
    )
  }
}
