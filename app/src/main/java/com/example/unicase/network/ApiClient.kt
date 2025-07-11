package com.example.unicase.repository

import com.example.unicase.network.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.getValue


object ApiClient {
    private const val BASE_URL = "https://unicasestore.my.id/api/"

    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val apiService: ApiService by lazy {
        instance.create(ApiService::class.java)
    }


}
