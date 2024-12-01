package com.mhss.app.shifak.domain.use_case.pharmacies

import com.mhss.app.shifak.data.remote.pharmacy.PharmacyRepository
import com.mhss.app.shifak.data.remote.pharmacy.model.GetNetworkRequestsDto
import org.koin.core.annotation.Single

@Single
class ApproveNetworkRequestUseCase(
    private val pharmacyRepository: PharmacyRepository,
) {
    suspend operator fun invoke(request: GetNetworkRequestsDto) =
        pharmacyRepository.approveRequest(request.id)
}