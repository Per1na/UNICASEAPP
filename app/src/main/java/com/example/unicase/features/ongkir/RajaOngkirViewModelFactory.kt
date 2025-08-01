package com.example.unicase.features.ongkir

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RajaOngkirViewModelFactory(
    private val repository: RajaOngkirRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RajaOngkirViewModel(repository) as T
    }
}
