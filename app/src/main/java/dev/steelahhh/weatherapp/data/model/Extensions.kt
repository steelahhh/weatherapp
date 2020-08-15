package dev.steelahhh.weatherapp.data.model

import android.content.Context
import android.graphics.Typeface
import android.location.Address
import android.location.Geocoder
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.StyleSpan
import dev.steelahhh.weatherapp.R
import java.text.NumberFormat

/*
 * Author: steelahhh
 * 15/8/20
 */

private const val UNKNOWN_LOCATION = "Unknown location"
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
 * @param context - used for getting strings from resources, normally would be extracted into
 *  some sort of ResourceManager
 * @param geocoder - for finding the address of current location
 * @param lat - latitude of the specified location
 * @param lon - longitude of the specified location
 */
fun WeatherOneCallResponse.toUi(
    context: Context,
    geocoder: Geocoder,
    lat: Double,
    lon: Double
): Weather? = current?.let {
    val numberFormatter = NumberFormat.getNumberInstance()
    numberFormatter.maximumFractionDigits = 1
    val detail = it.weather.firstOrNull() ?: return@let null

    Weather(
        location = getLocation(geocoder, lat, lon),
        temperature = it.buildTemperatureString(numberFormatter, context),
        humidity = context.getString(
            R.string.humidity_pattern,
            numberFormatter.format(it.humidity)
        ),
        pressure = context.getString(
            R.string.pressure_pattern,
            numberFormatter.format(it.pressure)
        ),
        icon = detail.parseIcon(),
        description = listOf(detail.main, detail.description).joinToString(" – "),
        windSpeed = context.getString(
            R.string.wind_speed_pattern,
            numberFormatter.format(it.windSpeed),
            context.getString(R.string.wind_speed_metric)
        ),
        uvIndex = context.getString(
            R.string.uv_index,
            numberFormatter.format(it.uvi)
        )
    )
}

/**
 * Get address line of the current location using android.location API
 */
fun getLocation(
    geocoder: Geocoder,
    latitude: Double,
    longitude: Double
): String {
    return try {
        val addresses: List<Address> = geocoder.getFromLocation(latitude, longitude, 1)
        when {
            addresses.isNotEmpty() -> addresses.first().getAddressLine(0)
            else -> UNKNOWN_LOCATION
        }
    } catch (e: Exception) {
        UNKNOWN_LOCATION
    }
}

private fun CurrentWeatherResponse.buildTemperatureString(
    numberFormatter: NumberFormat,
    context: Context
): CharSequence {
    val tempSymbol = context.getString(R.string.temperature_celsius_symbol)

    val temperatureRaw = numberFormatter.format(temp) + tempSymbol
    val temperatureFeelsLike = numberFormatter.format(feelsLike) + tempSymbol

    val resource = context.getString(
        R.string.temperature_pattern,
        temperatureRaw,
        temperatureFeelsLike
    )

    val tempRawStart = resource.indexOf(temperatureRaw)
    val tempRawEnd = tempRawStart + temperatureRaw.length

    val tempFeelsLikeStart = resource.indexOf(temperatureFeelsLike, tempRawEnd)
    val tempFeelsLikeEnd = tempFeelsLikeStart + temperatureFeelsLike.length

    val sb = SpannableStringBuilder(resource)

    sb.setSpan(
        StyleSpan(Typeface.BOLD),
        tempRawStart,
        tempRawEnd,
        Spanned.SPAN_INCLUSIVE_EXCLUSIVE
    )

    sb.setSpan(
        StyleSpan(Typeface.BOLD),
        tempFeelsLikeStart,
        tempFeelsLikeEnd,
        Spanned.SPAN_INCLUSIVE_EXCLUSIVE
    )

    return sb
}
