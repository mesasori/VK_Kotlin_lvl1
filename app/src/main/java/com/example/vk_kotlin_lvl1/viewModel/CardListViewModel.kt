package com.example.vk_kotlin_lvl1.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vk_kotlin_lvl1.models.ImageModel
import com.example.vk_kotlin_lvl1.repository.Repository
import com.example.vk_kotlin_lvl1.utils.AppConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CardListViewModel: ViewModel() {
    private val repository = Repository(AppConfig.ApiService())

    val _list = MutableStateFlow(listOf<ImageModel>())

    init {
        getImages(20)
    }

    fun getImages(limit: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getImages(limit).collect {
                _list.value = it
            }
        }
    }
}