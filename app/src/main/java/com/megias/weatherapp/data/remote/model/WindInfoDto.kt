package com.megias.weatherapp.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class WindInfoDto(
    val speed: Double,
    val deg: Double
)