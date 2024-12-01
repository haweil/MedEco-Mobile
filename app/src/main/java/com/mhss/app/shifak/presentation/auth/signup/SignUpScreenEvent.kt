package com.mhss.app.shifak.presentation.auth.signup

import com.mhss.app.shifak.domain.model.auth.SignUpData
import com.mhss.app.shifak.presentation.common.Screen

sealed class SignUpScreenEvent {
    data class SignUp(val signUpData: SignUpData) : SignUpScreenEvent()
    data object NavigateUp : SignUpScreenEvent()
    data class Navigate(val screen: Screen, val popUp: Boolean = false) : SignUpScreenEvent()
}
