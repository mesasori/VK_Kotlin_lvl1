package com.example.vk_kotlin_lvl1.network

import com.example.vk_kotlin_lvl1.models.ImageModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/v1/images/search?limit=100&api_key=live_EG2WcJ3UGgnxHbv1yVHRVnbZ1u5OHdEr9lQENhQwNvXoJ9N78ZTj3aOujLezwJlC")
    suspend fun loadImages(): List<ImageModel>
}