package com.mhss.app.shifak.presentation.auth.signup

import com.mhss.app.shifak.util.UserType

data class SignUpUiState(
    val done: Boolean = false,
    val error: String? = null,
    val loading: Boolean = false,
    val userType: UserType = UserType.USER
)