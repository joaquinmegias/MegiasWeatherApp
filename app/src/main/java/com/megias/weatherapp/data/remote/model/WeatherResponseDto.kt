package com.megias.weatherapp.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponseDto(
    val name: String,
    val weather: List<WeatherItemDto>,
    val main: MainInfoDto,
    val wind: WindInfoDto
)

@Serializable
data class WeatherItemDto(
    val description: String,
    val icon: String
)

@Serializable
data class MainInfoDto(
    val temp: Double,
    val temp_min: Double,
    val temp_max: Double
)

@Serializable
data class WindInfoDto(
    val speed: Double,
    val deg: Double
)