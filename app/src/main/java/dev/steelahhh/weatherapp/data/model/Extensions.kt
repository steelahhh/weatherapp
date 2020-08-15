package dev.steelahhh.weatherapp.data.model

import android.location.Address
import android.location.Geocoder
import java.text.NumberFormat

/*
 * Author: steelahhh
 * 15/8/20
 */

private const val UNKNOWN_LOCATION = "UNKNOWN_LOCATION"
private const val BASE_URL = "https://openweathermap.org/img/wn/"
private const val EXTENSION = ".png"

private fun WeatherDetailResponse.parseIcon(scale: String = "2x"): String {
    return "$BASE_URL$icon@$scale$EXTENSION"
}

/**
 * Map API response to UI model
 *
 * Latitude and longitude are required, since the API response returns rounded values.
 *
 * @param geocoder - for finding the address of current location
 * @param lat - latitude of the specified location
 * @param lon - longitude of the specified location
 */
fun WeatherOneCallResponse.toUi(
    geocoder: Geocoder,
    lat: Double,
    lon: Double
): Weather? = current?.let {
    val numberFormatter = NumberFormat.getNumberInstance()
    val detail = it.weather.firstOrNull() ?: return@let null

    Weather(
        location = getLocation(geocoder, lat, lon),
        temperature = it.temp.toString(),
        temperatureFeelsLike = it.feelsLike.toString(),
        humidity = numberFormatter.format(it.humidity),
        icon = detail.parseIcon(),
        description = listOf(detail.main, detail.description).joinToString(" – ")
    )
}

/**
 * Get address line of the current location using android.location API
 */
fun getLocation(
    geocoder: Geocoder,
    lat: Double,
    lon: Double
): String {
    return try {
        val addresses: List<Address> = geocoder.getFromLocation(lat, lon, 1)
        when {
            addresses.isNotEmpty() -> addresses.first().getAddressLine(0)
            else -> UNKNOWN_LOCATION
        }
    } catch (e: Exception) {
        UNKNOWN_LOCATION
    }
}
