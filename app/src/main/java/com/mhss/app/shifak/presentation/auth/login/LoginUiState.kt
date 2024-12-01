package com.mhss.app.shifak.presentation.auth.login

import com.mhss.app.shifak.util.UserType

data class LoginUiState(
    val done: Boolean = false,
    val error: String? = null,
    val loading: Boolean = false,
    val userType: UserType = UserType.USER
)