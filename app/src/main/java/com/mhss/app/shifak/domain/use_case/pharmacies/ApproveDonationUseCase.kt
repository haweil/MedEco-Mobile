package com.mhss.app.shifak.domain.use_case.pharmacies

import com.mhss.app.shifak.data.remote.pharmacy.PharmacyRepository
import org.koin.core.annotation.Single

@Single
class ApproveDonationUseCase(
    private val repository: PharmacyRepository
) {
    suspend operator fun invoke(id: String) = repository.approveDonateOrder(id)
}