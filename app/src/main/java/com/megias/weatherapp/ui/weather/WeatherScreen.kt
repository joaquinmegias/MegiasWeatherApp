package com.megias.weatherapp.ui.weather

import android.Manifest
import android.content.res.Configuration
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = hiltViewModel()
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) viewModel.loadWeatherFromLocation()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(if (isLandscape) 4.dp else 24.dp))
        Text(
            text = "Weather App",
            style = if (isLandscape) MaterialTheme.typography.titleLarge
            else MaterialTheme.typography.headlineMedium
        )
        Spacer(Modifier.height(if (isLandscape) 8.dp else 16.dp))

        if (isLandscape) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.Top
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(end = 16.dp, bottom = 8.dp)
                ) {
                    CitySelector(
                        modifier = Modifier.weight(1f),
                        onCitySelected = { viewModel.loadWeather(it) })
                    Spacer(Modifier.height(8.dp))
                    LocationButton(onClick = { permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION) })
                }
                Column(
                    modifier = Modifier
                        .weight(1.2f)
                        .fillMaxHeight()
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    WeatherStateContent(uiState, onRetry = { viewModel.retry() })
                }
            }
        } else {
            CitySelector(
                modifier = Modifier.heightIn(max = 250.dp),
                onCitySelected = { viewModel.loadWeather(it) })
            Spacer(Modifier.height(12.dp))
            LocationButton(onClick = {
                permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            })
            Spacer(Modifier.height(24.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WeatherStateContent(uiState, onRetry = { viewModel.retry() })
            }
        }
    }
}