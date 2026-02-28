package com.megias.weatherapp.ui.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.megias.weatherapp.data.location.LocationProvider
import com.megias.weatherapp.domain.usecase.GetWeatherByCityUseCase
import com.megias.weatherapp.domain.usecase.GetWeatherByLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherByCityUseCase: GetWeatherByCityUseCase,
    private val getWeatherByLocationUseCase: GetWeatherByLocationUseCase,
    private val locationProvider: LocationProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Idle)
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()
    private var lastCityRequested: String? = null
    private var lastRequestWasLocation: Boolean = false

    fun loadWeather(city: String) {
        viewModelScope.launch {
            lastCityRequested = city
            lastRequestWasLocation = false

            _uiState.value = WeatherUiState.Loading

            try {
                val result = getWeatherByCityUseCase(city)
                _uiState.value = WeatherUiState.Success(result)
            } catch (e: Exception) {
                _uiState.value =
                    WeatherUiState.Error(
                        e.message ?: "Unable to fetch weather data"
                    )
            }
        }
    }

    fun loadWeatherFromLocation() {
        viewModelScope.launch {
            lastCityRequested = null
            lastRequestWasLocation = true

            _uiState.value = WeatherUiState.Loading

            try {
                val location = locationProvider.getLastLocation()

                if (location != null) {
                    val (lat, lon) = location
                    val result = getWeatherByLocationUseCase(lat, lon)
                    _uiState.value = WeatherUiState.Success(result)
                } else {
                    _uiState.value =
                        WeatherUiState.Error("Location unavailable")
                }

            } catch (e: Exception) {
                _uiState.value =
                    WeatherUiState.Error(
                        e.message ?: "Unable to fetch location weather"
                    )
            }
        }
    }

    fun retry() {
        if (lastRequestWasLocation) {
            loadWeatherFromLocation()
        } else {
            lastCityRequested?.let {
                loadWeather(it)
            }
        }
    }
}