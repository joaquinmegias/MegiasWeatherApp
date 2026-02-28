package com.megias.weatherapp.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class MainInfoDto(
    val temp: Double,
    val tempMin: Double,
    val tempMax: Double
)
