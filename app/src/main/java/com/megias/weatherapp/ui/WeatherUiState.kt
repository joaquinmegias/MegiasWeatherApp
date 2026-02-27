package com.megias.weatherapp.ui

import com.megias.weatherapp.data.remote.model.WeatherResponse

sealed interface WeatherUiState {
    object Idle : WeatherUiState
    object Loading : WeatherUiState
    data class Success(val data: WeatherResponse) : WeatherUiState
    data class Error(val message: String) : WeatherUiState
}