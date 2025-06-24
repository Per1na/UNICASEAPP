// Lokasi: app/src/main/java/com/example/unicase/features/wishlist/WishlistScreen.kt

package com.example.unicase.features.wishlist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.unicase.model.Product
import com.example.unicase.model.dummyProducts
import com.example.unicase.ui.theme.PrimaryBlue
import com.example.unicase.ui.theme.UnicaseTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishlistScreen(navController: NavController) {
    val wishlistItems = dummyProducts.take(4)

    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    var bottomSheetType by remember { mutableStateOf("sort") }
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Wishlist", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).padding(horizontal = 16.dp)) {

            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                placeholder = { Text("Search item") },
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = "Search Icon")
                },
                shape = RoundedCornerShape(12.dp)
            )

            // --- BAGIAN YANG DIPERBAIKI DENGAN SURFACE ---
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Tombol "All"
                Surface(
                    modifier = Modifier.weight(1f).clickable { /*TODO*/ },
                    shape = CircleShape,
                    border = BorderStroke(1.dp, Color.Gray)
                ) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(vertical = 8.dp)) {
                        Text("All", color = PrimaryBlue)
                    }
                }

                // Tombol "Category"
                Surface(
                    modifier = Modifier.weight(1f).clickable {
                        bottomSheetType = "category"
                        showBottomSheet = true
                    },
                    shape = CircleShape,
                    border = BorderStroke(1.dp, Color.Gray)
                ) {
                    Row(
                        modifier = Modifier.padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text("Category", color = PrimaryBlue)
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Category Filter", tint = PrimaryBlue)
                    }
                }

                // Tombol "Sort"
                Surface(
                    modifier = Modifier.weight(1f).clickable {
                        bottomSheetType = "sort"
                        showBottomSheet = true
                    },
                    shape = CircleShape,
                    border = BorderStroke(1.dp, Color.Gray)
                ) {
                    Row(
                        modifier = Modifier.padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text("Sort", color = PrimaryBlue)
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Sort Filter", tint = PrimaryBlue)
                    }
                }
            }
            // ------------------------------------------


            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                items(wishlistItems) { product ->
                    WishlistProductCard(product = product)
                }
            }
        }

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState
            ) {
                when (bottomSheetType) {
                    "sort" -> SortBottomSheetContent()
                    "category" -> CategoryBottomSheetContent()
                }
            }
        }
    }
}


// --- Preview (pastikan semua fungsi helper ada di file ini) ---
@Composable
fun SortBottomSheetContent() {
    val radioOptions = listOf("Lowest Price", "Highest Price")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Sort by", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(bottom = 16.dp))
        radioOptions.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) },
                        role = Role.RadioButton
                    )
                    .padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = null
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { /* TODO: Apply filter*/ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Apply Filters")
        }
    }
}

@Composable
fun CategoryBottomSheetContent() {
    val categories = listOf("Xiomi", "Iphone", "Samsung", "Oppo", "VIVO")
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Filter by Category", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(bottom = 16.dp))
        categories.forEach { category ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                var isChecked by remember { mutableStateOf(false) }
                Checkbox(checked = isChecked, onCheckedChange = { isChecked = it })
                Text(category, modifier = Modifier.padding(start = 16.dp))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { /* TODO: Apply filter*/ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Apply Filters")
        }
    }
}


@Composable
fun WishlistProductCard(product: Product) {
    Card(shape = RoundedCornerShape(8.dp)) {
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
                Text(product.name, fontWeight = FontWeight.Bold, maxLines = 1, fontSize = 14.sp)
                Text(product.price, fontWeight = FontWeight.SemiBold, color = PrimaryBlue, modifier = Modifier.padding(vertical = 4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Star, contentDescription = "Rating", tint = Color(0xFFFFC107), modifier = Modifier.size(14.dp))
                        Text(product.rating.toString(), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                    OutlinedButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.height(32.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Add to cart", modifier = Modifier.size(18.dp))
                        Text("Cart", fontSize = 12.sp)
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun WishlistScreenPreview() {
    UnicaseTheme {
        WishlistScreen(navController = rememberNavController())
    }
}