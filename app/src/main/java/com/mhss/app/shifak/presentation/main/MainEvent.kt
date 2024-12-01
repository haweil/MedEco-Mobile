package com.mhss.app.shifak.presentation.main

import com.mhss.app.shifak.presentation.common.Screen

sealed class MainEvent {
    data class Navigate(val screen: Screen): MainEvent()
}
