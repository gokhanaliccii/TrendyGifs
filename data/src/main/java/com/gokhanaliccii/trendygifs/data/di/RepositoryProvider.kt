package com.gokhanaliccii.trendygifs.data.di

import com.gokhanaliccii.trendygifs.data.GifRepository
import com.gokhanaliccii.trendygifs.data.api.GiphyApi
import com.gokhanaliccii.trendygifs.data.api.GiphyApiFactory
import com.gokhanaliccii.trendygifs.data.source.remote.RemoteGifList

/**
 * Created by gokhan.alici on 24.02.2019
 */

//todo find better name
object RepositoryProvider {

    private val giphyApi: GiphyApi by lazy { GiphyApiFactory.createGiphyApi() }

    fun getGifRepository(): GifRepository {
        val remoteSource = RemoteGifList(giphyApi)
        return GifRepository(remoteSource)
    }
}