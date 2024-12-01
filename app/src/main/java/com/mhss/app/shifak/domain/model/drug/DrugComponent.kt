package com.mhss.app.shifak.domain.model.drug

data class DrugComponent(
    val id: Int,
    val name: String,
    val interactions: List<DrugComponent>? = null
)
