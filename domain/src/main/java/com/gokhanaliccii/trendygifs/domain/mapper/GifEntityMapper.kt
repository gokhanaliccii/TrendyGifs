package com.gokhanaliccii.trendygifs.domain.mapper

import com.gokhanaliccii.trendygifs.data.model.response.Gif
import com.gokhanaliccii.trendygifs.domain.model.GifUIItem

/**
 * Created by gokhan.alici on 24.02.2019
 */
class GifEntityMapper : EntityMapper<Gif, GifUIItem> {

    override fun mapTo(gif: Gif): GifUIItem {
        val id = gif.id ?: ""
        val previewGifUrl = gif.imageTypes?.previewGif?.url ?: ""
        val bigGifUrl = gif.imageTypes?.fixedWidthGif?.url ?: ""

        return GifUIItem(id, previewGifUrl, bigGifUrl)
    }
}