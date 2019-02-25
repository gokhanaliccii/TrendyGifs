package com.gokhanaliccii.trendygifs.data

import com.gokhanaliccii.trendygifs.data.model.request.GetTrendyGifRequest
import com.gokhanaliccii.trendygifs.data.model.response.Gif
import com.gokhanaliccii.trendygifs.data.source.cache.SharedPrefCacheSource
import com.gokhanaliccii.trendygifs.data.source.remote.RemoteGifList
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Test

/**
 * Created by gokhan.alici on 25.02.2019
 */
class GifRepositoryTest {

    @Test
    fun shouldSaveApiResponseToCacheWhenServiceSuccessfullyReturn() {
        val mockGifList = mockk<List<Gif>>()
        val mockCacheSource = mockk<SharedPrefCacheSource>(relaxed = true)
        val mockRemoteSource = mockk<RemoteGifList>(relaxed = true)

        every {
            mockRemoteSource.getTrendyGifs(any())
        }.returns(Single.just(mockGifList))

        val gifRepository = GifRepository(mockRemoteSource, mockCacheSource)

        gifRepository.getTrendyGifs(GetTrendyGifRequest(0)).test()

        verify { mockCacheSource.saveTrendyGifs(any(), any()) }
    }

  @Test
    fun shouldProvideGifsFromCacheWhenServiceReturnError() {
        val mockThrowable = mockk<Throwable>()
        val mockCacheSource = mockk<SharedPrefCacheSource>(relaxed = true)
        val mockRemoteSource = mockk<RemoteGifList>(relaxed = true)

        every {
            mockRemoteSource.getTrendyGifs(any())
        }.returns(Single.error(mockThrowable))

        val gifRepository = GifRepository(mockRemoteSource, mockCacheSource)

        gifRepository.getTrendyGifs(GetTrendyGifRequest(0)).test()

        verify { mockCacheSource.getTrendyGifs(any()) }
    }
}