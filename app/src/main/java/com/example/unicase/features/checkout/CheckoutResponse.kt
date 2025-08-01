package com.example.unicase.features.checkout

data class CheckoutResponse(
    val status: Boolean,
    val message: String,
    val checkout: CheckoutData
)

data class CheckoutData(
    val id: Long,
    val user_id: Long,
    val total: String,
    val status: String
    // tambahkan field lain jika dibutuhkan
)


