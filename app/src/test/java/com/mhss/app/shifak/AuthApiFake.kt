package com.mhss.app.shifak

import com.mhss.app.shifak.data.remote.auth.model.LoginResponse
import com.mhss.app.shifak.data.remote.auth.model.SignUpResponse
import com.mhss.app.shifak.data.remote.auth.model.UserDto
import com.mhss.app.shifak.domain.model.auth.LoginData
import com.mhss.app.shifak.domain.model.auth.SignUpData
import com.mhss.app.shifak.domain.repository.auth.AuthApi

class FakeAuthApi : AuthApi {

    var shouldReturnError = true

    override suspend fun login(body: LoginData): LoginResponse {
        return if (shouldReturnError) {
            LoginResponse(token = "", user = fakeUser, message = "")
        } else {
            LoginResponse(token = "fakeToken", user = fakeUser, message = "")
        }
    }

    override suspend fun signUp(body: SignUpData): SignUpResponse {
        return if (shouldReturnError) {
            SignUpResponse(token = "")
        } else {
            SignUpResponse(token = "token")
        }
    }

    private val fakeUser = UserDto(
        id = 0, name = "Moahmed", type = "user", avatar = null
    )
}