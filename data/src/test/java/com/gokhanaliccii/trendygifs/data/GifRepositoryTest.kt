package com.gokhanaliccii.trendygifs.data

import com.gokhanaliccii.trendygifs.data.model.request.GetTrendyGifRequest
import com.gokhanaliccii.trendygifs.data.model.response.Gif
import com.gokhanaliccii.trendygifs.data.source.cache.SharedPrefCacheSource
import com.gokhanaliccii.trendygifs.data.source.remote.RemoteGifList
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

/**
 * Created by gokhan.alici on 25.02.2019
 */
class GifRepositoryTest {

    @MockK(relaxed = true)
    lateinit var mockCacheSource: SharedPrefCacheSource

    @MockK(relaxed = true)
    lateinit var mockRemoteSource :RemoteGifList

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
    }

    @Test
    fun shouldSaveApiResponseToCacheWhenServiceSuccessfullyReturn() {
        val mockGifList = mockk<List<Gif>>()

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

        every {
            mockRemoteSource.getTrendyGifs(any())
        }.returns(Single.error(mockThrowable))

        val gifRepository = GifRepository(mockRemoteSource, mockCacheSource)

        gifRepository.getTrendyGifs(GetTrendyGifRequest(0)).test()

        verify { mockCacheSource.getTrendyGifs(any()) }
    }
}