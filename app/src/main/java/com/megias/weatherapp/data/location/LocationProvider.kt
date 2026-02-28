package com.megias.weatherapp.data.location

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import kotlinx.coroutines.tasks.await

class LocationProvider @Inject constructor(
    @param:ApplicationContext private val context: Context
) {

    private val fusedClient =
        LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    suspend fun getLastLocation(): Pair<Double, Double>? {
        val location = fusedClient.lastLocation.await()
        return location?.let { it.latitude to it.longitude }
    }
}