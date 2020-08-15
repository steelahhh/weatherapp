package dev.steelahhh.weatherapp.data

import android.content.Context
import android.location.Geocoder
import dagger.hilt.android.qualifiers.ActivityContext
import dev.steelahhh.weatherapp.core.Either
import dev.steelahhh.weatherapp.data.model.Weather
import dev.steelahhh.weatherapp.data.model.toUi
import javax.inject.Inject

/*
 * Author: steelahhh
 * 15/8/20
 */

const val LATITUDE_KEY = "key:latitude"
const val LONGITUDE_KEY = "key:longitude"

/**
 * Gets weather for location specified by [LATITUDE_KEY] and [LONGITUDE_KEY],
 *  that can be changed in AddressPickerFragment
 */
class GetWeatherUseCase @Inject constructor(
    private val weatherService: WeatherService,
    private val geocoder: Geocoder,
    @ActivityContext private val context: Context,
    private val locationRepository: LocationRepository
) {

    suspend fun invoke(): Either<Throwable, Weather> {
        val (latitude, longitude) = locationRepository.getLatLng()

        return try {
            val weather = weatherService.getWeather(
                latitude = latitude,
                longitude = longitude
            ).toUi(context, geocoder, latitude, longitude)

            if (weather != null) {
                Either.Right(weather)
            } else {
                Either.Left(WeatherNotFoundException(latitude, longitude))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Either.Left(e)
        }
    }
}
