package com.mhss.app.shifak.domain.use_case.user

import com.mhss.app.shifak.data.remote.location.LocationManager
import org.koin.core.annotation.Single

@Single
class GetUserLocationUseCase(
    private val locationManager: LocationManager
) {
    suspend operator fun invoke() = locationManager.getUserLocation()
}