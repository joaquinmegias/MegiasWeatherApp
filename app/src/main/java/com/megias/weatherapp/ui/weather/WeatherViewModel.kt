package com.megias.weatherapp.ui.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.megias.weatherapp.data.repository.WeatherRepository
import com.megias.weatherapp.ui.weather.WeatherUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Idle)
    val uiState: StateFlow<WeatherUiState> = _uiState

    fun loadWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            _uiState.value = WeatherUiState.Loading

            try {
                val response = repository.getWeather(lat, lon)
                _uiState.value = WeatherUiState.Success(response)
            } catch (e: Exception) {
                _uiState.value = WeatherUiState.Error(
                    e.message ?: "error"
                )
            }
        }
    }
}