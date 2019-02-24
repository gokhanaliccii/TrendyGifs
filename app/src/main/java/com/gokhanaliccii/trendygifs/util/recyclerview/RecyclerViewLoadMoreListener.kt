package com.gokhanaliccii.trendygifs.util.recyclerview

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager

/**
 * Created by gokhan.alici on 24.02.2019
 */
class RecyclerViewLoadMoreListener(
    private val layoutManager: RecyclerView.LayoutManager,
    private var visibleThreshold: Int = 8,
    private val onDataLoadable: (Int) -> Unit
) : RecyclerView.OnScrollListener() {

    private var loading = true
    private var currentPage = 0
    private var knownItemCount = 0

    init {
        if (layoutManager is GridLayoutManager) {
            visibleThreshold *= layoutManager.spanCount
        } else if (layoutManager is StaggeredGridLayoutManager) {
            visibleThreshold *= layoutManager.spanCount
        }
    }

    override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {
        val currentItemCount = layoutManager.itemCount
        var lastVisibleItemPosition: Int = when (layoutManager) {
            is GridLayoutManager -> layoutManager.findLastVisibleItemPosition()
            is LinearLayoutManager -> layoutManager.findLastVisibleItemPosition()
            is StaggeredGridLayoutManager -> layoutManager.findLastVisibleItemPositions(null).first()
            else -> 0
        }

        if (isLoadedDataInvalidated(currentItemCount)) {
            currentPage = 0
            knownItemCount = currentItemCount
            if (currentItemCount == 0) {
                this.loading = true
            }
        }

        if (isDataLoaded(currentItemCount)) {
            loading = false
            knownItemCount = currentItemCount
        }

        if (shouldLoadMoreItem(lastVisibleItemPosition, currentItemCount)) {
            loading = true
            onDataLoadable(++currentPage)
        }
    }

    private fun isLoadedDataInvalidated(currentItemCount: Int) = currentItemCount < knownItemCount

    private fun isDataLoaded(currentItemCount: Int) = loading && currentItemCount > knownItemCount

    private fun shouldLoadMoreItem(lastVisibleItemPosition: Int, currentItemCount: Int) =
        !loading && lastVisibleItemPosition + visibleThreshold > currentItemCount

    fun newDataLoaded() {
        this.loading = true
        this.currentPage = 0
        this.knownItemCount = 0
    }
}