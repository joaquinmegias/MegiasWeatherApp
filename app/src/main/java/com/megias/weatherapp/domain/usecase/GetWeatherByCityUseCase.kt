package com.megias.weatherapp.domain.usecase

import com.megias.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherByCityUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(city: String) =
        repository.getWeatherByCity(city)
}