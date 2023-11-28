package com.example.vk_kotlin_lvl1.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vk_kotlin_lvl1.CardListViewModel
import com.example.vk_kotlin_lvl1.R
import com.example.vk_kotlin_lvl1.adapter.MyCardAdapter
import com.example.vk_kotlin_lvl1.models.ImageModel
import com.example.vk_kotlin_lvl1.network.Error
import com.example.vk_kotlin_lvl1.network.Status
import com.google.android.material.snackbar.Snackbar
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
        return inflater.inflate(R.layout.fragment_card_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        columns = getOrientation(view)
        recyclerView = view.findViewById(R.id.rv_plate)
        progressBar = view.findViewById(R.id.progressBar)

        startListening()

        /*val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE*/
        recyclerView.layoutManager = GridLayoutManager(view.context, columns)
        recyclerView.adapter = adapter

        if (adapter.imagesList.isEmpty()) {
            viewModel.loadImages()
        }
    }

    private fun startListening() {
        lifecycleScope.launch {
            viewModel.list.collect {
                when (it.status) {
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                    }

                    Status.SUCCESS -> {
                        progressBar.visibility = View.INVISIBLE
                        updateAdapter(it.data)
                    }

                    Status.ERROR -> {
                        progressBar.visibility = View.INVISIBLE
                        val snackBar = Snackbar.make(
                            recyclerView.rootView,
                            "",
                            Snackbar.LENGTH_INDEFINITE
                        )
                        if (it.error == Error.INTERNET) {
                            snackBar.apply {
                                setText(resources.getText(R.string.err_internet))
                                setAction(resources.getText(R.string.snackbar_try)) {
                                    snackBar.dismiss()
                                    viewModel.loadImages()
                                }
                            }
                        } else snackBar.setText(resources.getText(R.string.err_request))
                        snackBar.show()
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