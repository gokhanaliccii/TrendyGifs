package com.gokhanaliccii.trendygifs.core.router

import android.support.v4.app.FragmentManager
import com.gokhanaliccii.trendygifs.R
import com.gokhanaliccii.trendygifs.domain.model.GifUIItem
import com.gokhanaliccii.trendygifs.ui.detail.GifDetailFragment

/**
 * Created by gokhan.alici on 25.02.2019
 */
class GifDetailRouter(private val fragmentManager: FragmentManager) {

    fun routeToDetail(gifUIItem: GifUIItem) {
        fragmentManager.beginTransaction()
            .replace(R.id.content_page, GifDetailFragment.newInstance(gifUIItem.previewGifUrl))
            .addToBackStack(null)
            .commit()
    }
}