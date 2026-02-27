package com.megias.weatherapp.data.repository

import com.megias.weatherapp.BuildConfig
import com.megias.weatherapp.data.remote.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val api: WeatherApi
) {
    suspend fun getWeather(lat: Double, lon: Double) =
        api.getCurrentWeather(
            lat = lat,
            lon = lon,
            apiKey = BuildConfig.OPEN_WEATHER_API_KEY
        )

    suspend fun getWeatherByCity(city: String) =
        api.getWeatherByCity(
            city = city,
            apiKey = BuildConfig.OPEN_WEATHER_API_KEY
        )

}