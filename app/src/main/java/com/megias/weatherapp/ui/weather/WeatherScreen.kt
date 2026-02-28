package com.megias.weatherapp.ui.weather

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CitySelector(
            onCitySelected = { city ->
                viewModel.loadWeather(city)
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        when (val state = uiState) {

            WeatherUiState.Idle -> {
                Text("Select a city")
            }

            WeatherUiState.Loading -> {
                CircularProgressIndicator()
            }

            is WeatherUiState.Error -> {
                Text(
                    text = state.message,
                    color = MaterialTheme.colorScheme.error
                )
            }

            is WeatherUiState.Success -> {
                WeatherContent(state.data)
            }
        }
    }
}

@Composable
fun CitySelector(
    onCitySelected: (String) -> Unit
) {

    val cities = listOf(
        "Montevideo",
        "London",
        "São Paulo",
        "Buenos Aires",
        "Munich"
    )

    Column {
        cities.forEach { city ->
            Button(
                onClick = { onCitySelected(city) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(city)
            }
        }
    }
}

@Composable
fun WeatherContent(data: com.megias.weatherapp.domain.model.Weather) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = data.cityName, style = MaterialTheme.typography.headlineMedium)

        val iconUrl = "https://openweathermap.org/img/wn/${data.iconCode}@2x.png"

        AsyncImage(
            model = iconUrl,
            contentDescription = data.description
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text("Temperature: ${data.temperature} °C")
        Text("Min: ${data.minTemperature} °C")
        Text("Max: ${data.maxTemperature} °C")
        Text("Wind: ${data.windSpeed} m/s")
        Text("Description: ${data.description}")
    }
}