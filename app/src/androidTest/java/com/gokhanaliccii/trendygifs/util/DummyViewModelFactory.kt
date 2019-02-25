package com.gokhanaliccii.trendygifs.util

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.gokhanaliccii.trendygifs.ui.list.GifListViewModel

/**
 * Created by gokhan.alici on 25.02.2019
 */
fun <T : ViewModel> dummyViewModelProvider(target: T): ViewModelProvider.Factory {
    return object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(GifListViewModel::class.java)) {
                return target as T
            }

            throw IllegalArgumentException("${modelClass.name} is unknown for this factory")
        }
    }
}