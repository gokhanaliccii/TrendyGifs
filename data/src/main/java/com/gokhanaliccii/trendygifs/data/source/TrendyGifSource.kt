package com.gokhanaliccii.trendygifs.data.source

import com.gokhanaliccii.trendygifs.data.model.request.GetTrendyGifRequest
import com.gokhanaliccii.trendygifs.data.model.response.Gif
import io.reactivex.Single

/**
 * Created by gokhan.alici on 24.02.2019
 */
interface TrendyGifSource {

    fun getTrendyGifs(request: GetTrendyGifRequest): Single<List<Gif>>
}