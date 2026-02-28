package com.megias.weatherapp.domain.model

data class Weather(
    val cityName: String,
    val temperature: Double,
    val minTemperature: Double,
    val maxTemperature: Double,
    val windSpeed: Double,
    val windDegrees: Double,
    val iconUrl: String,
    val description: String
)