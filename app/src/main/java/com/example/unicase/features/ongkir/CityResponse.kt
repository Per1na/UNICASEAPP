package com.example.unicase.features.ongkir

data class CityResponse(
    val rajaongkir: CityResults
)

data class CityResults(
    val results: List<ShippingCity>
)

data class ShippingCity(
    val city_id: String?,
    val city_name: String?
)

