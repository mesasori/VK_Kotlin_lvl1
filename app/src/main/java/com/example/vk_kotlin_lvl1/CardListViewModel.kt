package com.example.vk_kotlin_lvl1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vk_kotlin_lvl1.models.ImageItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CardListViewModel: ViewModel() {
    private val _list = MutableStateFlow(listOf<ImageItem>())
    val list = _list.asStateFlow()
    private val repository = Repository()

    init {
        getUpdates()
    }

    private fun getUpdates() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getList().collect {
                _list.emit(it)
            }
        }
    }

    fun setList(list: List<ImageItem>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.setList(list)
        }
    }
}