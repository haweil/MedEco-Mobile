package com.mhss.app.shifak.domain.model.pharmacy


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PharmacyBranch(
    @SerialName("id") val id: Int,
    @SerialName("phone") val phone: String,
    @SerialName("lat") val lat: Double?,
    @SerialName("lng") val lng: Double?,
    @SerialName("address") val address: String? = null,
    @SerialName("pharmacy") val pharmacy: Pharmacy? = null,
)
