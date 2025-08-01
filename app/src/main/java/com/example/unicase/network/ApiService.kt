package com.example.unicase.network

import com.example.unicase.features.checkout.CheckoutData
import com.example.unicase.features.checkout.CheckoutRequest
import com.example.unicase.features.checkout.CheckoutResponse
import com.example.unicase.repository.CategoryResponse
import com.example.unicase.repository.ProductImageResponse
import com.example.unicase.repository.ProductResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {
    @GET("products")
    suspend fun getProducts(): List<ProductResponse>
    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): ProductResponse
    @GET("products/{productId}/images")
    suspend fun getProductImages(@Path("productId") productId: Int): List<ProductImageResponse>
    @GET("categories")
    suspend fun getCategories(): List<CategoryResponse>
    @GET("products/category/{id}")
    suspend fun getProductsByCategory(@Path("id") categoryId: Int): List<ProductResponse>

    @Multipart
    @POST("custom-cases")
    suspend fun submitCustomCase(
        @Part("user_id") userId: RequestBody,
        @Part("case_type") caseType: RequestBody,
        @Part("print_effect") printEffect: RequestBody,
        @Part("brand_id") brandId: RequestBody,
        @Part("brand_type_id") brandTypeId: RequestBody,
        @Part("description") description: RequestBody,
        @Part imageFile: MultipartBody.Part
    ): Response<Any>

    @POST("checkouts")
    suspend fun postCheckout(
        @Body request: CheckoutRequest
    ): Response<CheckoutResponse>


    @GET("checkouts")
    suspend fun getCheckouts(): Response<List<CheckoutData>>





}

