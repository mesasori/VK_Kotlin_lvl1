package com.example.vk_kotlin_lvl1.utils

import com.example.vk_kotlin_lvl1.network.ApiService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppConfig {
    private const val BASE_URL = "https://api.thecatapi.com/"

    fun ApiService(): ApiService =
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ApiService::class.java)


}