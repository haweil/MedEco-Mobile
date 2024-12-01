package com.mhss.app.shifak.presentation.user.donate.approve

import com.mhss.app.shifak.data.remote.pharmacy.model.GetDonationData

data class ApproveDonationUiState(
    val approveLoading: Boolean = false,
    val loading: Boolean = false,
    val order: GetDonationData? = null,
    val success: Boolean = false,
    val error: String? = null
)
