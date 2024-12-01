package com.mhss.app.shifak.domain.use_case.user

import com.mhss.app.shifak.data.remote.user.UserRepository
import com.mhss.app.shifak.data.remote.user.model.CreateOrderBody
import org.koin.core.annotation.Single

@Single
class MakeDonateOrderUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(data: CreateOrderBody) = repository.makeDonateOrder(data)
}