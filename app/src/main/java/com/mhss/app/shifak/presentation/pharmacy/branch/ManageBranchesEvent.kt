package com.mhss.app.shifak.presentation.pharmacy.branch

import com.mhss.app.shifak.data.remote.pharmacy.model.CreatePharmacyBranchBody

sealed class ManageBranchesEvent {
    data class AddBranch(val branch: CreatePharmacyBranchBody): ManageBranchesEvent()
}