package com.example.unicase.viewmodel

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unicase.repository.ProductImageResponse
import com.example.unicase.repository.ProductRepository
import com.example.unicase.repository.ProductResponse
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    private val _products = mutableStateOf<List<ProductResponse>>(emptyList())
    val products: State<List<ProductResponse>> = _products

    private val _productDetail = mutableStateOf<ProductResponse?>(null)
    val productDetail: State<ProductResponse?> = _productDetail

    private val _productImages = mutableStateOf<List<ProductImageResponse>>(emptyList())
    val productImages: State<List<ProductImageResponse>> = _productImages

    private val _loading = mutableStateOf(false)
    val loading: State<Boolean> = _loading

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    private val _searchResults = mutableStateOf<List<ProductResponse>>(emptyList())
    val searchResults: State<List<ProductResponse>> = _searchResults

    init {
        fetchAllProducts()
    }

    fun fetchAllProducts() {
        viewModelScope.launch {
            _loading.value = true
            try {
                _products.value = repository.getProducts()
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Gagal memuat produk: ${e.message}"
                Log.e("ProductViewModel", "Error fetchAllProducts", e)
            } finally {
                _loading.value = false
            }
        }
    }

    fun getProductById(productId: Int) {
        viewModelScope.launch {
            try {
                _productDetail.value = repository.getProductById(productId)
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Gagal memuat detail produk: ${e.message}"
                Log.e("ProductViewModel", "Error getProductById", e)
            }
        }
    }

    fun getProductImages(productId: Int) {
        viewModelScope.launch {
            try {
                val response = repository.getProductImages(productId)
                Log.d("IMAGE_RESPONSE", response.toString())
                _productImages.value = response
            } catch (e: Exception) {
                Log.e("IMAGE_ERROR", "Gagal memuat gambar: ${e.message}")
                _error.value = "Gagal memuat gambar produk: ${e.message}"
            }
        }
    }
    fun searchProductsLocally(query: String) {
        _searchResults.value = if (query.isBlank()) {
            emptyList()
        } else {
            _products.value.filter {
                it.name?.contains(query, ignoreCase = true) == true ||
                        it.description?.contains(query, ignoreCase = true) == true
            }
        }
    }
    fun getProductsByCategory(categoryId: Int) {
        viewModelScope.launch {
            try {
                _products.value = repository.getProductsByCategory(categoryId)
            } catch (e: Exception) {
                Log.e("ProductViewModel", "Error loading products by category: ${e.message}")
            }
        }
    }

}
