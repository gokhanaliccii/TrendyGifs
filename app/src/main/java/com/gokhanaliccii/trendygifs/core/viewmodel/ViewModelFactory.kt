package com.gokhanaliccii.trendygifs.core.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.gokhanaliccii.trendygifs.domain.di.InteractorFactory
import com.gokhanaliccii.trendygifs.ui.list.GifListViewModel

/**
 * Created by gokhan.alici on 24.02.2019
 */
class ViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GifListViewModel::class.java)) {
            return GifListViewModel(InteractorFactory.getTrendyGifsUseCase()) as T
        }

        throw IllegalArgumentException("${modelClass.name} is unknown for this factory")
    }
}