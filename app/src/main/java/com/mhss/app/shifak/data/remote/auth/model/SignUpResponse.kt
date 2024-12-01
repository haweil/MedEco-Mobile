package com.mhss.app.shifak.data.remote.auth.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpResponse(
    @SerialName("token") val token: String,
    @SerialName("user") val user: UserDto
)
