package com.megias.weatherapp.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    val name: String,
    val weather: List<WeatherItem>,
    val main: MainInfo,
    val wind: WindInfo
)

@Serializable
data class WeatherItem(
    val description: String,
    val icon: String
)

@Serializable
data class MainInfo(
    val temp: Double,
    val temp_min: Double,
    val temp_max: Double
)

@Serializable
data class WindInfo(
    val speed: Double,
    val deg: Double
)