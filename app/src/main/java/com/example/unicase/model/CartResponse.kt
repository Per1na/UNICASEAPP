package com.example.unicase.model

data class CartResponse(
    val items: List<CartItemResponse>,
    val total_price: Double
)
