package com.gokhanaliccii.trendygifs.ui.list

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.gokhanaliccii.trendygifs.domain.interactor.GetTrendyGifsUseCase
import com.gokhanaliccii.trendygifs.domain.model.GifUIItem
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver

/**
 * Created by gokhan.alici on 24.02.2019
 */
class GifListViewModel(
    private val getTrendyGifsUseCase: GetTrendyGifsUseCase
) : ViewModel() {

    val gifList = MutableLiveData<List<GifUIItem>>()
    private val mMoreGifsLoading = MutableLiveData<Boolean>()
    private val mContentGifsLoading = MutableLiveData<Boolean>()

    private var currentIndex = 0
    private val currentGifList = mutableListOf<GifUIItem>()
    private val compositeDisposable = CompositeDisposable()


    fun moreGifsLoadingObserver(): LiveData<Boolean> {
        return mMoreGifsLoading
    }

    fun contentGifsLoadingObserver(): LiveData<Boolean> {
        return mContentGifsLoading
    }

    fun loadGifs() {
        if (currentIndex == 0) {
            mContentGifsLoading.value = true
        } else {
            mMoreGifsLoading.value = true;
        }

        getTrendyGifsUseCase.execute(currentIndex,
            object : DisposableSingleObserver<List<GifUIItem>>() {
                override fun onSuccess(gifList: List<GifUIItem>) {
                    onNewGifsLoaded(gifList)
                }

                override fun onError(e: Throwable) {
                    //todo handle
                }
            })
    }

    private fun onNewGifsLoaded(gifList: List<GifUIItem>) {
        mContentGifsLoading.value = false
        mMoreGifsLoading.value = false
        currentIndex++

        currentGifList.addAll(gifList)
        this.gifList.value = currentGifList
    }

    override fun onCleared() {
        super.onCleared()

        compositeDisposable.clear()
    }
}