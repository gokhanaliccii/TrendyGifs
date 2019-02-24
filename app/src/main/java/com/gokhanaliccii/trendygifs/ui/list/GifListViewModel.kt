package com.gokhanaliccii.trendygifs.ui.list

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.gokhanaliccii.trendygifs.domain.di.InteractorFactory
import com.gokhanaliccii.trendygifs.domain.model.GifUIItem
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver

/**
 * Created by gokhan.alici on 24.02.2019
 */
class GifListViewModel : ViewModel() {

    val gifList = MutableLiveData<List<GifUIItem>>()

    private var currentIndex = 0
    private val currentGifList = mutableListOf<GifUIItem>()
    private val getTrendyGifsUseCase = InteractorFactory.getTrendyGifsUseCase()
    private val compositeDisposable = CompositeDisposable()

    fun loadGifs() {
        getTrendyGifsUseCase.execute(currentIndex,
            object : DisposableSingleObserver<List<GifUIItem>>() {
                override fun onSuccess(gifList: List<GifUIItem>) {
                    onNewGifsLoaded(gifList)
                }

                override fun onError(e: Throwable) {

                }
            })
    }

    private fun onNewGifsLoaded(gifList: List<GifUIItem>) {
        currentIndex++

        currentGifList.addAll(gifList)
        this.gifList.value = currentGifList
    }

    override fun onCleared() {
        super.onCleared()

        compositeDisposable.clear()
    }
}