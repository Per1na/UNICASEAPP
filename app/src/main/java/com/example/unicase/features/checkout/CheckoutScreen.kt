package com.example.unicase.features.customization // Pastikan package sesuai

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.unicase.R
import com.example.unicase.ui.theme.PrimaryBlue
import com.example.unicase.ui.theme.UnicaseTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(
    navController: NavController,
    customizationViewModel: CustomizationViewModel
) {
    // Mengambil semua data dari satu ViewModel bersama
    val layers by customizationViewModel.layers
    val caseType by customizationViewModel.caseType
    val printEffect by customizationViewModel.printEffect
    val phoneBrand by customizationViewModel.phoneBrand
    val phoneType by customizationViewModel.phoneType
    val productPrice by customizationViewModel.price
    val selectedAddress by customizationViewModel.selectedAddress


    // Contoh biaya tambahan
    val shippingCost = 12000
    val serviceFee = 1000
    val totalPrice = productPrice + shippingCost + serviceFee

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Checkout", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
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
                        Text("Total Payment", style = MaterialTheme.typography.bodyMedium, color = Color.Black)
                        Text(
                            "Rp${String.format("%,d", totalPrice)}",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryBlue
                        )
                    }
                    Button(onClick = { navController.navigate("payment/$totalPrice")
                    }) {
                        Text("Pay Now")
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Order Summary", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = Color.Black)

            // 1. Ringkasan Pesanan
            ElevatedCard(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiary)
            ) {
                Row(modifier = Modifier.padding(16.dp)) {
                    CasePreview(layers = layers)
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text("Custom Phone Case", fontWeight = FontWeight.Bold, color = Color.Black)
                        Text("$phoneBrand $phoneType", style = MaterialTheme.typography.bodyMedium, color = Color.Black)
                        Text("Case: $caseType", style = MaterialTheme.typography.bodyMedium, color = Color.Black)
                        Text("Effect: $printEffect", style = MaterialTheme.typography.bodyMedium, color = Color.Black)
                    }
                }
            }

            // 2. Bagian Alamat Pengiriman
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
                        if (selectedAddress != null) {
                            Text(
                                "${selectedAddress!!.recipientName} (${selectedAddress!!.label})",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                selectedAddress!!.fullAddress,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Gray,
                                maxLines = 2
                            )
                        } else {
                            Text(
                                "You haven't added an address yet.",
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

            // 3. Opsi Pengiriman
            OptionCard(
                icon = Icons.Default.LocalShipping,
                title = "Shipping Options",
                subtitle = "Reguler (2-3 Day)",
                price = shippingCost
            )

            // 4. Metode Pembayaran
            OptionCard(
                icon = Icons.Default.CreditCard,
                title = "Manual Payment",
                subtitle = "Bank BRI",
                price = null
            )

            // 5. Rincian Harga
            PriceDetails(productPrice, shippingCost, serviceFee, totalPrice)
        }
    }
}

@Composable
private fun CasePreview(layers: List<DesignLayer>) {
    Box(
        modifier = Modifier
            .width(80.dp)
            .aspectRatio(10f / 19.5f)
            .clipToBounds()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        layers.forEach { layer ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer(
                        scaleX = layer.scale.value,
                        scaleY = layer.scale.value,
                        translationX = layer.offsetX.value,
                        translationY = layer.offsetY.value,
                        rotationZ = layer.rotation.value
                    )
            ) {
                when (layer) {
                    is ImageLayer -> {
                        AsyncImage(
                            model = layer.uri,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    is TextLayer -> {
                        Text(
                            text = layer.text,
                            color = layer.color.value,
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
        Image(
            painter = painterResource(id = R.drawable.case_template),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
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

@Composable
private fun PriceDetails(productPrice: Int, shippingCost: Int, serviceFee: Int, totalPrice: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Price Details", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Color.Black)
        PriceRow(label = "Product Price", price = productPrice)
        PriceRow(label = "Shipping Costs", price = shippingCost)
        PriceRow(label = "Service Fees", price = serviceFee)
        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Total Payment", fontWeight = FontWeight.Bold, color = Color.Black)
            Text(
                "Rp${String.format("%,d", totalPrice)}",
                fontWeight = FontWeight.Bold,
                color = PrimaryBlue,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}

@Composable
private fun PriceRow(label: String, price: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = Color.Gray)
        Text("Rp${String.format("%,d", price)}", fontWeight = FontWeight.SemiBold, color = Color.Black)
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun CheckoutScreenPreview() {
    val navController = rememberNavController()
    val fakeViewModel = CustomizationViewModel()

    // Menambahkan data contoh ke ViewModel untuk preview
    fakeViewModel.setCaseType("Premium anti crack")
    fakeViewModel.setPrintEffect("Glow Effect")
    fakeViewModel.setPhoneType("iPhone 15 Pro Max")
    fakeViewModel.addTextLayer("Unicase", Color.White)

    // Perbaikan untuk preview: Buat objek, tambahkan, lalu pilih
    val fakeAddress = Address(
        recipientName = "Budi Santoso",
        phoneNumber = "081234567890",
        fullAddress = "Jl. Kemerdekaan No. 17, Tebet",
        label = "Rumah",
        isSelected = true
    )
    fakeViewModel.addAddress(fakeAddress)
    // Fungsi addAddress yang diperbarui akan otomatis memilih alamat ini
    // fakeViewModel.selectAddress(fakeAddress.id) // Tidak perlu lagi jika addAddress sudah menangani

    UnicaseTheme {
        CheckoutScreen(
            navController = navController,
            customizationViewModel = fakeViewModel
        )
    }
}