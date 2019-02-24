package com.gokhanaliccii.trendygifs.data.model.response

import com.google.gson.annotations.SerializedName

/**
 * Created by gokhan.alici on 24.02.2019
 */
class GifResponse {
    @SerializedName("data")
    val data: List<Gif>? = null
}