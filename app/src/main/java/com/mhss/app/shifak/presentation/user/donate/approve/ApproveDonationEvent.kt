package com.mhss.app.shifak.presentation.user.donate.approve

sealed class ApproveDonationEvent {
    data class Approve(val id: String): ApproveDonationEvent()
    data class GetOrder(val id: String): ApproveDonationEvent()
}