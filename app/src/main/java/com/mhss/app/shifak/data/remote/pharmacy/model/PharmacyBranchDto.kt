package com.mhss.app.shifak.data.remote.pharmacy.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PharmacyBranchDto(
    @SerialName("address")
    val address: String,
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String
)