package com.mhss.app.shifak.data.remote.pharmacy.model

import com.mhss.app.shifak.domain.model.pharmacy.Pharmacy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetAllPharmaciesDto(
    @SerialName("data") val pharmacies: List<Pharmacy>
)