package com.megias.weatherapp.ui.weather

import com.megias.weatherapp.domain.model.WeatherDomain

sealed interface WeatherUiState {
    object Idle : WeatherUiState
    object Loading : WeatherUiState
    data class Success(val data: WeatherDomain) : WeatherUiState
    data class Error(val message: String) : WeatherUiState
}