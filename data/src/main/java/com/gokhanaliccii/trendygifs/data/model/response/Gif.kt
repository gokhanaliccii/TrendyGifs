package com.gokhanaliccii.trendygifs.data.model.response

import com.google.gson.annotations.SerializedName

/**
 * Created by gokhan.alici on 24.02.2019
 */
data class Gif(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("images")
    private val imageTypes: ImageType? = null
)