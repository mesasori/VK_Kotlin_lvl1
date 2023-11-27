package com.example.vk_kotlin_lvl1.network

data class ImageApiState<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): ImageApiState<T> {
            return ImageApiState(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String): ImageApiState<T> {
            return ImageApiState(Status.ERROR, null, msg)
        }

        fun <T> loading(): ImageApiState<T> {
            return ImageApiState(Status.LOADING, null, null)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}