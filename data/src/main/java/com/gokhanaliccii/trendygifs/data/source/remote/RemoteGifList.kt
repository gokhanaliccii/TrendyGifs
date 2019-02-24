package com.gokhanaliccii.trendygifs.data.source.remote

import com.gokhanaliccii.trendygifs.data.api.GiphyApi
import com.gokhanaliccii.trendygifs.data.model.request.GetTrendyGifRequest
import com.gokhanaliccii.trendygifs.data.model.response.Gif
import com.gokhanaliccii.trendygifs.data.model.response.GifResponse
import com.gokhanaliccii.trendygifs.data.source.TrendyGifSource
import com.gokhanaliccii.trendygifs.data.util.toQueryMap
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

/**
 * Created by gokhan.alici on 24.02.2019
 */
class RemoteGifList(private val gifApi: GiphyApi) : TrendyGifSource {

    override fun getTrendyGifs(request: GetTrendyGifRequest): Single<List<Gif>> {
        return gifApi.getTrendyGifs(request.toQueryMap())
            .subscribeOn(Schedulers.io())
            .flatMap(gifResponseToGifListMapper())
    }

    private fun gifResponseToGifListMapper(): (GifResponse) -> Single<List<Gif>> =
        { it ->
            when {
                it.data != null -> Single.just(it.data)
                else -> Single.just(emptyList())
            }
        }
}