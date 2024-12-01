package com.mhss.app.shifak.presentation.user.home

sealed class UserHomeEvent {
    data object Refresh : UserHomeEvent()
}