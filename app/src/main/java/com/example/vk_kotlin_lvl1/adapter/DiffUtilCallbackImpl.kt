package com.example.vk_kotlin_lvl1.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.vk_kotlin_lvl1.models.ImageModel

class DiffUtilCallbackImpl(
    private val oldItems: List<ImageModel>,
    private val newItems: List<ImageModel>
): DiffUtil.Callback() {
    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]
        return oldItem.url == newItem.url && oldItem.id == newItem.id
                && oldItem.width == newItem.width && oldItem.height == newItem.height
    }
}
