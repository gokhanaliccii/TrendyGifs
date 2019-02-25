package com.gokhanaliccii.trendygifs.ui.list.adapter

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.gokhanaliccii.trendygifs.R
import com.gokhanaliccii.trendygifs.domain.model.GifUIItem
import com.gokhanaliccii.trendygifs.ui.list.adapter.GifListAdapter.GifViewHolder
import com.gokhanaliccii.trendygifs.util.gifloader.GifLoader
import com.gokhanaliccii.trendygifs.util.recyclerview.ItemClickListener

/**
 * Created by gokhan.alici on 24.02.2019
 */
class GifListAdapter(
    private val gifLoader: GifLoader,
    private val itemClickListener: ItemClickListener<GifUIItem>
) : Adapter<GifViewHolder>() {

    private var gifItems: MutableList<GifUIItem> = mutableListOf()

    fun updateList(items: List<GifUIItem>) {
        gifItems.clear()
        gifItems.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(vg: ViewGroup, viewType: Int): GifViewHolder {
        val inflater = LayoutInflater.from(vg.context)
        val gifListItem = inflater.inflate(R.layout.list_item_gif, vg, false) as ImageView

        return GifViewHolder(gifListItem)
    }

    override fun getItemCount(): Int = gifItems.size

    override fun onBindViewHolder(viewHolder: GifViewHolder, index: Int) {
        gifLoader.loadGif(viewHolder.imageView, gifItems[index].previewGifUrl)

        viewHolder.imageView.setOnClickListener {
            itemClickListener.onItemClick(gifItems[index], it)
        }
    }

    override fun onViewRecycled(holder: GifViewHolder) {
        super.onViewRecycled(holder)
        gifLoader.cancelGifLoading(holder.imageView, null)
    }

    class GifViewHolder(val imageView: ImageView) : RecyclerView.ViewHolder(imageView)
}