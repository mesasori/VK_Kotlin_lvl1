package com.example.vk_kotlin_lvl1.network

import com.example.vk_kotlin_lvl1.models.ImageModel

data class ImageApiState(val status: Status, val data: List<ImageModel>, val error: Error) {
    companion object {
        fun success(data: List<ImageModel>): ImageApiState {
            return ImageApiState(Status.SUCCESS, data, Error.NONE)
        }

        fun error(err: Error): ImageApiState {
            return ImageApiState(Status.ERROR, listOf(), err)
        }

        fun loading(): ImageApiState {
            return ImageApiState(Status.LOADING, listOf(), Error.NONE)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

enum class Error {
    INTERNET,
    REQUEST,
    NONE
}