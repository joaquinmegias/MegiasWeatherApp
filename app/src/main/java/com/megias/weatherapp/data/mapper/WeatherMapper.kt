package com.megias.weatherapp.data.mapper

import com.megias.weatherapp.data.remote.model.WeatherResponseDto
import com.megias.weatherapp.domain.model.Weather

private const val ICON_BASE_URL =
    "https://openweathermap.org/img/wn/"

fun WeatherResponseDto.toDomain(): Weather {

    val weatherItem = weather.firstOrNull()

    return Weather(
        cityName = name,
        temperature = main.temp,
        minTemperature = main.temp_min,
        maxTemperature = main.temp_max,
        windSpeed = wind.speed,
        windDegrees = wind.deg,
        iconUrl = weatherItem?.icon?.let {
            "$ICON_BASE_URL${it}@2x.png"
        } ?: "",
        description = weatherItem?.description ?: ""
    )
}