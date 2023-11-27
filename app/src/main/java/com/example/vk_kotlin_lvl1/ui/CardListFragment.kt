package com.example.vk_kotlin_lvl1.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vk_kotlin_lvl1.viewModel.CardListViewModel
import com.example.vk_kotlin_lvl1.R
import com.example.vk_kotlin_lvl1.adapter.MyCardAdapter
import com.example.vk_kotlin_lvl1.models.ImageModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CardListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val adapter = MyCardAdapter()
    private lateinit var viewModel: CardListViewModel
    private lateinit var addButton: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CardListViewModel::class.java)
        addButton = view.findViewById(R.id.load)

        getImages()

        recyclerView = view.findViewById(R.id.rv_plate)
        val layoutManager = GridLayoutManager(view.context, 3)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        addButton.setOnClickListener {
            viewModel.addImages(10)
        }

    }

    private fun getImages() {
        lifecycleScope.launch {
            viewModel._list.collect {
                setUpAdapter(it)
            }
        }
    }

    private fun setUpAdapter(items: List<ImageModel>) {
        adapter.imagesList = items
    }

}