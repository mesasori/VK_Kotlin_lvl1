package com.example.vk_kotlin_lvl1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vk_kotlin_lvl1.network.ApiService
import com.example.vk_kotlin_lvl1.network.Error
import com.example.vk_kotlin_lvl1.network.ImageApiState
import com.example.vk_kotlin_lvl1.network.Status
import com.example.vk_kotlin_lvl1.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class CardListViewModel: ViewModel(){
    private val _list = MutableStateFlow(ImageApiState(Status.LOADING, listOf(), Error.NONE))
    val list = _list.asStateFlow()
    private val apiService = ApiService.getInstance()
    private val repository = Repository(apiService)

    init {
        loadImages()
    }

    fun loadImages() {
        _list.value = ImageApiState.loading()
        viewModelScope.launch {
            repository.loadImages()
                .catch {
                    var error = Error.NONE
                    it.message?.let {message ->
                        error = if (message.contains("HTTP")) Error.REQUEST
                        else Error.INTERNET
                    }
                    _list.value = ImageApiState.error(error)
                }
                .collect {
                    _list.value = ImageApiState.success(it.data)
                }
        }
    }
}