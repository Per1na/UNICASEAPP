package com.example.unicase.model

import androidx.compose.runtime.mutableStateListOf

// Data class untuk item di keranjang
data class CartItem(
    val product: Product,
    var quantity: Int
)

// Ini adalah "keranjang asli" yang dinamis, awalnya kosong
var cartItems = mutableStateListOf<CartItem>()

// INI DATA BOHONGANNYA
// Hanya digunakan untuk tujuan preview dan development awal.
val dummyCartItems = listOf(
    CartItem(product = dummyProducts[0], quantity = 1),
    CartItem(product = dummyProducts[1], quantity = 2),
)