package com.mhss.app.shifak.presentation.pharmacy


data class PharmacyHomeUiState(
    val id: Int = 0,
    val name: String = "",
    val logo: String = "",
    val hasNotifications: Boolean = false,
    val error: String? = ""
)
