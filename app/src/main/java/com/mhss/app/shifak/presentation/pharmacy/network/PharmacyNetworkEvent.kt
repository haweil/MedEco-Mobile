package com.mhss.app.shifak.presentation.pharmacy.network

import com.mhss.app.shifak.data.remote.pharmacy.model.GetNetworkRequestsDto

sealed class PharmacyNetworkEvent {
    data class MakeRequest(val description: String): PharmacyNetworkEvent()
    data class ApproveRequest(val request: GetNetworkRequestsDto): PharmacyNetworkEvent()
    data object NavigateUp: PharmacyNetworkEvent()
}