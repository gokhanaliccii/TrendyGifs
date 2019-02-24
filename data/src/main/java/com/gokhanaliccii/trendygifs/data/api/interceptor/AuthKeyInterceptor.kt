package com.gokhanaliccii.trendygifs.data.api.interceptor

import com.gokhanaliccii.trendygifs.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by gokhan.alici on 24.02.2019
 */

class AuthKeyInterceptor : Interceptor {

    private companion object {
        const val HEADER_API_KEY = "api_key"
    }
        
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader(HEADER_API_KEY, BuildConfig.GIPHY_API_KEY)
            .build()

        return chain.proceed(newRequest)
    }
}
