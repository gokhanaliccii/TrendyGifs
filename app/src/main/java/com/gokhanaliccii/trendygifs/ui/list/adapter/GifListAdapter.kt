package com.gokhanaliccii.trendygifs.ui.list.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.gokhanaliccii.trendygifs.R
import com.gokhanaliccii.trendygifs.domain.model.GifUIItem

/**
 * Created by gokhan.alici on 24.02.2019
 */
class GifListAdapter : RecyclerView.Adapter<GifListAdapter.GifViewHolder>() {

    private var gifItems: MutableList<GifUIItem> = mutableListOf()

    override fun onCreateViewHolder(vg: ViewGroup, viewType: Int): GifViewHolder {
        val inflater = LayoutInflater.from(vg.context)
        val gifListItem = inflater.inflate(R.layout.list_item_gif, vg, false) as ImageView

        return GifViewHolder(gifListItem)
    }

    override fun getItemCount(): Int = gifItems.size

    override fun onBindViewHolder(viewHolder: GifViewHolder, index: Int) {
    }

    class GifViewHolder(val imageView: ImageView) : RecyclerView.ViewHolder(imageView)
}