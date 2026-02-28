package com.megias.weatherapp.ui.weather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.megias.weatherapp.domain.model.Weather

@Composable
fun LocationButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
    ) {
        Text("📍 Use my location", style = MaterialTheme.typography.titleMedium)
    }
}

@Composable
fun CitySelector(
    modifier: Modifier = Modifier,
    onCitySelected: (String) -> Unit
) {
    val cities = listOf("Montevideo", "London", "São Paulo", "Buenos Aires", "Munich")

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(cities) { city ->
            Button(
                onClick = { onCitySelected(city) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = city)
            }
        }
    }
}

@Composable
fun WeatherContent(data: Weather) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = data.cityName, style = MaterialTheme.typography.headlineMedium)

            val iconUrl = "https://openweathermap.org/img/wn/${data.iconCode}@2x.png"
            AsyncImage(
                model = iconUrl,
                contentDescription = data.description,
                modifier = Modifier.height(80.dp)
            )

            Text(text = "${data.temperature} °C", style = MaterialTheme.typography.headlineLarge)

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Text("Min: ${data.minTemperature}°")
                Text("Max: ${data.maxTemperature}°")
            }
            Text("Wind: ${data.windSpeed} m/s")

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = data.description.replaceFirstChar { it.uppercase() },
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Composable
fun WeatherStateContent(
    uiState: WeatherUiState,
    onRetry: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when (uiState) {
            WeatherUiState.Idle -> Text("Select a city", style = MaterialTheme.typography.bodyLarge)
            WeatherUiState.Loading -> CircularProgressIndicator()
            is WeatherUiState.Error -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = uiState.message,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = onRetry,
                        shape = RoundedCornerShape(12.dp)
                    ) { Text("Retry") }
                }
            }

            is WeatherUiState.Success -> WeatherContent(uiState.data)
        }
    }
}