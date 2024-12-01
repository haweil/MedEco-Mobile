package com.mhss.app.shifak.data.remote.pharmacy.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApproveRequestBody(
    @SerialName("is_approved") val approved: Boolean = true
)
