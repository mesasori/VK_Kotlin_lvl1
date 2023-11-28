package com.example.vk_kotlin_lvl1.network

import com.example.vk_kotlin_lvl1.models.ImageModel
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/v1/images/search")
    suspend fun loadImages(
        @Query("limit") limit: Int,
        @Query("api_key") key: String = "live_EG2WcJ3UGgnxHbv1yVHRVnbZ1u5OHdEr9lQENhQwNvXoJ9N78ZTj3aOujLezwJlC"
    ): List<ImageModel>

    companion object {
        private const val BASE_URL = "https://api.thecatapi.com/"
        private var retrofitService: ApiService ?= null

        fun getInstance(): ApiService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                    .build()

                retrofitService = retrofit.create(ApiService::class.java)
            }
            return retrofitService!!
        }
    }
}