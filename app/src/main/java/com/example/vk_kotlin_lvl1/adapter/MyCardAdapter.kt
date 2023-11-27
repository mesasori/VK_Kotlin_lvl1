package com.example.vk_kotlin_lvl1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vk_kotlin_lvl1.R
import com.example.vk_kotlin_lvl1.models.ImageModel
import com.nostra13.universalimageloader.core.DisplayImageOptions
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration


class MyCardAdapter(): RecyclerView.Adapter<MyCardAdapter.CardViewHolder>() {

    var imagesList = listOf<ImageModel>()
        set(value) {
            val callback = DiffUtilCallbackImpl(
                oldItems = field,
                newItems = value
            )
            field = value
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCardAdapter.CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(com.example.vk_kotlin_lvl1.R.layout.card_item, parent, false)

        return CardViewHolder(view)
    }

    inner class CardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(com.example.vk_kotlin_lvl1.R.id.imageView)

        fun onBind(position: Int) {
            val item = imagesList[position]
            val loader = ImageLoader.getInstance()
            loader.init(ImageLoaderConfiguration.createDefault(itemView.context))
            Glide.with(itemView.context)
                .load(item.url)
                .into(imageView)
        }
    }

    override fun onBindViewHolder(holder: MyCardAdapter.CardViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount() = imagesList.size



}