package com.example.unicase.features.product

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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.unicase.model.ColorOption
import com.example.unicase.model.Product
import com.example.unicase.R
import com.example.unicase.model.dummyProducts
import com.example.unicase.ui.theme.Poppins
import com.example.unicase.ui.theme.PrimaryBlue
import com.example.unicase.ui.theme.UnicaseTheme

@OptIn(ExperimentalMaterial3Api::class, androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable
fun ProductDetailScreen(navController: NavController, product: Product) {

    Scaffold(
        // Point 1: TopBar tanpa teks
        topBar = {
            TopAppBar(
                title = { Text("Product Detail", fontWeight = FontWeight.Bold)},
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { /* TODO */ }) {
                        Icon(Icons.Default.Favorite, contentDescription = "Wishlist", tint = PrimaryBlue)
                    }
                }
            )
        },
        // Point 8: BottomBar khusus untuk halaman ini
        bottomBar = {
            BottomBar(price = product.price)
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            // Point 2: Image Carousel
            item {
                ProductImageCarousel(images = product.images)
            }
            // Point 3: Info Produk (Kategori, Nama, Rating)
            item {
                ProductInfoSection(
                    category = "Case Category", // Placeholder
                    name = product.fullName,
                    rating = product.rating,
                    reviewCount = product.reviewCount
                )
            }
            // Point 4: Deskripsi Produk
            item {
                ProductDescriptionSection(description = product.description)
            }
            // Point 5: Pilihan Warna
            item {
                ColorSelectorSection(colors = product.colors)
            }
            // Point 6 & 7: Ulasan Pelanggan
            item {
                CustomerReviewSection(reviewCount = product.reviewCount)
            }
        }
    }
}

// Point 2: Composable untuk Image Carousel
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
        // Indikator titik
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(vertical = 8.dp)
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


// Point 3: Composable untuk Info Produk
@Composable
fun ProductInfoSection(category: String, name: String, rating: Double, reviewCount: Int) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(category, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        Spacer(modifier = Modifier.height(4.dp))
        Text(name, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.SemiBold, color = Color.Black)
        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Star, contentDescription = "Rating", tint = Color(0xFFFFC107))
            Spacer(modifier = Modifier.width(4.dp))
            Text("$rating", fontWeight = FontWeight.Bold, color = Color.Black)
            Text(" ($reviewCount reviews)", color = Color.Gray, modifier = Modifier.padding(start = 4.dp))
        }
    }
}

// Point 4: Composable untuk Deskripsi
@Composable
fun ProductDescriptionSection(description: String) {
    var isExpanded by remember { mutableStateOf(false) }
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Product Details", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Color.Black)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = description,
            fontFamily = Poppins,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            style = MaterialTheme.typography.bodyMedium,
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

// Point 5: Composable untuk Pilihan Warna
@Composable
fun ColorSelectorSection(colors: List<ColorOption>) {
    var selectedColorName by remember { mutableStateOf(colors.first().name) }
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Color", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(colors) { colorOption ->
                val isSelected = selectedColorName == colorOption.name
                Button(
                    onClick = { selectedColorName = colorOption.name },
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

// Point 6 & 7: Composable untuk Ulasan
@Composable
fun CustomerReviewSection(reviewCount: Int) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Customer reviews ($reviewCount)", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text("See all", color = PrimaryBlue, fontWeight = FontWeight.Bold, modifier = Modifier.clickable { /*TODO*/ })
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Contoh satu kartu ulasan
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
                Text("N**a", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.weight(1f))
                Row {
                    repeat(5) {
                        Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFFFC107), modifier = Modifier.size(16.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text("Nina", fontWeight = FontWeight.Bold, color = Color.Black)
            Spacer(modifier = Modifier.height(4.dp))
            Text("Variant: Black - Ipong 200 pro mex mex Silitcon ketupat", fontSize = 12.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Great, just as ordered. I'll place another order here tomorrow", style = MaterialTheme.typography.bodyMedium, color = Color.Black)
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = painterResource(id = R.drawable.gambar_ws1), // Siapkan gambar ini
                contentDescription = "Review image",
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        }
    }
}


// Point 8: Composable untuk Bottom Bar
@Composable
fun BottomBar(price: String) {
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
                Text(price, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Normal, color = PrimaryBlue)
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(onClick = { /*TODO*/ }) {
                    Text("Buy")
                }
                Button(onClick = { /*TODO*/ }) {
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
        ProductDetailScreen(navController = rememberNavController(), product = dummyProducts.first())
    }
}