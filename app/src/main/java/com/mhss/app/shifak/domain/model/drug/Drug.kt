package com.mhss.app.shifak.domain.model.drug

import kotlinx.serialization.SerialName

import kotlinx.serialization.Serializable

@Serializable
data class Drug(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("drug_type")
    val drugType: DrugType,
    @SerialName("points")
    val points: Int
)
