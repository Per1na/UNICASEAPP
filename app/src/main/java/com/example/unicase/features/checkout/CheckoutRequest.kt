package com.example.unicase.features.checkout

data class CheckoutRequest(
    val cart_ids: List<Int>,
    val shipping_address_id: Int,
    val payment_method_id: Int,
    val courier: String,
    val courier_service: String
)


