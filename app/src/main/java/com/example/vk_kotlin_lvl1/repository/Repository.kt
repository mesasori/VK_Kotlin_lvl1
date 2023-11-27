package com.example.vk_kotlin_lvl1.repository

import com.example.vk_kotlin_lvl1.models.ImageModel
import com.example.vk_kotlin_lvl1.network.ApiService
import kotlinx.coroutines.flow.MutableStateFlow

class Repository(private val apiService: ApiService) {
    private val imageList = MutableStateFlow(listOf<ImageModel>())
    fun getList() = imageList

    suspend fun loadImages() {
        val loadedList = apiService.loadImages()
        imageList.emit(imageList.value + loadedList)
    }


}