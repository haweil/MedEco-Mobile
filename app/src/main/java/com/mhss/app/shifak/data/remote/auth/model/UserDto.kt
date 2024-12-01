package com.mhss.app.shifak.data.remote.auth.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("email")
    val type: String,
    @SerialName("avatar")
    val avatar: String? = null
)