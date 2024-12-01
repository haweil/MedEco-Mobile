package com.mhss.app.shifak.domain.use_case.pharmacies

import com.mhss.app.shifak.data.remote.pharmacy.model.NetworkRequest
import com.mhss.app.shifak.data.remote.pharmacy.PharmacyRepository
import org.koin.core.annotation.Single

@Single
class MakeNetworkRequestUseCase(
    private val pharmacyRepository: PharmacyRepository
) {
    suspend operator fun invoke(request: NetworkRequest) = pharmacyRepository.makeNetworkRequest(request)
}