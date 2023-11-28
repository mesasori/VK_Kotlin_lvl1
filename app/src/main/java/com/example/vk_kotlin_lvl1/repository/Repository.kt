package com.example.vk_kotlin_lvl1.repository

import com.example.vk_kotlin_lvl1.network.ApiService
import com.example.vk_kotlin_lvl1.network.ImageApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class Repository(private val apiService: ApiService) {
    private val loadLimit = 100
    suspend fun loadImages(): Flow<ImageApiState> {
        return flow {
            try {
                val loadedList = apiService.loadImages(limit = loadLimit)
                emit(ImageApiState.success(loadedList))
            } catch (e: Exception) {
                throw e
            }
        }.flowOn(Dispatchers.IO)
    }


}