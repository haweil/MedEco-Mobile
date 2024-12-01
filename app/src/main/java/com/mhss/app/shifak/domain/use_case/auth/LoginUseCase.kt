package com.mhss.app.shifak.domain.use_case.auth

import com.mhss.app.shifak.domain.model.auth.LoginData
import com.mhss.app.shifak.domain.repository.auth.AuthApi
import org.koin.core.annotation.Single

@Single
class LoginUseCase(
    private val api: AuthApi
) {
    suspend operator fun invoke(loginData: LoginData) = api.login(loginData)
}