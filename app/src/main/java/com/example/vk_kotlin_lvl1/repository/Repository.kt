package com.example.vk_kotlin_lvl1.repository

import com.example.vk_kotlin_lvl1.models.ImageModel
import com.example.vk_kotlin_lvl1.network.ApiService
import com.example.vk_kotlin_lvl1.network.ImageApiState
import com.example.vk_kotlin_lvl1.network.Status
import kotlinx.coroutines.flow.MutableStateFlow

class Repository(private val apiService: ApiService) {
    private val imageList = MutableStateFlow(
        ImageApiState(Status.LOADING, listOf<ImageModel>(), "")
    )
    fun getList() = imageList

    suspend fun loadImages() {
        val loadedList = apiService.loadImages()
        if (imageList.value.data != null) imageList.emit(ImageApiState.success(loadedList + (imageList.value.data as List<ImageModel>)))
        else imageList.emit(ImageApiState.success(loadedList))
    }


}