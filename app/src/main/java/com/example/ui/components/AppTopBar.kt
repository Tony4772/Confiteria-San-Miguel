package com.example.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.SanMiguelData
import com.example.ui.theme.CreamSurface
import com.example.ui.theme.GoldSecondary
import com.example.ui.theme.OnSurfaceDark
import com.example.ui.theme.OnSurfaceVariant

@Composable
fun AppTopBar(
  isDetailScreen: Boolean = false,
  title: String = "Detalle De Bombón",
  onBackClick: () -> Unit = {},
  onSearchClick: () -> Unit = {},
  onShareClick: () -> Unit = {},
  onProfileClick: () -> Unit = {}
) {
  Surface(
    modifier = Modifier.fillMaxWidth(),
    color = CreamSurface.copy(alpha = 0.95f),
    shadowElevation = 2.dp
  ) {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .statusBarsPadding(),
      contentAlignment = Alignment.Center
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 840.dp)
          .height(68.dp)
          .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        if (isDetailScreen) {
          // Detail screen top bar
          Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
          ) {
            IconButton(
              onClick = onBackClick,
              modifier = Modifier
                .size(44.dp)
                .testTag("back_button")
            ) {
              Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Volver",
                tint = OnSurfaceDark
              )
            }

            Spacer(modifier = Modifier.width(4.dp))

            Text(
              text = title,
              style = MaterialTheme.typography.titleLarge.copy(
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
              ),
              color = OnSurfaceDark,
              maxLines = 1,
              overflow = TextOverflow.Ellipsis
            )
          }

          Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(
              onClick = onShareClick,
              modifier = Modifier
                .size(44.dp)
                .testTag("share_button")
            ) {
              Icon(
                imageVector = Icons.Default.Share,
                contentDescription = "Compartir",
                tint = OnSurfaceDark
              )
            }

            IconButton(
              onClick = onProfileClick,
              modifier = Modifier
                .size(44.dp)
                .testTag("profile_button")
            ) {
              SanMiguelImage(
                model = SanMiguelData.PROFILE_URL,
                contentDescription = "Perfil de usuario",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                  .size(32.dp)
                  .clip(CircleShape)
                  .border(1.dp, GoldSecondary.copy(alpha = 0.4f), CircleShape)
              )
            }
          }
        } else {
          // Standard header
          Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
              .weight(1f)
              .padding(end = 8.dp)
          ) {
            SanMiguelImage(
              model = SanMiguelData.LOGO_URL,
              contentDescription = "Confitería San Miguel Logo",
              contentScale = ContentScale.Fit,
              modifier = Modifier
                .height(34.dp)
                .width(34.dp)
                .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column {
              Text(
                text = "Confitería San Miguel",
                style = MaterialTheme.typography.titleMedium.copy(
                  fontWeight = FontWeight.Bold,
                  fontSize = 15.sp
                ),
                color = OnSurfaceDark,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
              )

              Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 1.dp)
              ) {
                Icon(
                  imageVector = Icons.Default.LocationOn,
                  contentDescription = null,
                  tint = GoldSecondary,
                  modifier = Modifier.size(13.dp)
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                  text = "MADRID · ENVÍO EXPRESS",
                  style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.6.sp
                  ),
                  color = GoldSecondary
                )
                Spacer(modifier = Modifier.width(2.dp))
                Icon(
                  imageVector = Icons.Default.ExpandMore,
                  contentDescription = null,
                  tint = OnSurfaceVariant,
                  modifier = Modifier.size(13.dp)
                )
              }
            }
          }

          Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(
              onClick = onSearchClick,
              modifier = Modifier
                .size(44.dp)
                .testTag("search_button")
            ) {
              Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Buscar chocolates y bombones",
                tint = OnSurfaceDark
              )
            }

            IconButton(
              onClick = onProfileClick,
              modifier = Modifier
                .size(44.dp)
                .testTag("profile_button")
            ) {
              SanMiguelImage(
                model = SanMiguelData.PROFILE_URL,
                contentDescription = "Perfil",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                  .size(34.dp)
                  .clip(CircleShape)
                  .border(1.5.dp, GoldSecondary.copy(alpha = 0.4f), CircleShape)
              )
            }
          }
        }
      }
    }
  }
}
