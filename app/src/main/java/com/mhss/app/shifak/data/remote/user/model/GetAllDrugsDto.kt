package com.mhss.app.shifak.data.remote.user.model

import com.mhss.app.shifak.domain.model.drug.Drug
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetAllDrugsDto(
    @SerialName("data") val data: List<Drug>
)