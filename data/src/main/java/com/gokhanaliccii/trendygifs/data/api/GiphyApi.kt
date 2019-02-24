package com.gokhanaliccii.trendygifs.data.api

import com.gokhanaliccii.trendygifs.data.model.response.GifResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Created by gokhan.alici on 24.02.2019
 */
interface GiphyApi {

    @GET("/v1/gifs/trending")
    fun getTrendyGifs(@QueryMap queryParams: Map<String, String>): Single<GifResponse>
}