package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.OnSurfaceDark
import com.example.ui.theme.ChocolatePrimaryContainer
import com.example.ui.theme.CreamBackground
import com.example.ui.theme.CreamSurface
import com.example.ui.theme.GoldSecondary
import com.example.ui.theme.GoldSecondaryContainer
import com.example.viewmodel.ShopUiState

@Composable
fun ReclamacionesScreen(
  uiState: ShopUiState,
  onBack: () -> Unit,
  onSubmit: (
    nombreCompleto: String,
    tipoDocumento: String,
    numeroDocumento: String,
    telefono: String,
    email: String,
    tipoReclamo: String,
    descripcionBien: String,
    descripcionProblema: String,
    montoReclamado: Double?,
    pedidoSolicitado: String,
    onSuccess: (String) -> Unit,
    onError: (String) -> Unit
  ) -> Unit
) {
  var tipoReclamo by remember { mutableStateOf("reclamo") }
  var nombreCompleto by remember { mutableStateOf(uiState.accountName.orEmpty()) }
  var tipoDocumento by remember { mutableStateOf("DNI") }
  var numeroDocumento by remember { mutableStateOf("") }
  var telefono by remember { mutableStateOf("") }
  var email by remember { mutableStateOf(uiState.accountEmail.orEmpty()) }
  var descripcionBien by remember { mutableStateOf("") }
  var descripcionProblema by remember { mutableStateOf("") }
  var montoReclamado by remember { mutableStateOf("") }
  var pedidoSolicitado by remember { mutableStateOf("") }

  var isSubmitting by remember { mutableStateOf(false) }
  var errorMessage by remember { mutableStateOf<String?>(null) }
  var codigoRegistrado by remember { mutableStateOf<String?>(null) }

  val companyName = uiState.storefront.brandCompanyName.ifBlank { "EBYZOM E.I.R.L." }

  val textFieldColors = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = GoldSecondary,
    unfocusedBorderColor = Color(0xFF8B5E3C).copy(alpha = 0.5f),
    focusedLabelColor = GoldSecondary,
    unfocusedLabelColor = OnSurfaceDark.copy(alpha = 0.7f),
    cursorColor = GoldSecondary,
    focusedTextColor = OnSurfaceDark,
    unfocusedTextColor = OnSurfaceDark
  )

  if (codigoRegistrado != null) {
    // Pantalla de confirmación
    Column(
      modifier = Modifier
        .fillMaxSize()
        .background(CreamBackground)
        .padding(24.dp)
        .verticalScroll(rememberScrollState()),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      Box(
        modifier = Modifier
          .size(80.dp)
          .clip(CircleShape)
          .background(Color(0xFF2E7D32).copy(alpha = 0.15f))
          .border(2.dp, Color(0xFF2E7D32), CircleShape),
        contentAlignment = Alignment.Center
      ) {
        Icon(
          imageVector = Icons.Default.CheckCircle,
          contentDescription = null,
          tint = Color(0xFF2E7D32),
          modifier = Modifier.size(48.dp)
        )
      }

      Spacer(modifier = Modifier.height(20.dp))

      Text(
        text = "Reclamación Registrada",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = OnSurfaceDark
      )

      Spacer(modifier = Modifier.height(8.dp))

      Text(
        text = "Tu solicitud ha sido guardada conforme a la Ley N° 29571.",
        fontSize = 14.sp,
        color = OnSurfaceDark.copy(alpha = 0.7f),
        textAlign = TextAlign.Center
      )

      Spacer(modifier = Modifier.height(24.dp))

      Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = CreamSurface),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
      ) {
        Column(
          modifier = Modifier.padding(20.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Text(
            text = "CÓDIGO DE REFERENCIA",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = GoldSecondaryContainer,
            letterSpacing = 1.sp
          )
          Spacer(modifier = Modifier.height(6.dp))
          Text(
            text = codigoRegistrado.orEmpty(),
            fontSize = 22.sp,
            fontWeight = FontWeight.ExtraBold,
            color = OnSurfaceDark
          )
          Spacer(modifier = Modifier.height(12.dp))
          Text(
            text = "Guarda este código para hacer seguimiento a tu requerimiento. Hemos registrado el correo $email para enviarte la respuesta.",
            fontSize = 12.sp,
            color = OnSurfaceDark.copy(alpha = 0.7f),
            textAlign = TextAlign.Center,
            lineHeight = 18.sp
          )
          Spacer(modifier = Modifier.height(8.dp))
          Text(
            text = "Plazo de respuesta: máximo 30 días calendario.",
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFFB71C1C)
          )
        }
      }

      Spacer(modifier = Modifier.height(32.dp))

      Button(
        onClick = onBack,
        colors = ButtonDefaults.buttonColors(containerColor = ChocolatePrimaryContainer),
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
          .fillMaxWidth()
          .height(48.dp)
      ) {
        Text("Volver al Inicio", color = Color.White, fontWeight = FontWeight.Bold)
      }
    }
    return
  }

  // Formulario principal
  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(CreamBackground)
      .verticalScroll(rememberScrollState())
      .padding(horizontal = 20.dp, vertical = 16.dp)
  ) {
    // Header
    Row(
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier.fillMaxWidth()
    ) {
      IconButton(onClick = onBack) {
        Icon(
          imageVector = Icons.AutoMirrored.Filled.ArrowBack,
          contentDescription = "Volver",
          tint = OnSurfaceDark
        )
      }
      Spacer(modifier = Modifier.width(4.dp))
      Box(
        modifier = Modifier
          .size(36.dp)
          .clip(CircleShape)
          .background(Color(0xFFB71C1C)),
        contentAlignment = Alignment.Center
      ) {
        Icon(
          imageVector = Icons.AutoMirrored.Filled.MenuBook,
          contentDescription = null,
          tint = Color.White,
          modifier = Modifier.size(20.dp)
        )
      }
      Spacer(modifier = Modifier.width(12.dp))
      Column {
        Text(
          text = "Libro de Reclamaciones",
          fontSize = 18.sp,
          fontWeight = FontWeight.Bold,
          color = OnSurfaceDark
        )
        Text(
          text = companyName,
          fontSize = 12.sp,
          color = OnSurfaceDark.copy(alpha = 0.7f)
        )
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Legal notice banner
    Card(
      colors = CardDefaults.cardColors(containerColor = Color(0xFFB71C1C).copy(alpha = 0.08f)),
      shape = RoundedCornerShape(12.dp),
      border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFB71C1C).copy(alpha = 0.25f)),
      modifier = Modifier.fillMaxWidth()
    ) {
      Row(
        modifier = Modifier.padding(12.dp),
        verticalAlignment = Alignment.Top
      ) {
        Icon(
          imageVector = Icons.Default.Info,
          contentDescription = null,
          tint = Color(0xFFB71C1C),
          modifier = Modifier
            .size(20.dp)
            .padding(top = 2.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
          text = "Conforme a la Ley N° 29571 (Código de Protección y Defensa del Consumidor), este establecimiento cuenta con un Libro de Reclamaciones virtual a tu disposición. El proveedor responderá en un plazo no mayor a 30 días calendario.",
          fontSize = 12.sp,
          lineHeight = 17.sp,
          color = OnSurfaceDark.copy(alpha = 0.85f)
        )
      }
    }

    Spacer(modifier = Modifier.height(20.dp))

    // Selector Tipo Reclamo vs Queja
    Text(
      text = "TIPO DE SOLICITUD *",
      fontSize = 11.sp,
      fontWeight = FontWeight.Bold,
      color = GoldSecondaryContainer,
      letterSpacing = 1.sp
    )
    Spacer(modifier = Modifier.height(8.dp))
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      // Reclamo
      Card(
        modifier = Modifier
          .weight(1f)
          .clickable { tipoReclamo = "reclamo" },
        colors = CardDefaults.cardColors(
          containerColor = if (tipoReclamo == "reclamo") GoldSecondary.copy(alpha = 0.15f) else CreamSurface
        ),
        shape = RoundedCornerShape(12.dp),
        border = androidx.compose.foundation.BorderStroke(
          width = if (tipoReclamo == "reclamo") 1.5.dp else 1.dp,
          color = if (tipoReclamo == "reclamo") GoldSecondary else Color(0xFF8B5E3C).copy(alpha = 0.25f)
        )
      ) {
        Row(
          modifier = Modifier.padding(10.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          RadioButton(
            selected = tipoReclamo == "reclamo",
            onClick = { tipoReclamo = "reclamo" },
            colors = RadioButtonDefaults.colors(selectedColor = GoldSecondary)
          )
          Column {
            Text("Reclamo", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = OnSurfaceDark)
            Text("Disconformidad de producto o servicio", fontSize = 10.sp, color = OnSurfaceDark.copy(alpha = 0.6f), lineHeight = 13.sp)
          }
        }
      }

      // Queja
      Card(
        modifier = Modifier
          .weight(1f)
          .clickable { tipoReclamo = "queja" },
        colors = CardDefaults.cardColors(
          containerColor = if (tipoReclamo == "queja") GoldSecondary.copy(alpha = 0.15f) else CreamSurface
        ),
        shape = RoundedCornerShape(12.dp),
        border = androidx.compose.foundation.BorderStroke(
          width = if (tipoReclamo == "queja") 1.5.dp else 1.dp,
          color = if (tipoReclamo == "queja") GoldSecondary else Color(0xFF8B5E3C).copy(alpha = 0.25f)
        )
      ) {
        Row(
          modifier = Modifier.padding(10.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          RadioButton(
            selected = tipoReclamo == "queja",
            onClick = { tipoReclamo = "queja" },
            colors = RadioButtonDefaults.colors(selectedColor = GoldSecondary)
          )
          Column {
            Text("Queja", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = OnSurfaceDark)
            Text("Malestar o incomodidad en la atención", fontSize = 10.sp, color = OnSurfaceDark.copy(alpha = 0.6f), lineHeight = 13.sp)
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(24.dp))

    // Sección: Datos del Consumidor
    Text(
      text = "DATOS DEL CONSUMIDOR",
      fontSize = 11.sp,
      fontWeight = FontWeight.Bold,
      color = GoldSecondaryContainer,
      letterSpacing = 1.sp
    )
    Spacer(modifier = Modifier.height(10.dp))

    OutlinedTextField(
      value = nombreCompleto,
      onValueChange = { nombreCompleto = it },
      label = { Text("Nombre completo *") },
      modifier = Modifier.fillMaxWidth(),
      shape = RoundedCornerShape(10.dp),
      colors = textFieldColors,
      singleLine = true
    )

    Spacer(modifier = Modifier.height(10.dp))

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      // Tipo doc chips
      Row(
        modifier = Modifier.weight(0.45f),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
      ) {
        listOf("DNI", "RUC", "CE").forEach { doc ->
          val sel = tipoDocumento == doc
          Box(
            modifier = Modifier
              .weight(1f)
              .height(56.dp)
              .clip(RoundedCornerShape(10.dp))
              .background(if (sel) GoldSecondary else CreamSurface)
              .border(1.dp, if (sel) GoldSecondary else Color(0xFF8B5E3C).copy(alpha = 0.3f), RoundedCornerShape(10.dp))
              .clickable { tipoDocumento = doc },
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = doc,
              fontWeight = FontWeight.Bold,
              fontSize = 12.sp,
              color = if (sel) Color.White else OnSurfaceDark
            )
          }
        }
      }

      OutlinedTextField(
        value = numeroDocumento,
        onValueChange = { numeroDocumento = it },
        label = { Text("N° Documento *") },
        modifier = Modifier.weight(0.55f),
        shape = RoundedCornerShape(10.dp),
        colors = textFieldColors,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true
      )
    }

    Spacer(modifier = Modifier.height(10.dp))

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      OutlinedTextField(
        value = telefono,
        onValueChange = { telefono = it },
        label = { Text("Teléfono / Celular *") },
        modifier = Modifier.weight(1f),
        shape = RoundedCornerShape(10.dp),
        colors = textFieldColors,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        singleLine = true
      )
      OutlinedTextField(
        value = email,
        onValueChange = { email = it },
        label = { Text("Correo electrónico *") },
        modifier = Modifier.weight(1f),
        shape = RoundedCornerShape(10.dp),
        colors = textFieldColors,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true
      )
    }

    Spacer(modifier = Modifier.height(24.dp))

    // Sección: Detalle del Reclamo
    Text(
      text = "DETALLE DE LA RECLAMACIÓN",
      fontSize = 11.sp,
      fontWeight = FontWeight.Bold,
      color = GoldSecondaryContainer,
      letterSpacing = 1.sp
    )
    Spacer(modifier = Modifier.height(10.dp))

    OutlinedTextField(
      value = descripcionBien,
      onValueChange = { descripcionBien = it },
      label = { Text("Producto o servicio contratado *") },
      placeholder = { Text("Ej.: Tableta 70% Cacao San Martín") },
      modifier = Modifier.fillMaxWidth(),
      shape = RoundedCornerShape(10.dp),
      colors = textFieldColors,
      singleLine = true
    )

    Spacer(modifier = Modifier.height(10.dp))

    OutlinedTextField(
      value = descripcionProblema,
      onValueChange = { descripcionProblema = it },
      label = { Text("Descripción del reclamo o queja *") },
      placeholder = { Text("Detalla lo sucedido con la mayor precisión posible...") },
      modifier = Modifier
        .fillMaxWidth()
        .height(110.dp),
      shape = RoundedCornerShape(10.dp),
      colors = textFieldColors,
      maxLines = 4
    )

    Spacer(modifier = Modifier.height(10.dp))

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      OutlinedTextField(
        value = montoReclamado,
        onValueChange = { montoReclamado = it },
        label = { Text("Monto (S/) opcional") },
        placeholder = { Text("0.00") },
        modifier = Modifier.weight(0.4f),
        shape = RoundedCornerShape(10.dp),
        colors = textFieldColors,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        singleLine = true
      )

      OutlinedTextField(
        value = pedidoSolicitado,
        onValueChange = { pedidoSolicitado = it },
        label = { Text("Pedido / Solución solicitada *") },
        placeholder = { Text("Ej.: Reembolso, reposición...") },
        modifier = Modifier.weight(0.6f),
        shape = RoundedCornerShape(10.dp),
        colors = textFieldColors,
        singleLine = true
      )
    }

    if (errorMessage != null) {
      Spacer(modifier = Modifier.height(12.dp))
      Text(
        text = errorMessage.orEmpty(),
        color = Color(0xFFB71C1C),
        fontSize = 13.sp,
        fontWeight = FontWeight.SemiBold
      )
    }

    Spacer(modifier = Modifier.height(28.dp))

    // Botones
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      OutlinedButton(
        onClick = onBack,
        modifier = Modifier
          .weight(1f)
          .height(48.dp),
        shape = RoundedCornerShape(24.dp)
      ) {
        Text("Cancelar", color = OnSurfaceDark)
      }

      Button(
        onClick = {
          if (nombreCompleto.isBlank() || numeroDocumento.isBlank() || telefono.isBlank() ||
            email.isBlank() || descripcionBien.isBlank() || descripcionProblema.isBlank() || pedidoSolicitado.isBlank()
          ) {
            errorMessage = "Por favor completa todos los campos obligatorios (*)."
            return@Button
          }

          errorMessage = null
          isSubmitting = true
          val montoNum = montoReclamado.replace(',', '.').toDoubleOrNull()

          onSubmit(
            nombreCompleto,
            tipoDocumento,
            numeroDocumento,
            telefono,
            email,
            tipoReclamo,
            descripcionBien,
            descripcionProblema,
            montoNum,
            pedidoSolicitado,
            { codigo ->
              isSubmitting = false
              codigoRegistrado = codigo
            },
            { err ->
              isSubmitting = false
              errorMessage = err
            }
          )
        },
        enabled = !isSubmitting,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB71C1C)),
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
          .weight(1.3f)
          .height(48.dp)
      ) {
        if (isSubmitting) {
          CircularProgressIndicator(color = Color.White, modifier = Modifier.size(20.dp), strokeWidth = 2.dp)
        } else {
          Icon(imageVector = Icons.Default.Send, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
          Spacer(modifier = Modifier.width(6.dp))
          Text("Registrar Reclamo", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 13.sp)
        }
      }
    }

    Spacer(modifier = Modifier.height(32.dp))
  }
}
