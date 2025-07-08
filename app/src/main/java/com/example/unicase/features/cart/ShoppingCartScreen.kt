package com.example.unicase.features.cart

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.unicase.model.CartItem
import com.example.unicase.model.cartItems // <-- IMPORT PENTING
import com.example.unicase.model.dummyCartItems
import com.example.unicase.ui.theme.PrimaryBlue
import com.example.unicase.ui.theme.UnicaseTheme

// --- FUNGSI UTAMA YANG DIPERBARUI ---
@Composable
fun ShoppingCartScreen(navController: NavController) {
    // Membaca langsung dari list global `cartItems`
    if (cartItems.isEmpty()) {
        EmptyCartView(navController = navController)
    } else {
        FilledCartView(navController = navController, cartItems = cartItems)
    }
}

// Composable untuk tampilan keranjang berisi
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilledCartView(navController: NavController, cartItems: List<CartItem>) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Shopping Cart", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            CheckoutBottomBar(totalPrice = "Rp60.000")
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(cartItems) { cartItem ->
                CartItemRow(cartItem = cartItem)
            }
        }
    }
}

// Composable untuk satu baris item di keranjang
@Composable
fun CartItemRow(cartItem: CartItem) {
    var quantity by remember { mutableStateOf(cartItem.quantity) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .border(
                border = BorderStroke(1.dp, Color.LightGray),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 8.dp, vertical = 12.dp)
    ) {
        Checkbox(checked = true, onCheckedChange = { /*TODO*/ })
        Spacer(modifier = Modifier.width(8.dp))
        Image(
            painter = painterResource(id = cartItem.product.imageRes),
            contentDescription = cartItem.product.name,
            modifier = Modifier
                .size(70.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = cartItem.product.name, fontWeight = FontWeight.Bold, maxLines = 2, color = Color.Black)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = cartItem.product.price, color = Color.Black, fontWeight = FontWeight.SemiBold)
        }
        Spacer(modifier = Modifier.width(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),

        ) {
            IconButton(onClick = { if (quantity > 1) quantity-- }, modifier = Modifier.size(28.dp)) {
                Icon(Icons.Default.Remove, contentDescription = "Decrease quantity", tint = PrimaryBlue)
            }
            Text(text = "$quantity", fontWeight = FontWeight.Bold, color = PrimaryBlue)
            IconButton(onClick = { quantity++ }, modifier = Modifier.size(28.dp)) {
                Icon(Icons.Default.Add, contentDescription = "Increase quantity", tint = PrimaryBlue)
            }
        }
    }
}

// Composable untuk bottom bar checkout
@Composable
fun CheckoutBottomBar(totalPrice: String) {
    Surface(shadowElevation = 8.dp) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = true, onCheckedChange = { /*TODO*/ })
                Text("All", color = Color.Black)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(totalPrice, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = { /*TODO: Navigasi ke Checkout*/ }) {
                    Text("Checkout", color = Color.White)
                }
            }
        }
    }
}

// Composable untuk tampilan keranjang kosong
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmptyCartView(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Shopping Cart", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Empty Cart",
                modifier = Modifier.size(100.dp),
                tint = Color.LightGray
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Your Cart is Empty",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Find the perfect unique case for your phone!",
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }
    }
}

// Preview untuk melihat kedua kondisi
@Preview(name = "Filled Cart", showBackground = true)
@Composable
fun FilledCartScreenPreview() {
    UnicaseTheme {
        FilledCartView(rememberNavController(), dummyCartItems)
    }
}

@Preview(name = "Empty Cart", showBackground = true)
@Composable
fun EmptyCartScreenPreview() {
    UnicaseTheme {
        EmptyCartView(rememberNavController())
    }
}