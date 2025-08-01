package com.example.unicase.repository

import com.example.unicase.network.ApiService
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getCategories(): List<CategoryResponse> {
        return apiService.getCategories()
    }
}

