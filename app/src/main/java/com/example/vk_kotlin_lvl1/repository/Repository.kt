package com.example.vk_kotlin_lvl1.repository

import com.example.vk_kotlin_lvl1.models.ImageModel
import com.example.vk_kotlin_lvl1.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class Repository(private val apiService: ApiService) {
    fun addImages(): Flow<List<ImageModel>> {

    val newImages: Flow<List<ImageModel>> = flow {
        val newImages = apiService.getImages()
        emit(newImages)
    }.flowOn(Dispatchers.IO)

    suspend fun getImages(limit: Int): Flow<List<ImageModel>> {
        return flow {
            val list = apiService.getImages()
            emit(list)
        }.flowOn(Dispatchers.IO)
    }
}