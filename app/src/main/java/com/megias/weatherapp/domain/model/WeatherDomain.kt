package com.megias.weatherapp.domain.model

data class WeatherDomain(
    val cityName: String,
    val temperature: Double,
    val minTemperature: Double?,
    val maxTemperature: Double?,
    val windSpeed: Double?,
    val windDegrees: Double?,
    val iconCode: String,
    val description: String
)