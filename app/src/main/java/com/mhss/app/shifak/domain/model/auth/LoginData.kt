package com.mhss.app.shifak.domain.model.auth

import com.mhss.app.shifak.util.UserType

data class LoginData(
    val email: String,
    val password: String,
    val type: UserType = UserType.USER
)
