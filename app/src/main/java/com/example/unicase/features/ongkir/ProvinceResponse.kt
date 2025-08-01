package com.example.unicase.features.ongkir

data class ProvinceResponse(
    val rajaongkir: ProvinceResults
)

data class ProvinceResults(
    val results: List<Province>
)

data class Province(
    val province_id: String?,
    val province: String?
)

