package com.gokhanaliccii.trendygifs.util

import android.graphics.Color
import java.util.*

/**
 * Created by gokhan.alici on 24.02.2019
 */

private const val MAX_COLOR_CODE = 255

fun getRandomColor(): Int {
    val rnd = Random()
    return Color.argb(
        MAX_COLOR_CODE,
        rnd.nextInt(MAX_COLOR_CODE),
        rnd.nextInt(MAX_COLOR_CODE),
        rnd.nextInt(MAX_COLOR_CODE)
    )
}