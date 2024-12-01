package com.mhss.app.shifak.presentation.pharmacy

sealed class PharmacyHomeEvent {
    data object Refresh : PharmacyHomeEvent()
}