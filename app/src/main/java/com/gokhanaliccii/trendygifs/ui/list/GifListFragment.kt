package com.gokhanaliccii.trendygifs.ui.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gokhanaliccii.trendygifs.R
import com.gokhanaliccii.trendygifs.databinding.FragmentGifListBinding
import com.gokhanaliccii.trendygifs.ui.list.adapter.GifListAdapter
import com.gokhanaliccii.trendygifs.util.gifloader.GlideGifLoader

/**
 * Created by gokhan.alici on 24.02.2019
 */
class GifListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gif_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewBinding = FragmentGifListBinding.bind(view)
        val gifListViewModel = ViewModelProviders.of(this)[GifListViewModel::class.java]

        val gridLayoutManager = GridLayoutManager(context, getColumnCount())
        val gifListAdapter = GifListAdapter(GlideGifLoader())

        viewBinding.recyclerViewGif.run {
            layoutManager = gridLayoutManager
            adapter = gifListAdapter
        }

        gifListViewModel.gifList.observe(this,
            Observer {
                gifListAdapter.updateList(it!!)
            })

        gifListViewModel.loadGifs()
    }

    private fun getColumnCount(): Int {
        return resources.getInteger(R.integer.trend_list_column_count)
    }
}