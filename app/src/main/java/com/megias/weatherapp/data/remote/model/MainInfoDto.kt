package com.megias.weatherapp.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class MainInfoDto(
    val temp: Double,
    val temp_min: Double? = null,
    val temp_max: Double? = null,
)
