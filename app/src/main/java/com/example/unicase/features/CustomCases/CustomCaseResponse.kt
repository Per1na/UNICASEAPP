package com.example.unicase.features.CustomCases

data class CustomCaseResponse(
    val id: Int,
    val user_id: Int,
    val case_type: String,
    val print_effect: String,
    val brand_id: Int,
    val brand_type_id: Int,
    val description: String?,
    val image_url: String,
    val price_case: Int,
    val price_print: Int,
    val total_price: Int
)