package com.example.unicase.features.checkout

import androidx.compose.runtime.mutableStateOf
import com.example.unicase.repository.ProductResponse

// Produk langsung dibeli (dari tombol "Buy")
val directBuyProduct = mutableStateOf<ProductResponse?>(null)
