package com.example.unicase.features.checkout

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.unicase.R
import com.example.unicase.features.customization.CustomizationViewModel
import com.example.unicase.features.customization.ImageLayer
import com.example.unicase.features.customization.TextLayer
import com.example.unicase.model.globalCartItems
import com.example.unicase.ui.theme.Poppins
import com.example.unicase.ui.theme.PrimaryBlue
import com.example.unicase.ui.theme.UnicaseTheme
import java.text.NumberFormat
import java.util.Locale

private fun parsePrice(priceString: String): Int {
    return priceString.replace(Regex("[^\\d]"), "").toIntOrNull() ?: 0
}

private fun formatPrice(price: Int): String {
    val format = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
    format.maximumFractionDigits = 0
    return format.format(price).replace("Rp", "Rp ")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(
    navController: NavController,
    customizationViewModel: CustomizationViewModel = viewModel()
) {
    val customCaseLayers = customizationViewModel.layers.value
    val customCasePrice = customizationViewModel.price.value
    val customCasePhoneType = customizationViewModel.phoneType.value
    val hasCustomImage = customCaseLayers.any { it is ImageLayer }

    val shippingOptions = mapOf("Regular" to 20000, "Express" to 40000)
    var selectedShippingLabel by remember { mutableStateOf("Regular") }
    var isShippingExpanded by remember { mutableStateOf(false) }

    val paymentOptions = listOf("BRI Transfer")
    var selectedPayment by remember { mutableStateOf(paymentOptions.first()) }
    var isPaymentExpanded by remember { mutableStateOf(false) }

    val itemsSubtotal = globalCartItems.sumOf { (parsePrice(it.product.price) * it.quantity) }
    val customCaseSubtotal = if (hasCustomImage) customCasePrice else 0
    val subtotal = itemsSubtotal + customCaseSubtotal
    val shippingFee = shippingOptions[selectedShippingLabel] ?: 0
    val serviceFee = 1000
    val appServiceFee = 1000
    val totalPurchase = subtotal + shippingFee + serviceFee + appServiceFee

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Checkout", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            Button(
                onClick = { /*TODO: Proses pembayaran*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Pay Now")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Shipping Address",
                style = MaterialTheme.typography.titleMedium,
                fontFamily = Poppins,
                color = Color.Black,
                fontWeight = FontWeight.Bold)

            OutlinedButton(
                onClick = { /* TODO: Navigasi ke halaman daftar alamat */ },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Gray),
                border = BorderStroke(1.dp, Color.Black)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Address Icon",
                        tint = PrimaryBlue
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Select address", modifier = Modifier.weight(1f))
                    Icon(
                        Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "Select Address",
                        tint = Color.Black
                    )
                }
            }

            HorizontalDivider()

            Text(
                text = "Order Details",
                style = MaterialTheme.typography.titleMedium,
                fontFamily = Poppins,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        border = BorderStroke(1.dp, Color.Black),
                        shape = RoundedCornerShape(10.dp)
                    )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    if (hasCustomImage) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color.Gray),
                                contentAlignment = Alignment.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxHeight(0.9f)
                                        .aspectRatio(10f / 19.5f)
                                        .clipToBounds()
                                        .clip(RoundedCornerShape(8.dp))
                                ) {
                                    customCaseLayers.forEach { layer ->
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .graphicsLayer(
                                                    scaleX = layer.scale.value,
                                                    scaleY = layer.scale.value,
                                                    translationX = layer.offsetX.value / 4,
                                                    translationY = layer.offsetY.value / 4,
                                                    rotationZ = layer.rotation.value
                                                )
                                        ) {
                                            when (layer) {
                                                is ImageLayer -> {
                                                    AsyncImage(
                                                        model = layer.uri,
                                                        contentDescription = "Image Layer",
                                                        contentScale = ContentScale.Crop,
                                                        modifier = Modifier.fillMaxSize()
                                                    )
                                                }
                                                is TextLayer -> {
                                                    Text(
                                                        text = layer.text,
                                                        color = layer.color.value,
                                                        fontSize = 3.sp,
                                                        fontWeight = FontWeight.Bold,
                                                        modifier = Modifier.align(Alignment.Center)
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                                Image(
                                    painter = painterResource(id = R.drawable.case_template),
                                    contentDescription = "Case Template",
                                    contentScale = ContentScale.FillBounds,
                                    modifier = Modifier
                                        .fillMaxHeight(0.9f)
                                        .aspectRatio(10f / 19.5f)
                                        .clip(RoundedCornerShape(4.dp))
                                )
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text("Custom Case",
                                    fontFamily = Poppins,
                                    color = Color.Black,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis)
                                Text("Varian: $customCasePhoneType",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.Gray,
                                    fontSize = 12.sp,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis)
                            }
                            Text("${formatPrice(customCasePrice)} x1",
                                modifier = Modifier.widthIn(max = 80.dp),
                                fontSize = 12.sp)
                        }
                    }

                    globalCartItems.forEach { cartItem ->
                        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                            if (cartItem.product.imageUri != null) {
                                AsyncImage(
                                    model = cartItem.product.imageUri,
                                    contentDescription = cartItem.product.name,
                                    modifier = Modifier
                                        .size(60.dp)
                                        .clip(RoundedCornerShape(8.dp)),
                                    contentScale = ContentScale.Crop
                                )
                            } else {
                                Image(
                                    painter = painterResource(id = cartItem.product.imageRes),
                                    contentDescription = cartItem.product.name,
                                    modifier = Modifier
                                        .size(60.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                )
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(cartItem.product.name, fontFamily = Poppins, color = Color.Black, fontWeight = FontWeight.SemiBold)
                                Text("Varian: ${cartItem.product.variant}", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                            }
                            Text("${cartItem.product.price} x${cartItem.quantity}")
                        }
                    }
                }
            }
            HorizontalDivider()

            Text("Shipping Option",
                style = MaterialTheme.typography.titleMedium,
                fontFamily = Poppins,
                color = Color.Black,
                fontWeight = FontWeight.Bold)
            ExposedDropdownMenuBox(
                expanded = isShippingExpanded,
                onExpandedChange = { isShippingExpanded = !isShippingExpanded }
            ) {
                OutlinedTextField(
                    value = "$selectedShippingLabel (${formatPrice(shippingFee)})",
                    onValueChange = {},
                    readOnly = true,
                    shape = RoundedCornerShape(12.dp),
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isShippingExpanded) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = isShippingExpanded,
                    onDismissRequest = { isShippingExpanded = false }
                ) {
                    shippingOptions.forEach { (label, price) ->
                        DropdownMenuItem(
                            text = { Text("$label (${formatPrice(price)})") },
                            onClick = {
                                selectedShippingLabel = label
                                isShippingExpanded = false
                            }
                        )
                    }
                }
            }
            Text("Estimated arrival 10 - 17 Jan", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            HorizontalDivider()

            Text("Manual Payment", style = MaterialTheme.typography.titleMedium, fontFamily = Poppins, color = Color.Black, fontWeight = FontWeight.Bold)
            ExposedDropdownMenuBox(
                expanded = isPaymentExpanded,
                onExpandedChange = { isPaymentExpanded = !isPaymentExpanded }
            ) {
                OutlinedTextField(
                    value = selectedPayment,
                    onValueChange = {},
                    readOnly = true,
                    shape = RoundedCornerShape(12.dp),
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isPaymentExpanded) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = isPaymentExpanded,
                    onDismissRequest = { isPaymentExpanded = false }
                ) {
                    paymentOptions.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                selectedPayment = option
                                isPaymentExpanded = false
                            }
                        )
                    }
                }
            }
            HorizontalDivider()

            PriceRow(label = "Subtotal", price = formatPrice(subtotal))
            PriceRow(label = "Shipping Fee", price = formatPrice(shippingFee))
            PriceRow(label = "Service fee", price = formatPrice(serviceFee))
            PriceRow(label = "Application Service fee", price = formatPrice(appServiceFee))
            HorizontalDivider()
            PriceRow(label = "Total Purchase", price = formatPrice(totalPurchase), isTotal = true)
        }
    }
}

@Composable
fun PriceRow(label: String, price: String, isTotal: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontWeight = if(isTotal) FontWeight.Bold else FontWeight.Normal,
            color = if(isTotal) Color.Black else Color.Gray
        )
        Text(
            text = price,
            fontWeight = if(isTotal) FontWeight.Bold else FontWeight.SemiBold,
            color = if(isTotal) Color.Black else Color.DarkGray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CheckoutScreenPreview() {
    UnicaseTheme {
        CheckoutScreen(rememberNavController())
    }
}