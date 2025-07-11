package com.example.unicase.repository

import com.example.unicase.repository.LoginRequest
import com.example.unicase.repository.LoginResponse
import com.example.unicase.repository.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("register")
    suspend fun register(@Body request: RegisterRequest): Response<LoginResponse>

}
