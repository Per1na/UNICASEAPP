package com.example.unicase.features.checkout


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.unicase.features.customization.CustomCaseData
import com.example.unicase.features.customization.CustomizationViewModel
import com.example.unicase.model.globalCartItems
import com.example.unicase.ui.theme.Poppins
import com.example.unicase.ui.theme.UnicaseTheme
import java.text.NumberFormat
import java.util.Locale
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.unicase.features.customization.PriceRow
import com.example.unicase.model.AddressListViewModel
import com.example.unicase.ui.theme.PrimaryBlue

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
    customizationViewModel: CustomizationViewModel = viewModel(),
    addressListViewModel: AddressListViewModel = viewModel()
) {
    val selectedAddress by addressListViewModel.selectedAddress.collectAsState()
    val customCase = navController.previousBackStackEntry
        ?.savedStateHandle
        ?.get<CustomCaseData>("custom_case")


    val buyNowProduct = directBuyProduct.value

    val shippingOptions = mapOf("Regular" to 20000, "Express" to 40000)
    var selectedShippingLabel by remember { mutableStateOf("Regular") }


    val itemsSubtotal = globalCartItems.sumOf { (parsePrice(it.product.price) * it.quantity) }
    val customCaseSubtotal = customCase?.price ?: 0
    val buyNowSubtotal = buyNowProduct?.price?.toInt() ?: 0
    val subtotal = itemsSubtotal + customCaseSubtotal + buyNowSubtotal
    val shippingFee = shippingOptions[selectedShippingLabel] ?: 0
    val serviceFee = 1000
    val appServiceFee = 1000
    val totalPurchase = subtotal + shippingFee + serviceFee + appServiceFee
    val productPrice by customizationViewModel.price


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Checkout", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = {
                        directBuyProduct.value = null
                        navController.popBackStack()
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },

        bottomBar = {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shadowElevation = 8.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            "Total Payment",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Black
                        )
                        Text(
                            "Rp${String.format("%,d", totalPurchase)}",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryBlue
                        )
                    }
                    Button(onClick = {
                        navController.navigate("payment/$totalPurchase")
                    }) {
                        Text("Pay Now")
                    }
                }
            }
        }
    ) { innerPadding ->
        // Reset saat screen ini di-dispose (user keluar dari layar)
        DisposableEffect(Unit) {
            onDispose {
                directBuyProduct.value = null
                globalCartItems.clear()
                customizationViewModel.clearDesign()
            }
            onDispose {}
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ElevatedCard (
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiary
                )
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Default.LocationOn, contentDescription = "Address", modifier = Modifier.size(24.dp), tint = PrimaryBlue)
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Shipping Address", fontWeight = FontWeight.SemiBold, color = Color.Black)
                        if (selectedAddress != null) {Text(
                            "${selectedAddress!!.recipientName} (${selectedAddress!!})",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium
                        )
                            Text(
                                selectedAddress!!.streetAddress,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Gray,
                                maxLines = 2
                            )
                        } else {
                            Text(
                                "Jl.Kemerdekaan no. 17",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Gray
                            )
                        }
                    }
                    OutlinedButton(onClick = { navController.navigate("address_list") }) {
                        Text(if (selectedAddress != null) "Change" else "Add")
                    }
                }
            }


            HorizontalDivider()


            Text(
                text = "Order Detail",
                style = MaterialTheme.typography.titleMedium,
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            if (customCase != null) {
                directBuyProduct.value = null
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (customCase.imageUri != null) {
                        AsyncImage(
                            model = customCase.imageUri,
                            contentDescription = "Custom Image",
                            modifier = Modifier
                                .size(60.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            "Custom Case",
                            fontFamily = Poppins,
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            "Text: ${customCase.text ?: "-"}",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                        Text(
                            "Tipe: ${customCase.phoneType}",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                    }
                    Text("${formatPrice(customCase.price)} x1")
                }
            }
            if (buyNowProduct != null) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (!buyNowProduct.image.isNullOrEmpty()) {
                        AsyncImage(
                            model = buyNowProduct.image,
                            contentDescription = buyNowProduct.name,
                            modifier = Modifier
                                .size(60.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            buyNowProduct.name ?: "No Name",
                            fontFamily = Poppins,
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            buyNowProduct.description ?: "No Description",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                    }

                    Text("${formatPrice(buyNowProduct.price?.toInt() ?: 0)} x1")
                }
            }



            globalCartItems.forEach { cartItem ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
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
                        Text(
                            cartItem.product.name,
                            fontFamily = Poppins,
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            "Varian: ${cartItem.product.variant}",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                    }
                    Text("${cartItem.product.price} x${cartItem.quantity}")
                }
            }
            HorizontalDivider()

            OptionCard(
                icon = Icons.Default.LocalShipping,
                title = "Shipping Options",
                subtitle = "Reguler (2-3 Day)",
                price = shippingFee
            )
            HorizontalDivider()

            OptionCard(
                icon = Icons.Default.CreditCard,
                title = "Manual Payment",
                subtitle = "Bank BRI",
                price = null
            )
            HorizontalDivider()

            PriceRow(label = "Subtotal", price = formatPrice(subtotal))
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
@Composable
private fun OptionCard(icon: androidx.compose.ui.graphics.vector.ImageVector, title: String, subtitle: String, price: Int?) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiary)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = icon, contentDescription = title, modifier = Modifier.size(24.dp), tint = PrimaryBlue)
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(title, fontWeight = FontWeight.SemiBold, color = Color.Black)
                Text(subtitle, style = MaterialTheme.typography.bodyMedium, color = Color.Black)
            }
            price?.let {
                Text("Rp${String.format("%,d", it)}", fontWeight = FontWeight.Medium)
            }
        }
    }
}





@Preview(showBackground = true)
@Composable
fun CheckoutScreenPreview() {
    UnicaseTheme {
        CheckoutScreen(rememberNavController())
    }
}