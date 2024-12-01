package com.mhss.app.shifak.presentation.user.home

import androidx.compose.runtime.Stable
import com.mhss.app.shifak.domain.model.drug.Drug
import com.mhss.app.shifak.domain.model.pharmacy.Pharmacy

@Stable
data class UserHomeUiState(
    val userName: String = "",
    val hasNotifications: Boolean = false,
    val points: Int = 1235,
    val error: String? = ""
)
