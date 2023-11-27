package com.example.vk_kotlin_lvl1

import android.util.Log
import com.example.vk_kotlin_lvl1.models.ImageItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf

class Repository {
    private val imageList = MutableStateFlow(listOf<ImageItem>(
        ImageItem("1si","https://cdn2.thecatapi.com/images/1si.jpg",500,376),
        ImageItem("9vg","https://cdn2.thecatapi.com/images/9vg.jpg",667,1000),
        ImageItem("bef","https://cdn2.thecatapi.com/images/bef.jpg",500,333),
        ImageItem("9vg","https://cdn2.thecatapi.com/images/9vg.jpg",667,1000),
        ImageItem("bgk","https://cdn2.thecatapi.com/images/bgk.gif",500,333),
        ImageItem("bgk","https://cdn2.thecatapi.com/images/bgk.gif",500,333),
        ImageItem("9vg","https://cdn2.thecatapi.com/images/9vg.jpg",667,1000),
        ImageItem("btm","https://cdn2.thecatapi.com/images/btm.jpg",450,338),
        ImageItem("btm","https://cdn2.thecatapi.com/images/btm.jpg",450,338),
        ImageItem("ci9","https://cdn2.thecatapi.com/images/ci9.jpg",500,374),

        ImageItem("dj6","https://cdn2.thecatapi.com/images/dj6.jpg",1024,768),
        ImageItem("9vg","https://cdn2.thecatapi.com/images/9vg.jpg",667,1000),
        ImageItem("MTY0MDgwOA","https://cdn2.thecatapi.com/images/MTY0MDgwOA.jpg",500,338),
        ImageItem("MTY0MDgwOA","https://cdn2.thecatapi.com/images/MTY0MDgwOA.jpg",500,338),
        ImageItem("MTY1MjExMA","https://cdn2.thecatapi.com/images/MTY1MjExMA.jpg",500,375)
    ))

    fun getList() = imageList

    suspend fun setList(list: List<ImageItem>) {
        imageList.emit(list)
    }

    suspend fun getSmth() {
        Log.d("getSMTH fun", "launched")
        delay(5000L)
        imageList.emit(listOf())
    }


}