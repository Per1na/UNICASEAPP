package com.example.unicase.network


import com.example.unicase.features.ongkir.CityResponse
import com.example.unicase.features.ongkir.ProvinceResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RajaOngkirApiService {
    @GET("rajaongkir/provinces")
    suspend fun getProvinces(): Response<ProvinceResponse>

    @GET("rajaongkir/cities")
    suspend fun getCities(@Query("province_id") provinceId: String): Response<CityResponse>

}
