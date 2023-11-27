package com.example.vk_kotlin_lvl1.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vk_kotlin_lvl1.R
import com.example.vk_kotlin_lvl1.models.ImageModel

class MyCardAdapter(): RecyclerView.Adapter<MyCardAdapter.CardViewHolder>() {

    var imagesList = listOf<ImageModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
            Log.d("NOTIFY", "N+")
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCardAdapter.CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)

        return CardViewHolder(view)
    }

    inner class CardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)

        fun onBind(position: Int) {
            val item = imagesList[position]
            Glide.with(itemView.context).load(item.url).dontTransform()
                .into(imageView)

        }
    }

    override fun onBindViewHolder(holder: MyCardAdapter.CardViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount() = imagesList.size

}