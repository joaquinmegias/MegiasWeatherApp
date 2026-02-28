package com.megias.weatherapp.domain.repository

import com.megias.weatherapp.domain.model.WeatherDomain

interface WeatherRepository {
    suspend fun getWeather(lat: Double, lon: Double): WeatherDomain
    suspend fun getWeatherByCity(city: String): WeatherDomain
}