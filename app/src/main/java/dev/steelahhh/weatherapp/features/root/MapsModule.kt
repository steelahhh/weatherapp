package dev.steelahhh.weatherapp.features.root

import android.content.Context
import android.location.Geocoder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

/*
 * Author: steelahhh
 * 15/8/20
 */

@InstallIn(ActivityComponent::class)
@Module
object MapsModule {
    @Provides
    fun provideGeocoder(@ActivityContext context: Context): Geocoder = Geocoder(context)
}
