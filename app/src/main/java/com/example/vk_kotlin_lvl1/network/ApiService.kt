package com.example.vk_kotlin_lvl1.network

import com.example.vk_kotlin_lvl1.models.ImageModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/v1/images/search?limit=10")
    suspend fun loadImages(): List<ImageModel>
}