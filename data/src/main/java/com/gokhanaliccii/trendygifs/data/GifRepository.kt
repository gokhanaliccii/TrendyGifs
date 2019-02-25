package com.gokhanaliccii.trendygifs.data

import com.gokhanaliccii.trendygifs.data.model.request.GetTrendyGifRequest
import com.gokhanaliccii.trendygifs.data.model.response.Gif
import com.gokhanaliccii.trendygifs.data.source.TrendyGifSource
import com.gokhanaliccii.trendygifs.data.source.cache.SharedPrefCacheSource
import io.reactivex.Single

/**
 * Created by gokhan.alici on 24.02.2019
 */
class GifRepository(
    private val remoteSource: TrendyGifSource,
    private val cacheSource: TrendyGifSource
) : TrendyGifSource {

    override fun getTrendyGifs(request: GetTrendyGifRequest): Single<List<Gif>> {
        return remoteSource.getTrendyGifs(request)
            .doOnSuccess {
                if (cacheSource is SharedPrefCacheSource) {
                    cacheSource.saveTrendyGifs(request, it)
                }
            }.onErrorResumeNext {
                cacheSource.getTrendyGifs(request)
            }
    }
}