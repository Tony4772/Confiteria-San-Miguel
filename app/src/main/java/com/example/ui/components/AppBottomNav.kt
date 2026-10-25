package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.ChocolatePrimaryContainer
import com.example.ui.theme.CreamSurfaceContainerLowest
import com.example.ui.theme.GoldSecondary
import com.example.ui.theme.GoldSecondaryContainer
import com.example.ui.theme.OnGoldSecondaryContainer
import com.example.ui.theme.OnSurfaceVariant
import com.example.viewmodel.CurrentScreen

@Composable
fun AppBottomNav(
  currentScreen: CurrentScreen,
  cartBadgeCount: Int,
  onNavigate: (CurrentScreen) -> Unit
) {
  Surface(
    modifier = Modifier.fillMaxWidth(),
    color = CreamSurfaceContainerLowest.copy(alpha = 0.96f),
    shadowElevation = 8.dp
  ) {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .navigationBarsPadding(),
      contentAlignment = Alignment.Center
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 640.dp)
          .height(64.dp)
          .padding(horizontal = 4.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
      ) {
        BottomNavItem(
          label = "Inicio",
          selectedIcon = Icons.Filled.Home,
          unselectedIcon = Icons.Outlined.Home,
          isSelected = currentScreen is CurrentScreen.Home,
          testTag = "tab_inicio",
          onClick = { onNavigate(CurrentScreen.Home) }
        )

        BottomNavItem(
          label = "Catálogo",
          selectedIcon = Icons.Filled.Storefront,
          unselectedIcon = Icons.Outlined.Storefront,
          isSelected = currentScreen is CurrentScreen.Catalog,
          testTag = "tab_catalogo",
          onClick = { onNavigate(CurrentScreen.Catalog) }
        )

        BottomNavItem(
          label = "Visítanos",
          selectedIcon = Icons.Filled.Place,
          unselectedIcon = Icons.Outlined.Place,
          isSelected = currentScreen is CurrentScreen.VisitUs,
          testTag = "tab_visitanos",
          onClick = { onNavigate(CurrentScreen.VisitUs) }
        )

        BottomNavItem(
          label = "Carrito",
          selectedIcon = Icons.Filled.ShoppingBag,
          unselectedIcon = Icons.Outlined.ShoppingBag,
          isSelected = currentScreen is CurrentScreen.Cart,
          badgeCount = cartBadgeCount,
          testTag = "tab_bolsa",
          onClick = { onNavigate(CurrentScreen.Cart) }
        )

        BottomNavItem(
          label = "Cuenta",
          selectedIcon = Icons.Filled.Person,
          unselectedIcon = Icons.Outlined.Person,
          isSelected = currentScreen is CurrentScreen.Profile || currentScreen is CurrentScreen.ShopAdmin,
          testTag = "tab_perfil",
          onClick = { onNavigate(CurrentScreen.Profile) }
        )
      }
    }
  }
}

@Composable
private fun BottomNavItem(
  label: String,
  selectedIcon: ImageVector,
  unselectedIcon: ImageVector,
  isSelected: Boolean,
  badgeCount: Int = 0,
  testTag: String,
  onClick: () -> Unit
) {
  val interactionSource = remember { MutableInteractionSource() }

  Column(
    modifier = Modifier
      .width(66.dp)
      .height(56.dp)
      .clickable(
        interactionSource = interactionSource,
        indication = null,
        onClick = onClick
      )
      .testTag(testTag),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    // Indicator line
    Box(
      modifier = Modifier
        .width(28.dp)
        .height(3.dp)
        .clip(RoundedCornerShape(2.dp))
        .background(if (isSelected) GoldSecondary else Color.Transparent)
    )

    Spacer(modifier = Modifier.height(3.dp))

    Box(contentAlignment = Alignment.Center) {
      Icon(
        imageVector = if (isSelected) selectedIcon else unselectedIcon,
        contentDescription = label,
        tint = if (isSelected) ChocolatePrimaryContainer else OnSurfaceVariant,
        modifier = Modifier.size(24.dp)
      )

      if (badgeCount > 0) {
        Box(
          modifier = Modifier
            .offset(x = 10.dp, y = (-6).dp)
            .size(16.dp)
            .background(GoldSecondaryContainer, CircleShape),
          contentAlignment = Alignment.Center
        ) {
          Text(
            text = badgeCount.toString(),
            color = OnGoldSecondaryContainer,
            fontSize = 9.sp,
            fontWeight = FontWeight.Bold
          )
        }
      }
    }

    Text(
      text = label,
      style = MaterialTheme.typography.labelSmall.copy(
        fontSize = 10.5.sp,
        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
      ),
      color = if (isSelected) ChocolatePrimaryContainer else OnSurfaceVariant,
      modifier = Modifier.padding(top = 2.dp),
      maxLines = 1
    )
  }
}
