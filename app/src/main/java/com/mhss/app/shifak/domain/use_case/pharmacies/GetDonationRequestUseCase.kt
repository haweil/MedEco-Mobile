package com.mhss.app.shifak.domain.use_case.pharmacies

import com.mhss.app.shifak.data.remote.pharmacy.PharmacyRepository
import org.koin.core.annotation.Single

@Single
class GetDonationRequestUseCase(
    private val repository: PharmacyRepository
) {
    suspend operator fun invoke(id: String) = repository.getDonationRequest(id)
}