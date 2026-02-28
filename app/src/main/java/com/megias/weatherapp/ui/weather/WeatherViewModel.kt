package com.megias.weatherapp.ui.weather

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.megias.weatherapp.BuildConfig
import com.megias.weatherapp.data.repository.WeatherRepository
import com.megias.weatherapp.ui.weather.WeatherUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Idle)
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()
    
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

    fun loadWeather(city: String) {
        viewModelScope.launch {
            Log.d("API_KEY", BuildConfig.OPEN_WEATHER_API_KEY)
            _uiState.value = WeatherUiState.Loading
            try {
                val result = repository.getWeatherByCity(city)
                _uiState.value = WeatherUiState.Success(result)
            } catch (e: Exception) {
                _uiState.value =
                    WeatherUiState.Error(e.message ?: "error")
            }
        }
    }
}