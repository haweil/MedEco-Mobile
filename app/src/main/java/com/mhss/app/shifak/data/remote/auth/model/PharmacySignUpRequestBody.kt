package com.mhss.app.shifak.data.remote.auth.model

import com.mhss.app.shifak.domain.model.auth.PharmacySignUpData
import com.mhss.app.shifak.util.UserType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PharmacySignUpRequestBody(
    @SerialName("name") val name: String,
    @SerialName("email") val email: String,
    @SerialName("password") val password: String,
    @SerialName("password_confirmation") val passwordConfirmation: String,
    @SerialName("phone") val phone: String,
    @SerialName("gender") val gender: String,
    @SerialName("national_id") val nationalId: String,
    @SerialName("birthdate") val birthdate: String,
    @SerialName("pharmacy[name]") val pharmacyName: String,
    @SerialName("pharmacy[hotline]") val pharmacyHotline: String,
    @SerialName("pharmacy[logo]") val pharmacyLogo: ByteArray,
    @SerialName("pharmacy[is_accept_expired]") val acceptsExpired: Int,
    @SerialName("type") val type: String = UserType.PHARMACY.networkValue
)

fun PharmacySignUpData.toRequestBody(): SignUpRequestBody {
    return SignUpRequestBody(
        name = name,
        email = email,
        password = password,
        passwordConfirmation = passwordConfirmation,
        phone = phone,
        gender = gender.networkValue,
        nationalId = nationalId,
        birthdate = birthdate.toString(),
        pharmacyName = pharmacyName,
        pharmacyHotline = pharmacyHotline,
        pharmacyLogo = pharmacyLogo,
        type = UserType.PHARMACY.networkValue
    )
}