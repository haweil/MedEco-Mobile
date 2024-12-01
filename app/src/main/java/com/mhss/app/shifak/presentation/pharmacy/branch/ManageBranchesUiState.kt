package com.mhss.app.shifak.presentation.pharmacy.branch

import com.mhss.app.shifak.domain.model.pharmacy.PharmacyBranch

data class ManageBranchesUiState(
    val loading: Boolean = false,
    val branches: List<PharmacyBranch> = emptyList(),
    val error: String? = null
)
