package com.gokhanaliccii.trendygifs.util.recyclerview

import android.view.View

/**
 * Created by gokhan.alici on 24.02.2019
 */
interface ItemClickListener<T> {
    fun onItemClick(data: T, view: View? = null)
}