package com.example.unicase.features.addresslist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.example.unicase.R
import com.example.unicase.model.Address
import com.example.unicase.model.AddressListViewModel
import com.example.unicase.ui.theme.PrimaryBlue
import com.example.unicase.ui.theme.UnicaseTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressListScreen(
    addressListViewModel: AddressListViewModel,
    onBackClick: () -> Unit,
    onAddAddressClick: () -> Unit,
    onAddressSelected: (Address) -> Unit
) {
    val addresses by addressListViewModel.addresses.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Address List", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        if (addresses.isEmpty()) {
            EmptyAddressContent(
                modifier = Modifier.padding(innerPadding),
                onAddAddressClick = onAddAddressClick
            )
        } else {
            FilledAddressContent(
                modifier = Modifier.padding(innerPadding),
                addresses = addresses,
                onSelectAddress = {
                    addressListViewModel.selectAddress(it)
                    onAddressSelected(it)
                },
                onAddAddressClick = onAddAddressClick
            )
        }
    }
}

@Composable
fun FilledAddressContent(
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
                    onClick = { onSelectAddress(address) }
                )
            }
        }
        Button(
            onClick = onAddAddressClick,
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Add New Address", color = Color.White)
        }
    }
}

@Composable
fun AddressItemCard(
    address: Address,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(address.recipientName, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
            Text(address.phone, fontSize = 14.sp, color = Color.Gray)
            Spacer(Modifier.height(4.dp))
            Text(
                text = "${address.streetAddress}, ${address.district}, ${address.city}, ${address.province}, ${address.postalCode}",
                fontSize = 14.sp,
                lineHeight = 20.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun EmptyAddressContent(
    modifier: Modifier = Modifier,
    onAddAddressClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize().padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_unicase_logo2),
            contentDescription = "UNICASE Logo",
            modifier = Modifier.width(320.dp).height(100.dp)
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

@Preview(name = "Address List - Empty", showBackground = true)
@Composable
fun EmptyAddressContentPreview() {
    UnicaseTheme {
        EmptyAddressContent(onAddAddressClick = {})
    }
}


@Preview(name = "Address List - Filled", showBackground = true)
@Composable
fun FilledAddressContentPreview() {
    val fakeAddresses = listOf(
        Address(
            recipientName = "Budi Santoso",
            phone = "081234567890",
            province = "DKI JAKARTA",
            city = "Jakarta Selatan",
            district = "Tebet",
            postalCode = "12810",
            streetAddress = "Jl. Casablanca Raya No. 88",
            isMain = true
        ),
        Address(
            recipientName = "Ani Lestari (Kantor)",
            phone = "087788990011",
            province = "JAWA BARAT",
            city = "Kota Bandung",
            district = "Sukajadi",
            postalCode = "40162",
            streetAddress = "Gedung Bio Farma, Jl. Pasteur No. 28",
            isMain = false
        )
    )

    UnicaseTheme {
        FilledAddressContent(
            addresses = fakeAddresses,
            onAddAddressClick = {},
            onSelectAddress = {} // ✅ ini yang benar
        )
    }
}

