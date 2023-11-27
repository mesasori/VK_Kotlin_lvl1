package com.example.vk_kotlin_lvl1.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vk_kotlin_lvl1.models.ImageModel
import com.example.vk_kotlin_lvl1.repository.Repository
import com.example.vk_kotlin_lvl1.utils.AppConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CardListViewModel: ViewModel() {
    private val repository = Repository(AppConfig.ApiService())

    val _list = MutableStateFlow(listOf<ImageModel>())

    init {
        getImages()
    }

    fun addImages(limit: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addImages(limit).collect {
                _list.value = _list.value + it
            }
        }
    }

    private fun getImages() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getImages().collect {
                _list.emit(it)
            }
        }
    }
}