package com.mhss.app.shifak.domain.use_case.pharmacies

import com.mhss.app.shifak.data.remote.pharmacy.PharmacyRepository
import com.mhss.app.shifak.data.remote.pharmacy.model.CreatePharmacyBranchBody
import org.koin.core.annotation.Single

@Single
class CreatePharmacyBranchUseCase(
    private val repository: PharmacyRepository
) {
    suspend operator fun invoke(data: CreatePharmacyBranchBody) = repository.createBranch(data)
}