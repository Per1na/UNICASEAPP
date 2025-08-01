package com.example.unicase.network

import com.example.unicase.features.requestcart.CartItemRequest
import com.example.unicase.model.CartItemResponse
import retrofit2.Response
import retrofit2.http.*

interface CartApiService {

    @GET("cart")
    suspend fun getCartItems(): List<CartItemResponse>

    @POST("cart")
    suspend fun addCartItem(@Body item: CartItemRequest): Response<Unit>

    @PUT("cart/{id}")
    suspend fun updateCartItem(
        @Path("id") id: Int,
        @Body item: CartItemRequest
    ): Response<Unit>

    @DELETE("cart/{id}")
    suspend fun deleteCartItem(@Path("id") id: Int): Response<Unit>
}
