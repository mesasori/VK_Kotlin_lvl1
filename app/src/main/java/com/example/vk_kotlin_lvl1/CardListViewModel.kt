package com.example.vk_kotlin_lvl1

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vk_kotlin_lvl1.models.ImageModel
import com.example.vk_kotlin_lvl1.network.ImageApiState
import com.example.vk_kotlin_lvl1.network.Status
import com.example.vk_kotlin_lvl1.repository.Repository
import com.example.vk_kotlin_lvl1.utils.AppConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CardListViewModel: ViewModel() {
    private val _list = MutableStateFlow(ImageApiState(Status.LOADING, listOf<ImageModel>(), ""))
    val list = _list.asStateFlow()
    private val repository = Repository(AppConfig.ApiService())


    init {
        getList()
    }

    private fun getList() {
        _list.value = ImageApiState.loading()
        viewModelScope.launch {

            repository.getList()
                .catch { _list.value = ImageApiState.error(it.message.toString()) }
                .collect {
                    _list.value = ImageApiState.success(it.data)
                }
        }
    }

    fun loadImages() {
        _list.value = ImageApiState.loading()
        viewModelScope.launch {
            Log.d("ViewModel", "Before loadImages")
            repository.loadImages()
            Log.d("ViewModel", "After loadImages")
        }
        Log.d("ViewModel", "After launch")
    }
}