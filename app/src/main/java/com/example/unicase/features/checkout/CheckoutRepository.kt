package com.example.unicase.features.checkout

import com.example.unicase.network.ApiService
import com.example.unicase.network.RetrofitInstance
import retrofit2.Response

class CheckoutRepository(private val apiService: ApiService) {
    suspend fun postCheckout(request: CheckoutRequest): Response<CheckoutResponse> {
        return apiService.postCheckout(request)
    }
}

