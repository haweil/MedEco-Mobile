package com.mhss.app.shifak.data.remote.pharmacy.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class GetDonationDto(
    @SerialName("data") val data: GetDonationData
)

@Serializable
data class GetDonationData(
    @SerialName("id") val id: Int,
    @SerialName("order_items") val items: List<DonationDrugItem>,
    @SerialName("points_earned") val points: Int
)


@Serializable
class DonationDrugItem(
    @SerialName("drug") val drug: DonationDrug,
    @SerialName("quantity") val quantity: Int
)

@Serializable
class DonationDrug(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String
)