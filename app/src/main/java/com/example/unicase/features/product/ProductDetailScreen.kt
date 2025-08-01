package com.example.unicase.features.product

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.unicase.features.cart.CartItemState
import com.example.unicase.features.cart.cartItems
import com.example.unicase.pricerupiah.formatRupiah
import com.example.unicase.repository.ApiClient
import com.example.unicase.repository.ProductRepository
import com.example.unicase.repository.ProductResponse
import com.example.unicase.ui.theme.PrimaryBlue
import com.example.unicase.viewmodel.ProductViewModel
import com.example.unicase.viewmodel.ProductViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(productId: Int, navController: NavController) {
    val viewModel: ProductViewModel = viewModel(
        factory = ProductViewModelFactory(ProductRepository(ApiClient.apiService))
    )

    // Ambil detail produk
    LaunchedEffect(productId) {
        viewModel.getProductById(productId)
    }


    val product = viewModel.productDetail.value
    val error = viewModel.error.value

    if (error != null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Gagal memuat produk: $error", color = Color.Red)
        }
        return
    }

    var isFavorited by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Product Detail", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { isFavorited = !isFavorited }) {
                        Icon(
                            imageVector = if (isFavorited) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Wishlist",
                            tint = PrimaryBlue
                        )
                    }
                }
            )
        },
        bottomBar = {
            if (product != null) {
                BottomBar(product, navController = navController)
            }
        }
    ) { innerPadding ->
        if (product != null) {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                AsyncImage(
                    model = product.image ?: "",
                    contentDescription = product.name ?: "No name",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = product.name ?: "",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = product.description ?: "No Description",
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = formatRupiah(product.price ?: 0),
                    fontWeight = FontWeight.Bold,
                    color = PrimaryBlue
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}


@Composable
fun BottomBar(product: ProductResponse, navController: NavController) {
    val context = LocalContext.current

    Surface(shadowElevation = 8.dp) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text("Total Price", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                Text(
                    text = formatRupiah(product.price ?: 0),
                    fontWeight = FontWeight.Bold,
                    color = PrimaryBlue
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(onClick = {
                    com.example.unicase.features.checkout.directBuyProduct.value = product
                    navController.navigate("checkout")
                }) {
                    Text("Buy")
                }
                Button(onClick = {
                    val existingItem = cartItems.find { it.product.id == product.id }

                    if (existingItem != null) {
                        existingItem.quantity.value++
                    } else {
                        cartItems.add(CartItemState(product = product, quantity = mutableStateOf(1)))
                    }
                    Toast.makeText(
                        context,
                        "${product.name ?: "Produk"} Added to cart",
                        Toast.LENGTH_SHORT
                    ).show()
                }) {
                    Icon(Icons.Default.ShoppingCart, contentDescription = "Add to Cart")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Add to Cart")
                }
            }
        }
    }
}
