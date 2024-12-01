package com.mhss.app.shifak.data.remote.auth.model

import com.mhss.app.shifak.domain.model.auth.SignUpData
import com.mhss.app.shifak.util.formattedForNetwork
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class SignUpRequestBody(
    @SerialName("name") val name: String,
    @SerialName("email") val email: String,
    @SerialName("password") val password: String,
    @SerialName("password_confirmation") val passwordConfirmation: String,
    @SerialName("phone") val phone: String,
    @SerialName("type") val type: String,
    @SerialName("gender") val gender: String,
    @SerialName("national_id") val nationalId: String,
    @SerialName("birthdate") val birthdate: String,
    @SerialName("pharmacy[name]") val pharmacyName: String? = null,
    @SerialName("pharmacy[hotline]") val pharmacyHotline: String? = null,
    @SerialName("pharmacy[logo]") val pharmacyLogo: ByteArray? = null,
)

fun SignUpData.toRequestBody(): SignUpRequestBody {
    return SignUpRequestBody(
        name = fullName,
        email = email,
        password = password,
        passwordConfirmation = passwordConf,
        phone = phone,
        type = type.networkValue,
        gender = gender.networkValue,
        nationalId = nationalId,
        birthdate = birthDate.formattedForNetwork()
    )
}


