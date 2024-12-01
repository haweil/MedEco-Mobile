package com.mhss.app.shifak.data.remote.auth

import com.mhss.app.shifak.data.remote.NetworkConstants.API_BASE_URL
import com.mhss.app.shifak.data.remote.auth.model.LoginResponse
import com.mhss.app.shifak.data.remote.auth.model.SignUpResponse
import com.mhss.app.shifak.data.remote.auth.model.toLoginRequestBody
import com.mhss.app.shifak.domain.model.auth.LoginData
import com.mhss.app.shifak.domain.model.auth.SignUpData
import com.mhss.app.shifak.domain.repository.auth.AuthApi
import com.mhss.app.shifak.presentation.user.donate.FileManager
import com.mhss.app.shifak.util.formattedForNetwork
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.appendPathSegments
import io.ktor.http.contentType
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import kotlin.coroutines.CoroutineContext

@Single
class AuthApiImpl(
    private val client: HttpClient,
    private val fileManager: FileManager,
    @Named("ioDispatcher") private val ioDispatcher: CoroutineContext,
) : AuthApi {

    override suspend fun login(body: LoginData): LoginResponse {
        return withContext(ioDispatcher) {
            client.post(API_BASE_URL) {
                url {
                    appendPathSegments("login")
                }
                contentType(ContentType.Application.Json)
                setBody(body.toLoginRequestBody())
            }.body<LoginResponse>()
        }
    }

    override suspend fun signUp(
        body: SignUpData,
    ): SignUpResponse {
        return withContext(ioDispatcher) {
            val logoFileInfo = body.pharmacyLogo?.let { fileManager.uriToByteArray(it) }
            client.submitFormWithBinaryData(
                API_BASE_URL,
                formData = formData {
                    append("name", body.fullName)
                    append("email", body.email)
                    append("password", body.password)
                    append("password_confirmation", body.passwordConf)
                    append("phone", body.phone)
                    append("gender", body.gender.networkValue)
                    append("user_type", body.type.networkValue)
                    append("birthdate", body.birthDate.formattedForNetwork())
                    append("national_id", body.nationalId)
                    append("type", body.type.networkValue)
                    body.pharmacyName?.let {
                        append("pharmacy[is_accept_expired]", 1)
                        append("pharmacy[name]", it)
                    }
                    body.pharmacyHotline?.let {
                        append("pharmacy[hotline]", it)
                    }
                    logoFileInfo?.let { info ->
                        append(
                            "pharmacy[logo]",
                            info.bytes,
                            Headers.build {
                                append(HttpHeaders.ContentType, info.mimeType)
                                append(
                                    HttpHeaders.ContentDisposition,
                                    "filename=${info.name}"
                                )
                            })
                    }

                }
            ) {
                url {
                    appendPathSegments("register")
                }
                contentType(ContentType.Application.Json)

            }.body<SignUpResponse>()
        }
    }

}