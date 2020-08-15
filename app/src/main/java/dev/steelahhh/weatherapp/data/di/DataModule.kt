package dev.steelahhh.weatherapp.data.di

import android.content.Context
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.steelahhh.weatherapp.BuildConfig
import dev.steelahhh.weatherapp.data.ApiKeyInterceptor
import dev.steelahhh.weatherapp.data.KeyValueStorage
import dev.steelahhh.weatherapp.data.WeatherService
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/*
 * Author: steelahhh
 * 15/8/20
 */

@InstallIn(ApplicationComponent::class)
@Module
object DataModule {
    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    private const val PREFS_NAME = "weatherapp:prefs"

    @Provides
    fun keyValueStorage(@ApplicationContext context: Context): KeyValueStorage = KeyValueStorage(
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    )

    @Provides
    @Singleton
    fun provideMoshi() = Moshi.Builder().build()

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor? {
        if (BuildConfig.DEBUG) return null

        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        apiKeyInterceptor: ApiKeyInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor?
    ): OkHttpClient = OkHttpClient.Builder()
        .apply {
            if (httpLoggingInterceptor != null)
                addNetworkInterceptor(httpLoggingInterceptor)
        }
        .addInterceptor(apiKeyInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): WeatherService = retrofit.create(WeatherService::class.java)
}
