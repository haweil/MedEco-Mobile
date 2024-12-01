package com.mhss.app.shifak.domain.model.order

import com.mhss.app.shifak.domain.model.drug.Drug
import com.mhss.app.shifak.domain.model.pharmacy.PharmacyBranch
import com.mhss.app.shifak.domain.model.user.User

data class Order(
    val id: Int,
    val qty: Int,
    val user: User,
    val drug: Drug,
    val pharmacyBranch: PharmacyBranch,
    val isCompleted: Boolean,
    val createdAt: Long,
    val updatedAt: Long
)