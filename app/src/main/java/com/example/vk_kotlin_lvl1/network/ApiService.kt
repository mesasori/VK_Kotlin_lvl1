package com.example.vk_kotlin_lvl1.network

import com.example.vk_kotlin_lvl1.BuildConfig
import com.example.vk_kotlin_lvl1.models.ImageModel
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/v1/images/search")
    suspend fun loadImages(
        @Query("limit") limit: Int,
        @Query("api_key") key: String = BuildConfig.API_KEY
    ): List<ImageModel>

    companion object {
        fun getInstance(): ApiService {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
                .create()
        }
    }
}