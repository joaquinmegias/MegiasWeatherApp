package com.megias.weatherapp.data.mapper

import com.megias.weatherapp.data.remote.model.WeatherResponseDto
import com.megias.weatherapp.domain.model.WeatherDomain

fun WeatherResponseDto.toDomain(): WeatherDomain {

    val weatherItem = weather.firstOrNull()

    return WeatherDomain(
        cityName = name,
        temperature = main.temp,
        minTemperature = main.tempMin,
        maxTemperature = main.tempMax,
        windSpeed = wind.speed,
        windDegrees = wind.deg,
        iconCode = weatherItem?.icon ?: "",
        description = weatherItem?.description ?: ""
    )
}