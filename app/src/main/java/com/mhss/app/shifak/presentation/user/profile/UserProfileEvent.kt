package com.mhss.app.shifak.presentation.user.profile

import com.mhss.app.shifak.presentation.common.Screen

sealed class UserProfileEvent {
    data object SignOut : UserProfileEvent()
    data class Navigate(val screen: Screen, val popUp: Boolean = false) : UserProfileEvent()
}
