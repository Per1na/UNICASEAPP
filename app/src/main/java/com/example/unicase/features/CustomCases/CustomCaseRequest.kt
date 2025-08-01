package com.example.unicase.features.CustomCases

data class CustomCaseRequest(
    val user_id: Int,
    val case_type: String,
    val print_effect: String,
    val brand_id: Int,
    val brand_type_id: Int,
    val description: String
)