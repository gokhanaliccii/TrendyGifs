package com.gokhanaliccii.trendygifs.data.util

import com.gokhanaliccii.trendygifs.data.model.request.GetTrendyGifRequest
import org.hamcrest.core.IsEqual.equalTo
import org.junit.Assert.assertThat
import org.junit.Test

/**
 * Created by gokhan.alici on 24.02.2019
 */
class GifToQueryMapperTest {

    @Test
    fun shouldConvertGifRequestAttributesToMapCorrectly() {
        val expectedAttributeCount = 2
        val getTrendyGifRequest = dummyRequest(1, 2)

        val mappedAttributes = toQueryMap(getTrendyGifRequest)
        assertThat(mappedAttributes.size, equalTo(expectedAttributeCount))
    }

    @Test
    fun shouldGetOffsetAttributesCorrectly() {
        val expectedOffsetValue = 1
        val expectedLimitValue = 2
        val getTrendyGifRequest = dummyRequest(expectedOffsetValue, expectedLimitValue)

        val mappedAttributes = toQueryMap(getTrendyGifRequest)
        assertThat(mappedAttributes["offset"], equalTo(expectedOffsetValue.toString()))
    }

    @Test
    fun shouldGetLimitAttributesCorrectly() {
        val expectedOffsetValue = 1
        val expectedLimitValue = 2
        val getTrendyGifRequest = dummyRequest(expectedOffsetValue, expectedLimitValue)

        val mappedAttributes = toQueryMap(getTrendyGifRequest)
        assertThat(mappedAttributes["limit"], equalTo(expectedLimitValue.toString()))
    }

    private fun dummyRequest(
        offset: Int,
        limit: Int
    ) = GetTrendyGifRequest(offset, limit)
}