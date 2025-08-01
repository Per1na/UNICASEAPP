package com.example.unicase.features.addresslist

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.unicase.R
import com.example.unicase.features.customization.Address
import com.example.unicase.features.customization.CustomizationViewModel
import com.example.unicase.ui.theme.PrimaryBlue
import com.example.unicase.ui.theme.UnicaseTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressListScreen(
    navController: NavController,
    customizationViewModel: CustomizationViewModel
) {
    val addresses by customizationViewModel.addresses

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Addresses", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        if (addresses.isEmpty()) {
            EmptyAddressContent(
                modifier = Modifier.padding(innerPadding),
                onAddAddressClick = { navController.navigate("add_address") }
            )
        } else {
            FilledAddressContent(
                modifier = Modifier.padding(innerPadding),
                addresses = addresses,
                onSelectAddress = { address ->
                    // Beri tahu ViewModel alamat mana yang dipilih
                    customizationViewModel.selectAddress(address.id)
                    // Langsung kembali ke CheckoutScreen setelah memilih
                    navController.popBackStack()
                },
                onAddAddressClick = { navController.navigate("add_address") }
            )
        }
    }
}

@Composable
private fun FilledAddressContent(
    modifier: Modifier = Modifier,
    addresses: List<Address>,
    onSelectAddress: (Address) -> Unit,
    onAddAddressClick: () -> Unit,
) {
    Column(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(addresses) { address ->
                AddressItemCard(
                    address = address,
                    isSelected = address.isSelected,
                    onClick = { onSelectAddress(address) }
                )
            }
        }
        Button(
            onClick = onAddAddressClick,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Add New Address")
        }
    }
}

@Composable
private fun AddressItemCard(
    address: Address,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = if (isSelected) 2.dp else 1.dp,
                color = if (isSelected) PrimaryBlue else Color.LightGray,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "${address.recipientName} (${address.label})",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = address.phoneNumber,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = address.fullAddress,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            if (isSelected) {
                RadioButton(selected = true, onClick = null)
            }
        }
    }
}

@Composable
private fun EmptyAddressContent(
    modifier: Modifier = Modifier,
    onAddAddressClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_unicase_logo2),
            contentDescription = "UNICASE Logo",
            modifier = Modifier
                .width(320.dp)
                .height(100.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "No addresses registered yet",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Add an address so your order can be shipped.\n" +
                    "You can register more than one address!",
            fontSize = 14.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = onAddAddressClick,
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Address", color = Color.White)
        }
    }
}


@Preview(name = "Address List - Filled", showBackground = true)
@Composable
fun FilledAddressContentPreview() {
    val fakeAddresses = listOf(
        Address(
            recipientName = "Budi Santoso (Utama)",
            phoneNumber = "081234567890",
            fullAddress = "Jl. Casablanca Raya No. 88, Tebet, Jakarta Selatan, DKI JAKARTA, 12810",
            label = "Rumah",
            isSelected = true
        ),
        Address(
            recipientName = "Ani Lestari",
            phoneNumber = "087788990011",
            fullAddress = "Gedung Bio Farma, Jl. Pasteur No. 28, Sukajadi, Kota Bandung, JAWA BARAT, 40162",
            label = "Kantor",
            isSelected = false
        )
    )

    UnicaseTheme {
        FilledAddressContent(
            addresses = fakeAddresses,
            onAddAddressClick = {},
            onSelectAddress = {}
        )
    }
}