package com.mhss.app.shifak.domain.model.drug

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DrugType(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("unit")
    val unit: String
)