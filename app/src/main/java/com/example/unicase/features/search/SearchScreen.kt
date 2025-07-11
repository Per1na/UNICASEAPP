package com.example.unicase.features.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.unicase.repository.ApiClient
import com.example.unicase.repository.ProductRepository
import com.example.unicase.ui.theme.UnicaseTheme
import com.example.unicase.viewmodel.ProductViewModel
import com.example.unicase.viewmodel.ProductViewModelFactory
import com.example.unicase.model.ProductCard
import androidx.compose.material3.Icon
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.unicase.R
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.example.unicase.ui.theme.PrimaryBlue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchScreen(navController: NavController) {
    val viewModel: ProductViewModel = viewModel(
        factory = ProductViewModelFactory(ProductRepository(ApiClient.apiService))
    )

    var searchQuery by remember { mutableStateOf("") }
    var submittedQuery by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    val searchResults by viewModel.searchResults
    val error by viewModel.error
    val keyboardController = LocalSoftwareKeyboardController.current


    // Dummy riwayat pencarian
    val searchHistory = remember {
        mutableStateListOf("Case ip 11", "Case ip", "Case Samsung")
    }




    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Search Bar
        // Search Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }

            // TextField memanjang ke kanan
            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .weight(1f)
                    .focusRequester(focusRequester),
                placeholder = { Text("Search your case") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        submittedQuery = searchQuery
                        if (searchQuery.isNotEmpty()) {
                            viewModel.searchProductsLocally(searchQuery)
                            if (!searchHistory.contains(searchQuery)) {
                                searchHistory.add(0, searchQuery)
                            }
                        }
                        keyboardController?.hide()
                    }
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                )
            )

            // ICON hanya tampil setelah user tekan search
            if (submittedQuery.isNotEmpty()) {
                Spacer(modifier = Modifier.width(4.dp))

                IconButton(onClick = {
                    navController.navigate("cart")
                }) {
                    Icon(Icons.Default.ShoppingCart, contentDescription = "Cart", tint = PrimaryBlue)
                }

                IconButton(onClick = {
                    // TODO: Implement Filter
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.filter),
                        contentDescription = "Filter",
                        tint = PrimaryBlue
                    )
                }
            }
        }


        if (error != null) {
            Text("Error: $error", color = Color.Red, modifier = Modifier.padding(16.dp))
        }

        if (submittedQuery.isEmpty()) {
            // Tampilkan Riwayat
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Search History", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                searchHistory.forEach { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clickable {
                                searchQuery = item
                                submittedQuery = item
                                viewModel.searchProductsLocally(item)
                            },
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(item)
                        IconButton(onClick = { searchHistory.remove(item) }) {
                            Icon(Icons.Default.Close, contentDescription = "Remove", tint = Color.Gray)
                        }
                    }
                }
            }
        } else {
            // Header: Result & Icons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Result (${searchResults.size} founds)", fontWeight = FontWeight.Bold)

            }

            // Grid Produk
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(searchResults) { product ->
                    ProductCard(
                        product = product,
                        onClick = { navController.navigate("product_detail/${product.id}") }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    UnicaseTheme {
        SearchScreen(navController = rememberNavController())
    }
}
