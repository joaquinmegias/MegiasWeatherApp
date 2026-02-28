package com.megias.weatherapp.data.repository

import com.megias.weatherapp.BuildConfig
import com.megias.weatherapp.data.mapper.toDomain
import com.megias.weatherapp.data.remote.WeatherApi
import com.megias.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
) : WeatherRepository {

    override suspend fun getWeather(lat: Double, lon: Double) =
        api.getCurrentWeather(
            lat = lat,
            lon = lon,
            apiKey = BuildConfig.OPEN_WEATHER_API_KEY
        ).toDomain()

    override suspend fun getWeatherByCity(city: String) =
        api.getWeatherByCity(
            city = city,
            apiKey = BuildConfig.OPEN_WEATHER_API_KEY
        ).toDomain()
}
