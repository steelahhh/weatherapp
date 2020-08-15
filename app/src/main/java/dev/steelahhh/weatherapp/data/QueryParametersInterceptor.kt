package dev.steelahhh.weatherapp.data

import javax.inject.Inject
import javax.inject.Singleton
import okhttp3.Interceptor
import okhttp3.Response

/*
 * Author: steelahhh
 * 15/8/20
 */

@Singleton
class QueryParametersInterceptor @Inject constructor() : Interceptor {
    companion object {
        private const val EXCLUDE_KEY = "exclude"
        private const val UNITS_KEY = "units"

        /**
         * This should be controlled by [KeyValueStorage], but for the sake of simplicity,
         *  these values are hardcoded
         */
        private const val EXCLUDED_WEATHER = "daily,hourly,minutely"
        private const val METRIC = "metric"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val newUrl = original.url.newBuilder()
            .addQueryParameter(EXCLUDE_KEY, EXCLUDED_WEATHER)
            .addQueryParameter(UNITS_KEY, METRIC)
            .build()

        val newRequest = original.newBuilder().url(newUrl).build()

        return chain.proceed(newRequest)
    }
}
