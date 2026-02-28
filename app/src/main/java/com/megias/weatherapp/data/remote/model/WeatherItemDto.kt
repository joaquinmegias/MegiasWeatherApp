package com.megias.weatherapp.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class WeatherItemDto(
    val description: String,
    val icon: String
)
