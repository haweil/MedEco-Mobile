package com.mhss.app.shifak.presentation.notifications

import com.mhss.app.shifak.data.remote.pharmacy.model.GetNetworkRequestsDto
import com.mhss.app.shifak.presentation.pharmacy.network.PharmacyNetworkEvent

sealed class NotificationsScreenEvent {
    data class ApproveRequest(val request: GetNetworkRequestsDto): NotificationsScreenEvent()

}