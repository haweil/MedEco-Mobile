package com.mhss.app.shifak.data.remote.pharmacy.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApproveUserOrderBody(
    @SerialName("is_completed") val completed: Boolean = true
)
