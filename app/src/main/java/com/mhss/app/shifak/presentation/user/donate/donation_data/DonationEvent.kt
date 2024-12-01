package com.mhss.app.shifak.presentation.user.donate.donation_data

import com.mhss.app.shifak.domain.model.drug.Drug

sealed class DonationEvent {
    data class MakeOrder(val data: Map<Drug, Int>, val branchId: Int): DonationEvent()
    data object Done: DonationEvent()
}