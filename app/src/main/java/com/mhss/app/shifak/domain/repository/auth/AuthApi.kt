package com.mhss.app.shifak.domain.repository.auth

import com.mhss.app.shifak.data.remote.auth.model.LoginResponse
import com.mhss.app.shifak.data.remote.auth.model.SignUpResponse
import com.mhss.app.shifak.domain.model.auth.LoginData
import com.mhss.app.shifak.domain.model.auth.SignUpData

interface AuthApi {

    suspend fun login(body: LoginData): LoginResponse

    suspend fun signUp(body: SignUpData): SignUpResponse
}