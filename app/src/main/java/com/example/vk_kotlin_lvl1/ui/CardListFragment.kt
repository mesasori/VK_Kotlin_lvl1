package com.example.vk_kotlin_lvl1.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.vk_kotlin_lvl1.CardListViewModel
import com.example.vk_kotlin_lvl1.R
import com.example.vk_kotlin_lvl1.adapter.MyCardAdapter
import com.example.vk_kotlin_lvl1.models.ImageModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CardListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val adapter = MyCardAdapter()
    private val viewModel: CardListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getUpdates()

        recyclerView = view.findViewById(R.id.rv_plate)
        val layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        if (adapter.imagesList.isEmpty()) {
            repeat(3) {
                lifecycleScope.launch(Dispatchers.IO) {
                    viewModel.loadImages()
                }
            }
        }
    }

    private fun getUpdates() {
        lifecycleScope.launch {
            viewModel.list.collect {
                updateAdapter(it)
            }
        }
    }

    private fun updateAdapter(items: List<ImageModel>) {
        adapter.imagesList = items
    }
}