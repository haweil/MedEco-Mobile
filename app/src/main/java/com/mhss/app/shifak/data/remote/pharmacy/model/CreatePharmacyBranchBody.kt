package com.mhss.app.shifak.data.remote.pharmacy.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreatePharmacyBranchBody(
    @SerialName("address")
    val address: String,
    @SerialName("commercial_registration_number")
    val commercialRegistrationNumber: String,
//    @SerialName("is_open")
//    val isOpen: String,
    @SerialName("pharmacy_id")
    val pharmacyId: String,
    @SerialName("phone")
    val phone: String,
    @SerialName("tax_number")
    val taxNumber: String,
    @SerialName("lat")
    val lat: Double,
    @SerialName("lng")
    val lng: Double,
    )