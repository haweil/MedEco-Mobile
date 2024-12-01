package com.mhss.app.shifak.data.remote.user.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateOrderBody(
    @SerialName("pharmacy_branch_id") val branchId: Int,
    @SerialName("drug_ids") val ids: List<IdWithQuantity>
)

@Serializable
data class IdWithQuantity(
    @SerialName("drug_id") val id: Int,
    @SerialName ("quantity") val qty: Int
)
