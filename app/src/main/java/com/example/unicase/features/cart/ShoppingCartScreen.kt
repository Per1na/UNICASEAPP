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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.unicase.repository.ProductResponse
import com.example.unicase.ui.theme.PrimaryBlue
import com.example.unicase.ui.theme.UnicaseTheme

data class CartItem(
    val product: ProductResponse,
    var quantity: Int = 1
)

val cartItems = mutableStateListOf<CartItem>()

@Composable
fun ShoppingCartScreen(navController: NavController) {
    if (cartItems.isEmpty()) {
        EmptyCartView(navController)
    } else {
        FilledCartView(navController, cartItems)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilledCartView(navController: NavController, cartItems: List<CartItem>) {
    val totalPrice = cartItems.sumOf { (it.product.price ?: 0.0) * it.quantity }


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
            CheckoutBottomBar(totalPrice = "Rp${"%,.0f".format(totalPrice)}")
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(cartItems) { item ->
                CartItemRow(item)
            }
        }
    }
}

@Composable
fun CartItemRow(cartItem: CartItem) {
    var quantity by remember { mutableStateOf(cartItem.quantity) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .border(BorderStroke(1.dp, Color.LightGray), RoundedCornerShape(12.dp))
            .padding(horizontal = 8.dp, vertical = 12.dp)
    ) {
        Checkbox(checked = true, onCheckedChange = { })
        Spacer(modifier = Modifier.width(8.dp))
        AsyncImage(
            model = cartItem.product.image ?: "",
            contentDescription = cartItem.product.name,
            modifier = Modifier
                .size(70.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(cartItem.product.name ?:"Produk", fontWeight = FontWeight.Bold, maxLines = 2, color = Color.Black)
            Spacer(modifier = Modifier.height(4.dp))
            Text("Rp${cartItem.product.price}", fontWeight = FontWeight.SemiBold, color = Color.Black)
        }
        Spacer(modifier = Modifier.width(16.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = {
                if (quantity > 1) {
                    quantity--
                    cartItem.quantity = quantity
                }
            }) {
                Icon(Icons.Default.Close, contentDescription = "Kurang", tint = PrimaryBlue)
            }
            Text("$quantity", fontWeight = FontWeight.Bold, color = PrimaryBlue)
            IconButton(onClick = {
                quantity++
                cartItem.quantity = quantity
            }) {
                Icon(Icons.Default.Add, contentDescription = "Tambah", tint = PrimaryBlue)
            }
        }
    }
}

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
                Checkbox(checked = true, onCheckedChange = { })
                Text("All", color = Color.Black)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(totalPrice, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = { /* TODO: Navigasi ke halaman checkout */ }) {
                    Text("Checkout", color = Color.White)
                }
            }
        }
    }
}

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
            Text("Your Cart is Empty", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Find the perfect unique case for your phone!",
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }
    }
}
