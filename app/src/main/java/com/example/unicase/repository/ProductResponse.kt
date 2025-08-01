package com.example.unicase.repository

data class ProductResponse(
    val id: Int,
    val name: String?,
    val description: String?,
    val price: Double?,
    val image: String?,
    val color: String?,
    val category_id: Int,
    val weight: Int,
    val stock: Int
)

data class Category(
    val id: Int,
    val name: String
)