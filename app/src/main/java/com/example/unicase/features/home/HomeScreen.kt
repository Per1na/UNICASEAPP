// Lokasi: app/src/main/java/com/example/unicase/features/home/HomeScreen.kt

package com.example.unicase.features.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.unicase.R
import com.example.unicase.model.Product
import com.example.unicase.model.dummyProducts
import com.example.unicase.ui.theme.PrimaryBlue


val categories = listOf("Minimalism", "Cartoon", "Anime", "Geometric", "Abstract")

@Composable
fun HomeScreen(navController: NavController) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Bagian Header
        item(span = { GridItemSpan(maxLineSpan) }) {
            Column {
                HomeTopBar(navController = navController)
                CustomBanner()
                CategorySection()
            }
        }

        // Grid Produk
        items(dummyProducts.size) { index ->
            ProductCard(
                product = dummyProducts[index],
                onClick = {
                    // Navigasi ke halaman detail. Nantinya kita akan teruskan ID.
                    // Pastikan rute "product_detail" sudah ada di NavHost Anda.
                    navController.navigate("product_detail/${dummyProducts[index].id}")
                }
            )
        }
    }
}

@Composable
fun HomeTopBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .background(Color.LightGray.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                .clickable { navController.navigate("search") }
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.Gray)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Search", color = Color.Gray)
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            IconButton(onClick = { navController.navigate("notification") }) {
                Icon(Icons.Default.Notifications, contentDescription = "Notifications", tint = PrimaryBlue)
            }
            IconButton(onClick = { navController.navigate("cart") }) {
                Icon(Icons.Default.ShoppingCart, contentDescription = "Shopping Cart", tint = PrimaryBlue)
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.Person, contentDescription = "Profile", tint = PrimaryBlue)
            }
        }
    }
}

@Composable
fun CustomBanner() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = PrimaryBlue)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(color = Color.White.copy(alpha = 0.3f), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Custom Case",
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Custom Your Case",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun CategorySection() {
    var selectedCategoryIndex by remember { mutableStateOf(0) }
    Column(modifier = Modifier.padding(vertical = 16.dp)) {
        Text("Case Category", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 4.dp)
        ) {
            items(categories.size) { index ->
                val isSelected = selectedCategoryIndex == index
                Button(
                    onClick = {
                        selectedCategoryIndex = index
                    },
                    shape = CircleShape,
                    contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isSelected) PrimaryBlue else Color.LightGray.copy(alpha = 0.5f),
                        contentColor = if (isSelected) Color.White else Color.Black
                    ),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
                ) {
                    Text(text = categories[index])
                }
            }
        }
    }
}

@Composable
fun ProductCard(product: Product, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Column {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(product.name, fontWeight = FontWeight.Bold, maxLines = 1)
                Text(product.variant, fontSize = 12.sp, color = Color.Gray, maxLines = 1)
                Text(product.price, fontWeight = FontWeight.Bold, color = PrimaryBlue)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Star, contentDescription = "Rating", tint = Color(0xFFFFC107), modifier = Modifier.size(14.dp))
                    Text(product.rating.toString(), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}