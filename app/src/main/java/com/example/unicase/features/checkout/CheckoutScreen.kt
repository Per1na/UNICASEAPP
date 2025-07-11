package com.example.unicase.features.checkout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.unicase.model.dummyCartItems
import com.example.unicase.ui.theme.Poppins
import com.example.unicase.ui.theme.UnicaseTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(navController: NavController) {
    // State untuk dropdown
    val shippingOptions = listOf("Regular (Rp20.000)", "Express (Rp40.000)")
    var selectedShipping by remember { mutableStateOf(shippingOptions.first()) }
    var isShippingExpanded by remember { mutableStateOf(false) }

    val paymentOptions = listOf("BCA Virtual Account", "GoPay", "OVO", "COD")
    var selectedPayment by remember { mutableStateOf(paymentOptions.first()) }
    var isPaymentExpanded by remember { mutableStateOf(false) }

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
            // Bagian Alamat Pengiriman
            Text("Shipping Address",
                style = MaterialTheme.typography.titleMedium,
                fontFamily = Poppins,
                color = Color.Black,
                fontWeight = FontWeight.Bold)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Select address", color = Color.Gray)
                Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "Select Address")
            }
            HorizontalDivider()

            // Daftar Produk (saat ini masih dummy)
            dummyCartItems.forEach { cartItem ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        painter = painterResource(id = cartItem.product.imageRes),
                        contentDescription = cartItem.product.name,
                        modifier = Modifier
                            .size(60.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(cartItem.product.name,
                            fontFamily = Poppins,
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold)
                        Text("Varian: ${cartItem.product.variant}",
                            style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                    }
                    Text("${cartItem.product.price} x${cartItem.quantity}")
                }
            }
            HorizontalDivider()

            // Dropdown Opsi Pengiriman
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
                    value = selectedShipping,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isShippingExpanded) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = isShippingExpanded,
                    onDismissRequest = { isShippingExpanded = false }
                ) {
                    shippingOptions.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                selectedShipping = option
                                isShippingExpanded = false
                            }
                        )
                    }
                }
            }
            Text("Estimated arrival 10 - 17 Jan", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            Divider()

            // Dropdown Metode Pembayaran
            Text("Payment Method",
                style = MaterialTheme.typography.titleMedium,
                fontFamily = Poppins,
                color = Color.Black,
                fontWeight = FontWeight.Bold)
            ExposedDropdownMenuBox(
                expanded = isPaymentExpanded,
                onExpandedChange = { isPaymentExpanded = !isPaymentExpanded }
            ) {
                OutlinedTextField(
                    value = selectedPayment,
                    onValueChange = {},
                    readOnly = true,
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
            Divider()

            // Rincian Pembelian
            PriceRow(label = "Total Price", price = "Rp.60.000")
            PriceRow(label = "Shipping Fee", price = "Rp.20.000")
            PriceRow(label = "Service fee", price = "Rp.1000")
            PriceRow(label = "Application Service fee", price = "Rp.1000")
            Divider()
            PriceRow(label = "Total Purchase", price = "Rp.82.000", isTotal = true)
        }
    }
}

// Composable terpisah untuk baris harga
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