package com.mhss.app.shifak.domain.use_case.user

import com.mhss.app.shifak.data.remote.user.UserRepository
import org.koin.core.annotation.Single

@Single
class GetAllPharmaciesUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke() = repository.getAllPharmacies()
}