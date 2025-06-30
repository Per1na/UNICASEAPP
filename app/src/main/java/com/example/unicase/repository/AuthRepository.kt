package com.example.unicase.repository

import retrofit2.Response

class AuthRepository {
    private val api = ApiClient.instance.create(AuthService::class.java)

    suspend fun login(request: LoginRequest): Response<LoginResponse> {
        return api.login(request)
    }

    suspend fun register(request: RegisterRequest): Response<LoginResponse> {
        return api.register(request)
    }
}
