package com.gokhanaliccii.trendygifs.data.source.cache

import android.content.SharedPreferences
import com.gokhanaliccii.trendygifs.data.model.request.GetTrendyGifRequest
import com.gokhanaliccii.trendygifs.data.model.response.Gif
import com.gokhanaliccii.trendygifs.data.source.TrendyGifSource
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Single

/**
 * Created by gokhan.alici on 25.02.2019
 */
class SharedPrefCacheSource(
    private val sharedPreference: SharedPreferences
) : TrendyGifSource {

    override fun getTrendyGifs(request: GetTrendyGifRequest): Single<List<Gif>> {
        val trendyGifListJson = sharedPreference.getString(pageKey(request), "")

        if (trendyGifListJson.isNullOrEmpty()) {
            return Single.just(emptyList())
        }

        return Single.fromCallable {
            deserializeGifList(trendyGifListJson)
        }
    }

    private fun deserializeGifList(trendyGifListJson: String?): List<Gif> {
        val type = object : TypeToken<List<Gif>>() {
        }.type

        return Gson().fromJson(trendyGifListJson, type)
    }

    fun saveTrendyGifs(request: GetTrendyGifRequest, trendyGifs: List<Gif>) {
        val trendyGifsJson = Gson().toJson(trendyGifs)
        sharedPreference.edit().putString(pageKey(request), trendyGifsJson).apply()
    }

    private fun pageKey(request: GetTrendyGifRequest) = "page_${request.offset}"
}