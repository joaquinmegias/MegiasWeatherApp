package com.megias.weatherapp.domain.usecase

import com.megias.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherByLocationUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(lat: Double, lon: Double) =
        repository.getWeather(lat, lon)
}