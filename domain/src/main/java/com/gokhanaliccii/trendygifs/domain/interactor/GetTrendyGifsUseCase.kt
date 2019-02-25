package com.gokhanaliccii.trendygifs.domain.interactor

import com.gokhanaliccii.trendygifs.data.GifRepository
import com.gokhanaliccii.trendygifs.data.model.request.GetTrendyGifRequest
import com.gokhanaliccii.trendygifs.domain.mapper.GifEntityMapper
import com.gokhanaliccii.trendygifs.domain.model.GifUIItem
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Created by gokhan.alici on 24.02.2019
 */
class GetTrendyGifsUseCase(
    private val repository: GifRepository,
    private val entityMapper: GifEntityMapper
) : UseCase.Single<Int, List<GifUIItem>>() {

    override fun build(input: Int?): io.reactivex.Single<List<GifUIItem>> {
        return repository.getTrendyGifs(GetTrendyGifRequest(input!!))
            .flatMap {
                Observable.fromIterable(it)
                    .map(entityMapper::mapTo)
                    .toList()
            }.observeOn(AndroidSchedulers.mainThread())
    }
}