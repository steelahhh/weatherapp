package dev.steelahhh.weatherapp.data

import dev.steelahhh.weatherapp.BuildConfig
import javax.inject.Inject
import javax.inject.Singleton
import okhttp3.Interceptor
import okhttp3.Response

/*
 * Author: steelahhh
 * 15/8/20
 */

@Singleton
class ApiKeyInterceptor @Inject constructor() : Interceptor {
    companion object {
        private const val API_KEY = "appid"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val newUrl = original.url.newBuilder()
            .addQueryParameter(API_KEY, BuildConfig.API_KEY)
            .build()

        val newRequest = original.newBuilder().url(newUrl).build()

        return chain.proceed(newRequest)
    }
}
