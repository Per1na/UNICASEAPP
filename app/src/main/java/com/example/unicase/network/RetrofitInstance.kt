package com.example.unicase.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://unicasestore.my.id/api/"

    private val client = OkHttpClient.Builder().build()

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    val rajaOngkirApi: RajaOngkirApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL) // Laravel kamu yang meneruskan ke rajaongkir
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RajaOngkirApiService::class.java)
    }


}
