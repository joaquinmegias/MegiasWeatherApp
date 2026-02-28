package com.megias.weatherapp.ui.weather

import com.megias.weatherapp.domain.model.Weather

sealed interface WeatherUiState {
    object Idle : WeatherUiState
    object Loading : WeatherUiState
    data class Success(val data: Weather) : WeatherUiState
    data class Error(val message: String) : WeatherUiState
}