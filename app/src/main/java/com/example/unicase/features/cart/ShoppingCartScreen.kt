package com.example.unicase.features.cart

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.unicase.R
import com.example.unicase.pricerupiah.formatRupiah
import com.example.unicase.repository.ProductResponse
import com.example.unicase.ui.theme.PrimaryBlue


data class CartItemState(
    val product: ProductResponse,
    var quantity: MutableState<Int> = mutableStateOf(1),
    var isSelected: MutableState<Boolean> = mutableStateOf(true)
)


val cartItems = mutableStateListOf<CartItemState>()

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
fun FilledCartView(navController: NavController, cartItems: List<CartItemState>) {
    val totalPrice by remember {
        derivedStateOf {
            cartItems.filter { it.isSelected.value }
                .sumOf { (it.product.price ?: 0.0) * it.quantity.value }
        }
    }
    val allSelected by remember { derivedStateOf { cartItems.all { it.isSelected.value } } }

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
            CheckoutBottomBar(
                totalPrice = "Rp${"%,.0f".format(totalPrice)}",
                allSelected = allSelected,
                onSelectAllChange = { isChecked ->
                    cartItems.forEach { it.isSelected.value = isChecked }
                }
            )

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
fun CartItemRow(cartItem: CartItemState) {
    val currentItem by rememberUpdatedState(cartItem)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(BorderStroke(1.dp, Color.LightGray), RoundedCornerShape(12.dp))
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(
                checked = cartItem.isSelected.value,
                onCheckedChange = {
                    cartItem.isSelected.value = it
                },
                        modifier = Modifier
                        .padding(top = 18.dp)
            )

            AsyncImage(
                model = cartItem.product.image ?: "",
                contentDescription = cartItem.product.name,
                modifier = Modifier
                    .size(70.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 48.dp)
            ) {
                Text(
                    text = cartItem.product.name ?: "Produk",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Black
                )
                Text(
                    text = formatRupiah(cartItem.product.price ?: 0.0),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = {
                        if (cartItem.quantity.value > 1) {
                            cartItem.quantity.value--
                        }
                    }) {
                        Icon(
                            painterResource(id = R.drawable.baseline_remove_24),
                            contentDescription = "Kurang",
                            tint = PrimaryBlue
                        )
                    }

                    Text(
                        "${cartItem.quantity.value}",
                        fontWeight = FontWeight.Bold,
                        color = PrimaryBlue
                    )

                    IconButton(onClick = {
                        cartItem.quantity.value++
                    }) {
                        Icon(Icons.Default.Add, contentDescription = "Tambah", tint = PrimaryBlue)
                    }
                }
            }
        }

        IconButton(
            onClick = {
                cartItems.remove(currentItem)
            },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(4.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.trash),
                contentDescription = "Hapus Produk",
                tint = Color.Black
            )
        }
    }
}








@Composable
fun CheckoutBottomBar(totalPrice: String, allSelected: Boolean,
                      onSelectAllChange: (Boolean) -> Unit) {
    Surface(shadowElevation = 8.dp) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = allSelected,
                    onCheckedChange = onSelectAllChange
                )
                Text("All", color = Color.Black)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(totalPrice, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = {
                    // TODO: Navigate ke CheckoutScreen nanti
                }) {
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
