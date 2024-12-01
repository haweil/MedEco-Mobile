package com.mhss.app.shifak.data.remote.auth.model

import com.mhss.app.shifak.domain.model.auth.LoginData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestBody(
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val password: String,
    @SerialName("type")
    val type: String
)

fun LoginData.toLoginRequestBody(): LoginRequestBody {
    return LoginRequestBody(
        email = email,
        password = password,
        type = type.networkValue
    )
}
