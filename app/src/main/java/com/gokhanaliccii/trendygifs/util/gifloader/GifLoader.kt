package com.gokhanaliccii.trendygifs.util.gifloader

import android.widget.ImageView

/**
 * Created by gokhan.alici on 24.02.2019
 */
interface GifLoader {

    fun loadGif(view: ImageView, url: String)

    fun cancelGifLoading(view: ImageView, url: String)
}