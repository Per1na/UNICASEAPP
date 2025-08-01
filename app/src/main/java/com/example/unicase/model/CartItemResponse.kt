package com.example.unicase.model

import com.example.unicase.repository.ProductResponse

data class CartItemResponse(
    val id: Int,
    val product: ProductResponse,
    val quantity: Int
)





