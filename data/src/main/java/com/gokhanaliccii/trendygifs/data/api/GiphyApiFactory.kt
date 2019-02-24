package com.gokhanaliccii.trendygifs.data.api

import com.gokhanaliccii.trendygifs.data.BuildConfig
import com.gokhanaliccii.trendygifs.data.api.interceptor.AuthKeyInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by gokhan.alici on 24.02.2019
 */
internal object GiphyApiFactory {

    private val retrofit by lazy {
        ApiFacade().createRetrofit()
    }

    fun createGiphyApi() = retrofit.create(GiphyApi::class.java)

    private class ApiFacade {
        private companion object {
            const val CONNECTION_TIMEOUT = 3L
            const val READ_TIMEOUT = 3L
        }

        fun createRetrofit(): Retrofit {
            buildHttpClient()

            return Retrofit.Builder()
                .baseUrl(BuildConfig.GIPHY_API_URL)
                .client(buildHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }

        private fun buildHttpClient(): OkHttpClient {
            val httpClientBuilder = OkHttpClient.Builder()
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(AuthKeyInterceptor())

            if (BuildConfig.DEBUG) {
                httpClientBuilder.addInterceptor(
                    HttpLoggingInterceptor().setLevel(
                        HttpLoggingInterceptor.Level.BODY
                    )
                )
            }

            return httpClientBuilder.build()
        }
    }
}