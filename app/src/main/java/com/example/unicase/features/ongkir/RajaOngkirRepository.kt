package com.example.unicase.features.ongkir

import com.example.unicase.network.RajaOngkirApiService

class RajaOngkirRepository(private val apiService: RajaOngkirApiService) {

    suspend fun getProvinces() = apiService.getProvinces()

    suspend fun getCities(provinceId: String) = apiService.getCities(provinceId)
}
