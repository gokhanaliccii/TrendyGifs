package com.gokhanaliccii.trendygifs.domain.di

import com.gokhanaliccii.trendygifs.data.di.RepositoryProvider
import com.gokhanaliccii.trendygifs.domain.interactor.GetTrendyGifsUseCase
import com.gokhanaliccii.trendygifs.domain.mapper.GifEntityMapper

/**
 * Created by gokhan.alici on 24.02.2019
 */

object InteractorFactory {

    fun getTrendyGifsUseCase(): GetTrendyGifsUseCase {
        return GetTrendyGifsUseCase(
            RepositoryProvider.getGifRepository(),
            GifEntityMapper()
        )
    }
}