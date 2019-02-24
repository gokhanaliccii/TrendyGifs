package com.gokhanaliccii.trendygifs.data.model.response

import com.google.gson.annotations.SerializedName

/**
 * Created by gokhan.alici on 24.02.2019
 */
data class ImageType(
    @SerializedName("preview_gif")
    val previewGif: Image? = null,
    @SerializedName("fixed_width_still")
    val fixedWidthGif: Image? = null
)