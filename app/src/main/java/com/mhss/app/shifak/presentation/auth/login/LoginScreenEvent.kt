package com.mhss.app.shifak.presentation.auth.login

import com.mhss.app.shifak.domain.model.auth.LoginData
import com.mhss.app.shifak.presentation.common.Screen

sealed class LoginScreenEvent {
    data class Login(val loginData: LoginData) : LoginScreenEvent()
    data object NavigateUp : LoginScreenEvent()
    data object ForgotPassword : LoginScreenEvent()
    data class Navigate(val screen: Screen, val popUp: Boolean = false) : LoginScreenEvent()
}
