package com.example.unicase.features.product

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.unicase.R
import com.example.unicase.model.CartItem
import com.example.unicase.model.ColorOption
import com.example.unicase.model.Product
import com.example.unicase.model.globalCartItems
import com.example.unicase.model.dummyProducts
import com.example.unicase.ui.theme.Poppins
import com.example.unicase.ui.theme.PrimaryBlue
import com.example.unicase.ui.theme.UnicaseTheme

@OptIn(ExperimentalMaterial3Api::class, androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable
fun ProductDetailScreen(navController: NavController, product: Product) {
    var isFavorited by remember { mutableStateOf(false) }

    // State untuk mengontrol bottom sheet
    var showCartBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val context = LocalContext.current

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
            // Tombol Add to Cart sekarang hanya memicu bottom sheet
            BottomBar(
                price = product.price,
                onAddToCartClicked = { showCartBottomSheet = true }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            item { ProductImageCarousel(images = product.images ?: emptyList()) }
            item {
                ProductInfoSection(
                    category = "Case Category",
                    name = product.fullName,
                    rating = product.rating ?: 0.0,
                    reviewCount = product.reviewCount ?: 0
                )
            }
            item { ProductDescriptionSection(description = product.description ?: "") }
            item { CustomerReviewSection(reviewCount = product.reviewCount ?: 0) }
        }

        // --- BOTTOM SHEET DI SINI ---
        if (showCartBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showCartBottomSheet = false },
                sheetState = sheetState
            ) {
                AddToCartBottomSheetContent(
                    product = product,
                    onConfirm = { selectedColor, quantity ->
                        // Logika untuk menambahkan ke keranjang
                        val existingItem = globalCartItems.find { it.product.id == product.id && it.product.variant == selectedColor.name }

                        if (existingItem != null) {
                            existingItem.quantity += quantity
                        } else {
                            globalCartItems.add(CartItem(product = product.copy(variant = selectedColor.name), quantity = quantity))
                        }

                        showCartBottomSheet = false
                        Toast.makeText(context, "Dimasukkan ke Keranjang", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
    }
}

// Composable baru untuk konten di dalam bottom sheet
@Composable
fun AddToCartBottomSheetContent(
    product: Product,
    onConfirm: (selectedColor: ColorOption, quantity: Int) -> Unit
) {
    var quantity by rememberSaveable { mutableIntStateOf(1) }
    var selectedColor by remember { mutableStateOf(product.colors?.first() ?: ColorOption("Default", Color.Gray)) }

    Column(modifier = Modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.Bottom) {
            if (product.imageUri != null) {
                AsyncImage(model = product.imageUri, contentDescription = product.name, modifier = Modifier.size(100.dp).clip(RoundedCornerShape(8.dp)), contentScale = ContentScale.Crop)
            } else {
                Image(painter = painterResource(id = product.imageRes), contentDescription = product.name, modifier = Modifier.size(100.dp).clip(RoundedCornerShape(8.dp)))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(product.price, style = MaterialTheme.typography.titleLarge, color = PrimaryBlue, fontWeight = FontWeight.Bold)
                Text("Stok: 39", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
            }
        }
        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

        product.colors?.let { colors ->
            ColorSelectorSection(
                colors = colors,
                selectedColor = selectedColor,
                onColorSelected = { selectedColor = it }
            )
        }
        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Jumlah", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.border(BorderStroke(1.dp, Color.LightGray), RoundedCornerShape(8.dp))
            ) {
                IconButton(onClick = { if (quantity > 1) quantity-- }, modifier = Modifier.size(32.dp)) {
                    Icon(Icons.Default.Remove, "Decrease quantity")
                }
                Text("$quantity", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                IconButton(onClick = { quantity++ }, modifier = Modifier.size(32.dp)) {
                    Icon(Icons.Default.Add, "Increase quantity")
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { onConfirm(selectedColor, quantity) },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Masukkan Keranjang")
        }
    }
}


@OptIn(androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable
fun ProductImageCarousel(images: List<Int>) {
    val pagerState = rememberPagerState(pageCount = { images.size })
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
        ) { page ->
            Image(
                painter = painterResource(id = images[page]),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth()
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color = if (pagerState.currentPage == iteration) PrimaryBlue else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(8.dp)
                )
            }
        }
    }
}

@Composable
fun ProductInfoSection(category: String, name: String, rating: Double, reviewCount: Int) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(category, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        Spacer(modifier = Modifier.height(4.dp))
        Text(name, style = MaterialTheme.typography.headlineSmall, fontFamily = Poppins, color = Color.Black ,fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Star, contentDescription = "Rating", tint = Color(0xFFFFC107))
            Spacer(modifier = Modifier.width(4.dp))
            Text("$rating", fontFamily = Poppins, color = Color.Black , fontWeight = FontWeight.Bold)
            Text(" ($reviewCount reviews)", color = Color.Gray, modifier = Modifier.padding(start = 4.dp))
        }
    }
}

@Composable
fun ProductDescriptionSection(description: String) {
    var isExpanded by remember { mutableStateOf(false) }
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Product Details", style = MaterialTheme.typography.titleMedium, fontFamily = Poppins ,color = Color.Black , fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            maxLines = if (isExpanded) Int.MAX_VALUE else 3
        )
        Text(
            text = if (isExpanded) "Read less" else "Read more",
            color = PrimaryBlue,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 4.dp)
                .clickable { isExpanded = !isExpanded }
        )
    }
}

@Composable
fun ColorSelectorSection(
    colors: List<ColorOption>,
    selectedColor: ColorOption,
    onColorSelected: (ColorOption) -> Unit
) {
    Column {
        Text("Color", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(colors) { colorOption ->
                val isSelected = selectedColor.name == colorOption.name
                Button(
                    onClick = { onColorSelected(colorOption) },
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, if(isSelected) PrimaryBlue else Color.LightGray),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = if(isSelected) PrimaryBlue else Color.Gray)
                ) {
                    Text(colorOption.name)
                }
            }
        }
    }
}

@Composable
fun CustomerReviewSection(reviewCount: Int) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Customer reviews ($reviewCount)", style = MaterialTheme.typography.titleMedium, fontFamily = Poppins, color = Color.Black , fontWeight = FontWeight.Bold)
            Text("See all", color = PrimaryBlue, fontWeight = FontWeight.Bold, modifier = Modifier.clickable { /*TODO*/ })
        }
        Spacer(modifier = Modifier.height(16.dp))
        ReviewCard()
    }
}

@Composable
fun ReviewCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        border = BorderStroke(1.dp, Color.LightGray),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("N**a", fontFamily = Poppins, color = Color.Black ,fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.weight(1f))
                Row {
                    repeat(5) {
                        Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFFFC107), modifier = Modifier.size(16.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text("Variant: Black - Ipong 200 pro mex mex Silitcon ketupat", fontSize = 12.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Great, just as ordered. I'll place another order here tomorrow", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = painterResource(id = R.drawable.gambar_ws1),
                contentDescription = "Review image",
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        }
    }
}

@Composable
fun BottomBar(price: String, onAddToCartClicked: () -> Unit) {
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
                Text(price, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = PrimaryBlue)
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(onClick = { /*TODO*/ }) { Text("Buy") }
                Button(onClick = onAddToCartClicked) {
                    Icon(Icons.Default.ShoppingCart, contentDescription = "Add to Cart")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Add to Cart")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductDetailScreenPreview() {
    UnicaseTheme {
        ProductDetailScreen(rememberNavController(), dummyProducts.first())
    }
}