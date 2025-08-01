package com.example.unicase.features.ongkir

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RajaOngkirViewModel(private val repository: RajaOngkirRepository) : ViewModel() {

    val provinces = mutableStateOf<List<Province>>(emptyList())
    val cities = mutableStateOf<List<ShippingCity>>(emptyList())
    val selectedProvince = mutableStateOf<Province?>(null)
    val selectedCity = mutableStateOf<ShippingCity?>(null)

    fun fetchProvinces() {
        viewModelScope.launch {
            try {
                val response = repository.getProvinces()
                if (response.isSuccessful) {
                    provinces.value = response.body()?.rajaongkir?.results ?: emptyList()
                }
            } catch (e: Exception) {
                // TODO: Error handling
            }
        }
    }

    fun fetchCities(provinceId: String) {
        viewModelScope.launch {
            try {
                val response = repository.getCities(provinceId)
                if (response.isSuccessful) {
                    cities.value = response.body()?.rajaongkir?.results ?: emptyList()
                }
            } catch (e: Exception) {
                // TODO: Error handling
            }
        }
    }
}
