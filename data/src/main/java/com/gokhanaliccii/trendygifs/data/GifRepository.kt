package com.gokhanaliccii.trendygifs.data

import com.gokhanaliccii.trendygifs.data.model.request.GetTrendyGifRequest
import com.gokhanaliccii.trendygifs.data.model.response.Gif
import com.gokhanaliccii.trendygifs.data.source.TrendyGifSource
import io.reactivex.Single

/**
 * Created by gokhan.alici on 24.02.2019
 */
class GifRepository(
    private val remoteSource: TrendyGifSource
) : TrendyGifSource {
    
    override fun getTrendyGifs(request: GetTrendyGifRequest): Single<List<Gif>> {
        return remoteSource.getTrendyGifs(request)
    }
}