package dev.steelahhh.weatherapp.data

import android.location.Geocoder
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
    private val keyValueStorage: KeyValueStorage,
    private val geocoder: Geocoder
) {
    companion object {
        const val DEFAULT_LAT = 52.364138
        const val DEFAULT_LNG = 4.891697
    }

    suspend fun invoke(): Either<Throwable, Weather> {
        val latitude = keyValueStorage.getString(
            key = LATITUDE_KEY,
            default = DEFAULT_LAT.toString()
        ).toDouble()

        val longitude = keyValueStorage.getString(
            key = LONGITUDE_KEY,
            default = DEFAULT_LNG.toString()
        ).toDouble()

        return try {
            val weather = weatherService.getWeather(
                latitude = latitude,
                longitude = longitude
            ).toUi(geocoder, latitude, longitude)

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
