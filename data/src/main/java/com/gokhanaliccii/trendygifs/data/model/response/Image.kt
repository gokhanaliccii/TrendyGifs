package com.gokhanaliccii.trendygifs.data.model.response

import com.google.gson.annotations.SerializedName

/**
 * Created by gokhan.alici on 24.02.2019
 */
data class Image(
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("height")
    val height: Int? = 0,
    @SerializedName("width")
    val width: Int? = 0
)