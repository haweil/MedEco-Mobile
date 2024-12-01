package com.mhss.app.shifak.data.remote.auth.model

import com.mhss.app.shifak.data.remote.pharmacy.model.PharmacyDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("message")
    val message: String,
    @SerialName("token")
    val token: String,
    @SerialName("user")
    val user: UserDto? = null,
    @SerialName("pharmacy")
    val pharmacy: PharmacyDto? = null
)