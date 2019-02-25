package com.gokhanaliccii.trendygifs.data.di

import android.content.Context
import com.gokhanaliccii.trendygifs.data.GifRepository
import com.gokhanaliccii.trendygifs.data.api.GiphyApi
import com.gokhanaliccii.trendygifs.data.api.GiphyApiFactory
import com.gokhanaliccii.trendygifs.data.source.cache.SharedPrefCacheSource
import com.gokhanaliccii.trendygifs.data.source.remote.RemoteGifList

/**
 * Created by gokhan.alici on 24.02.2019
 */

// todo find better name
object RepositoryProvider {

    private const val FILE_NAME = "trendy_gif"

    private val giphyApi: GiphyApi by lazy { GiphyApiFactory.createGiphyApi() }

    fun getGifRepository(context: Context): GifRepository {
        val cacheSource = SharedPrefCacheSource(trendyGifsSharedPreference(context))
        val remoteSource = RemoteGifList(giphyApi)

        return GifRepository(remoteSource, cacheSource)
    }

    private fun trendyGifsSharedPreference(context: Context) =
        context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
}