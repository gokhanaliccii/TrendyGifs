package com.gokhanaliccii.trendygifs.ui.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.gokhanaliccii.trendygifs.R
import com.gokhanaliccii.trendygifs.core.base.BindableFragment
import com.gokhanaliccii.trendygifs.core.viewmodel.ViewModelFactory
import com.gokhanaliccii.trendygifs.databinding.FragmentGifListBinding
import com.gokhanaliccii.trendygifs.ui.list.adapter.GifListAdapter
import com.gokhanaliccii.trendygifs.util.gifloader.GlideGifLoader
import com.gokhanaliccii.trendygifs.util.recyclerview.RecyclerViewLoadMoreListener

/**
 * Created by gokhan.alici on 24.02.2019
 */
class GifListFragment : BindableFragment<FragmentGifListBinding, GifListViewModel>() {

    override val layoutRes: Int
        get() = R.layout.fragment_gif_list

    override val viewModelFactory: ViewModelProvider.Factory?
        get() = ViewModelFactory()

    private lateinit var loadMoreListener: RecyclerViewLoadMoreListener

    override fun getViewModel(): Class<GifListViewModel> = GifListViewModel::class.java

    override fun onViewBindingReady(savedInstanceState: Bundle?) {
        val gridLayoutManager = GridLayoutManager(context, getColumnCount())
        val gifListAdapter = GifListAdapter(GlideGifLoader())

        loadMoreListener = RecyclerViewLoadMoreListener(gridLayoutManager) {
            viewModel.loadGifs()
        }

        viewBinding.recyclerViewGif.run {
            layoutManager = gridLayoutManager
            adapter = gifListAdapter
        }

        viewBinding.recyclerViewGif.addOnScrollListener(loadMoreListener)

        viewModel.gifList.observe(this,
            Observer {
                gifListAdapter.updateList(it!!)
            })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.loadGifs()
    }

    private fun getColumnCount(): Int {
        return resources.getInteger(R.integer.trend_list_column_count)
    }


    override fun onDestroyView() {
        super.onDestroyView()

        viewBinding.recyclerViewGif.removeOnScrollListener(loadMoreListener)
    }
}