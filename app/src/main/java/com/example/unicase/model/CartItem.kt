package com.example.unicase.model

import androidx.compose.runtime.mutableStateListOf
import com.example.unicase.repository.ProductResponse

// Data class untuk item di keranjang
data class CartItem(
    val product: ProductResponse,
    var quantity: Int = 1
)

val cartItems = mutableStateListOf<CartItem>() // global cart list