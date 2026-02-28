package com.megias.weatherapp.data.mapper

import com.megias.weatherapp.data.remote.model.WeatherResponseDto
import com.megias.weatherapp.domain.model.WeatherDomain

fun WeatherResponseDto.toDomain(): WeatherDomain {
    val weatherItem = weather.firstOrNull()

    return WeatherDomain(
        cityName = name.ifBlank { "—" },
        temperature = main.temp,
        minTemperature = main.temp_min,
        maxTemperature = main.temp_max,
        windSpeed = wind?.speed,
        windDegrees = wind?.deg,
        iconCode = weatherItem?.icon.orEmpty(),
        description = weatherItem?.description.orEmpty()
    )
}
