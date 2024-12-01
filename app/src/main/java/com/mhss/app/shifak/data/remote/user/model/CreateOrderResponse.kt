package com.mhss.app.shifak.data.remote.user.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateOrderResponse(
    @SerialName("order_number") val orderId: String,
    @SerialName("expected_points") val points: Int
)
