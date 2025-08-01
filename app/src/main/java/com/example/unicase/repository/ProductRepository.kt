package com.example.unicase.repository

import com.example.unicase.network.ApiService

class ProductRepository(private val apiService: ApiService) {

    suspend fun getProducts(): List<ProductResponse> {
        return apiService.getProducts()
    }
    suspend fun getProductById(id: Int): ProductResponse {
        return apiService.getProductById(id)
    }
    suspend fun getProductImages(productId: Int): List<ProductImageResponse> {
        return apiService.getProductImages(productId)
    }
    suspend fun getProductsByCategory(categoryId: Int): List<ProductResponse> {
        return apiService.getProductsByCategory(categoryId)
    }


}

