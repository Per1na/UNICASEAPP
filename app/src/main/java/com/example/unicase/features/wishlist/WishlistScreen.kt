package com.example.unicase.features.wishlist

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.unicase.repository.ApiClient
import com.example.unicase.repository.ProductResponse
import com.example.unicase.repository.ProductRepository
import com.example.unicase.ui.theme.PrimaryBlue
import com.example.unicase.ui.theme.UnicaseTheme
import com.example.unicase.viewmodel.ProductViewModel
import com.example.unicase.viewmodel.ProductViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishlistScreen(navController: NavController) {
    val viewModel: ProductViewModel = viewModel(
        factory = ProductViewModelFactory(ProductRepository(ApiClient.apiService))
    )
    val products by viewModel.products
    val wishlistItems = products.take(4) // anggap ini item wishlist untuk sementara

    var searchQuery by remember { mutableStateOf("") }
    var showBottomSheet by remember { mutableStateOf(false) }
    var bottomSheetType by remember { mutableStateOf("sort") }
    val sheetState = rememberModalBottomSheetState()

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
        Column(modifier = Modifier
            .padding(innerPadding)
            .padding(horizontal = 16.dp)) {

            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                placeholder = { Text("Search item") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                shape = RoundedCornerShape(12.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Surface(
                    modifier = Modifier.weight(1f).clickable { },
                    shape = CircleShape,
                    border = BorderStroke(1.dp, Color.Gray)
                ) {
                    Box(modifier = Modifier.padding(vertical = 8.dp), contentAlignment = Alignment.Center) {
                        Text("All", color = PrimaryBlue)
                    }
                }

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
                        Icon(Icons.Default.ArrowDropDown, contentDescription = null, tint = PrimaryBlue)
                    }
                }

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
                        Icon(Icons.Default.ArrowDropDown, contentDescription = null, tint = PrimaryBlue)
                    }
                }
            }

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

@Composable
fun WishlistProductCard(product: ProductResponse) {
    Card(shape = RoundedCornerShape(8.dp)) {
        Column {
            AsyncImage(
                model = product.image ?: "",
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(product.name ?: "Nama tidak tersedia", fontWeight = FontWeight.Bold, maxLines = 1, fontSize = 14.sp)
                Text("Rp${product.price ?: 0}",
                fontWeight = FontWeight.SemiBold, color = PrimaryBlue, modifier = Modifier.padding(vertical = 4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Star, contentDescription = "Rating", tint = Color(0xFFFFC107), modifier = Modifier.size(14.dp))
                        Text("4.5", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                    OutlinedButton(
                        onClick = { /* Tambahkan ke keranjang di sini */ },
                        modifier = Modifier.height(32.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(18.dp))
                        Text("Cart", fontSize = 12.sp)
                    }
                }
            }
        }
    }
}
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
            onClick = { /* TODO: Apply sort */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Apply Filters")
        }
    }
}
@Composable
fun CategoryBottomSheetContent() {
    val categories = listOf("Xiaomi", "iPhone", "Samsung", "Oppo", "Vivo")
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Filter by Category", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(bottom = 16.dp))
        categories.forEach { category ->
            var isChecked by remember { mutableStateOf(false) }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Checkbox(checked = isChecked, onCheckedChange = { isChecked = it })
                Text(category, modifier = Modifier.padding(start = 16.dp))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { /* TODO: Apply category filter */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Apply Filters")
        }
    }
}

