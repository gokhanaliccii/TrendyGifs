package com.gokhanaliccii.trendygifs.data.util

import com.gokhanaliccii.trendygifs.data.model.request.GetTrendyGifRequest

/**
 * Created by gokhan.alici on 24.02.2019
 */

fun GetTrendyGifRequest.toQueryMap() =
    com.gokhanaliccii.trendygifs.data.util.toQueryMap(this)