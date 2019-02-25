package com.gokhanaliccii.trendygifs.ui.list

import android.arch.lifecycle.MutableLiveData
import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import com.gokhanaliccii.trendygifs.TestActivity

import com.gokhanaliccii.trendygifs.domain.model.GifUIItem
import com.gokhanaliccii.trendygifs.util.dummyViewModelProvider
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch

/**
 * Created by gokhan.alici on 25.02.2019
 */
class GifListFragmentTest {

    private companion object {
        const val GIFS_LOADING_TEXT = "Gifs are loading"
    }

    @Rule
    @JvmField
    val mActivityRule = object : ActivityTestRule<TestActivity>(TestActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return Intent(InstrumentationRegistry.getTargetContext(), TestActivity::class.java)
        }
    }

    @MockK(relaxed = true)
    lateinit var mockGifListViewModel: GifListViewModel

    private val gifList: MutableLiveData<List<GifUIItem>> = MutableLiveData()
    private val moreGifsLoading = MutableLiveData<Boolean>()
    private val contentGifsLoading = MutableLiveData<Boolean>()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        val viewModelFactory = dummyViewModelProvider(mockGifListViewModel)
        val gifListFragment = GifListFragment()
        gifListFragment.injectViewModelFactory(viewModelFactory)

        every {
            mockGifListViewModel.gifList
        } returns (gifList)

        every {
            mockGifListViewModel.contentGifsLoadingObserver()
        } returns (contentGifsLoading)

        every {
            mockGifListViewModel.moreGifsLoadingObserver()
        } returns (moreGifsLoading)

        mActivityRule.activity.addFragment(gifListFragment)
    }

    @Test
    @Throws(InterruptedException::class)
    fun shouldLoadingTextVisibleBeforeAppDataAvailable() {
        contentGifsLoading.postValue(true)

        onView(withText(GIFS_LOADING_TEXT)).check(matches(isDisplayed()))
    }
}