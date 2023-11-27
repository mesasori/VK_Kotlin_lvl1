package com.example.vk_kotlin_lvl1

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vk_kotlin_lvl1.models.ImageModel
import com.example.vk_kotlin_lvl1.repository.Repository
import com.example.vk_kotlin_lvl1.utils.AppConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CardListViewModel: ViewModel() {
    private val _list = MutableStateFlow(listOf<ImageModel>())
    val list = _list.asStateFlow()
    private val repository = Repository(AppConfig.ApiService())
    private var count = 0


    init {
        getList()
    }

    private fun getList() {
        Log.d("Thread Outside", Thread.currentThread().name)
        viewModelScope.launch {
            repository.getList().collect {
                _list.emit(_list.value + it)
            }
        }
    }

    fun loadImages() {
        viewModelScope.launch {
            repository.loadImages()
        }
    }
}