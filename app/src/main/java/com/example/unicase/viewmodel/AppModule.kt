package com.example.unicase.viewmodel

import com.example.unicase.network.ApiService
import com.example.unicase.repository.ApiClient
import com.example.unicase.repository.CategoryRepository
import com.example.unicase.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideApiService(): ApiService {
        return ApiClient.apiService
    }

    @Provides
    fun provideCategoryRepository(apiService: ApiService): CategoryRepository {
        return CategoryRepository(apiService)
    }

    @Provides
    fun provideProductRepository(apiService: ApiService): ProductRepository {
        return ProductRepository(apiService)
    }
}
