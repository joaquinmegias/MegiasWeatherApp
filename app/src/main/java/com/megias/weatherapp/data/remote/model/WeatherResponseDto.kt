package com.megias.weatherapp.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponseDto(
    val name: String,
    val weather: List<WeatherItemDto> = emptyList(),
    val main: MainInfoDto,
    val wind: WindInfoDto? = null
)
