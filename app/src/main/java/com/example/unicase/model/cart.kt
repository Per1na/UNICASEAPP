package com.example.unicase.model

import androidx.compose.runtime.mutableStateListOf

/**
 * Ini adalah satu-satunya file untuk semua yang berhubungan dengan data keranjang.
 */

// Data class untuk satu item di dalam keranjang
data class CartItem(
    val product: Product,
    var quantity: Int
)
val globalCartItems = mutableStateListOf<CartItem>()

// Ini adalah data dummy yang hanya digunakan untuk @Preview
// agar kita bisa melihat desain keranjang yang sudah terisi.
val dummyCartItems = listOf(
    CartItem(product = dummyProducts.first(), quantity = 1),
    CartItem(product = dummyProducts.getOrNull(1) ?: dummyProducts.first(), quantity = 2),
)