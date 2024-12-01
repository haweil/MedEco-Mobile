package com.mhss.app.shifak.presentation.notifications

import com.mhss.app.shifak.data.remote.pharmacy.model.GetNetworkRequestsDto

sealed interface Notification

data class NetworkRequestNotification(val request: GetNetworkRequestsDto): Notification
data class BasicNotification(val content: String): Notification