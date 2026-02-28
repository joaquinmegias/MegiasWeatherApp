package com.megias.weatherapp.data.mapper

import com.megias.weatherapp.data.remote.model.WeatherResponseDto
import com.megias.weatherapp.domain.model.Weather

fun WeatherResponseDto.toDomain(): Weather {

    val weatherItem = weather.firstOrNull()

    return Weather(
        cityName = name,
        temperature = main.temp,
        minTemperature = main.temp_min,
        maxTemperature = main.temp_max,
        windSpeed = wind.speed,
        windDegrees = wind.deg,
        iconCode = weatherItem?.icon ?: "",
        description = weatherItem?.description ?: ""
    )
}