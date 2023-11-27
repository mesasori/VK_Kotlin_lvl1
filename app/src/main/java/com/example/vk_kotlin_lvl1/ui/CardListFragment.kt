package com.example.vk_kotlin_lvl1.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.vk_kotlin_lvl1.CardListViewModel
import com.example.vk_kotlin_lvl1.R
import com.example.vk_kotlin_lvl1.adapter.MyCardAdapter
import com.example.vk_kotlin_lvl1.models.ImageModel
import com.example.vk_kotlin_lvl1.network.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CardListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val adapter = MyCardAdapter()
    private val viewModel: CardListViewModel by activityViewModels()
    private var columns = 0
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        columns = getOrientation(view)
        recyclerView = view.findViewById(R.id.rv_plate)
        progressBar = view.findViewById(R.id.progressBar)
        getUpdates()

        /*val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE*/
        recyclerView.layoutManager = GridLayoutManager(view.context, columns)
        recyclerView.adapter = adapter

        if (adapter.imagesList.isEmpty()) {
            repeat(10) {
                Log.d("Fragment", "Before launch")
                lifecycleScope.launch(Dispatchers.IO) {
                    Log.d("Fragment", "Inside launch")
                    viewModel.loadImages()
                }
                Log.d("Fragment", "After launch")
            }
        }
    }

    private fun getUpdates() {
        lifecycleScope.launch {
            viewModel.list.collect {
                when (it.status) {
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        Log.d("ListFragment", "LOADING")
                    }

                    Status.SUCCESS -> {
                        progressBar.visibility = View.INVISIBLE
                        if (it.data != null) updateAdapter(it.data)
                        Log.d("ListFragment", "SUCCESS")
                    }

                    Status.ERROR -> {
                        progressBar.visibility = View.INVISIBLE
                        Toast.makeText(recyclerView.rootView.context, "ERROR!!!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun updateAdapter(items: List<ImageModel>) {
        adapter.imagesList = items
    }

    private fun getOrientation(view: View): Int {
        return view.resources.getInteger(R.integer.column_amount)
    }
}