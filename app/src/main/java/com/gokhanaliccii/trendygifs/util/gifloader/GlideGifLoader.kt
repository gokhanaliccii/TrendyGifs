package com.gokhanaliccii.trendygifs.util.gifloader

import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gokhanaliccii.trendygifs.util.getRandomColor

/**
 * Created by gokhan.alici on 24.02.2019
 */
class GlideGifLoader : GifLoader {

    override fun loadGif(view: ImageView, url: String) {
        val randomColor = ColorDrawable(getRandomColor())
        val randomColoredPlaceHolder = RequestOptions().placeholder(randomColor).centerCrop()

        Glide.with(view.context)
            .asGif()
            .load(url)
            .apply(randomColoredPlaceHolder)
            .into(view)
    }

    override fun cancelGifLoading(view: ImageView, url: String?) {
        Glide.with(view).clear(view)
    }
}