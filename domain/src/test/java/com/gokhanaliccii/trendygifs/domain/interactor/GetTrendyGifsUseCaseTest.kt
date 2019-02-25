package com.gokhanaliccii.trendygifs.domain.interactor

import com.gokhanaliccii.trendygifs.data.GifRepository
import com.gokhanaliccii.trendygifs.data.model.response.Gif
import com.gokhanaliccii.trendygifs.data.model.response.ImageType
import com.gokhanaliccii.trendygifs.domain.mapper.GifEntityMapper
import com.gokhanaliccii.trendygifs.domain.model.GifUIItem
import com.gokhanaliccii.trendygifs.domain.util.RxImmediateSchedulerRule
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.reactivex.Single
import io.reactivex.observers.DisposableSingleObserver
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * Created by gokhan.alici on 25.02.2019
 */
class GetTrendyGifsUseCaseTest {

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @MockK(relaxed = true)
    lateinit var mockGifRepository: GifRepository

    lateinit var getTrendyGifsUse: GetTrendyGifsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        getTrendyGifsUse = GetTrendyGifsUseCase(mockGifRepository, GifEntityMapper())
    }

    @Test
    fun shouldMapIncomingGifsCorrectly() {
        val expectedId = "1"
        val countDownLatch = CountDownLatch(1)
        val mockImageType = mockk<ImageType>(relaxed = true)

        val gif = Gif(expectedId, mockImageType)

        every {
            mockGifRepository.getTrendyGifs(any())
        } returns Single.just(listOf(gif))

        getTrendyGifsUse.execute(1, object : DisposableSingleObserver<List<GifUIItem>>() {
            override fun onSuccess(gifList: List<GifUIItem>) {
                assert(gifList.size == 1)
                assert(gifList[0].id == expectedId)
                countDownLatch.countDown()
            }

            override fun onError(e: Throwable) {
                Assert.fail()
            }
        })

        countDownLatch.await(1, TimeUnit.SECONDS)
    }

    @Test
    fun shouldNotifyErrorCorrectly() {
        val countDownLatch = CountDownLatch(1)
        val mockThrowable = mockk<Throwable>(relaxed = true)

        every {
            mockGifRepository.getTrendyGifs(any())
        } returns Single.error(mockThrowable)

        getTrendyGifsUse.execute(1, object : DisposableSingleObserver<List<GifUIItem>>() {
            override fun onSuccess(gifList: List<GifUIItem>) {
                Assert.fail()
            }

            override fun onError(e: Throwable) {
                countDownLatch.countDown()
            }
        })

        countDownLatch.await(1, TimeUnit.SECONDS)
    }
}