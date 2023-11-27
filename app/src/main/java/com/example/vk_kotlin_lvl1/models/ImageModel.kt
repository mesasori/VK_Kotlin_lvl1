package com.example.vk_kotlin_lvl1.models

import com.google.gson.annotations.SerializedName

data class ImageModel(
    @SerializedName("id")
    val id: String = "",
    @SerializedName("url")
    val url: String = "",
    @SerializedName("width")
    val width: Int = 0,
    @SerializedName("height")
    val height: Int = 0
)