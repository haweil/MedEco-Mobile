package com.mhss.app.shifak.data.remote.location

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import org.koin.core.annotation.Single
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Single
class LocationManager(context: Context) {

    private val locationProvider = LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    suspend fun getUserLocation() = suspendCoroutine { continuation ->
        locationProvider.lastLocation.addOnSuccessListener { location ->
            continuation.resume(
                location?.let { LatLng(it.latitude, it.longitude) }
            )
        }.addOnFailureListener {
            continuation.resume(null)
        }
    }
}