package com.mhss.app.shifak.data.remote.pharmacy

import com.mhss.app.shifak.data.remote.pharmacy.model.ApproveRequestBody
import com.mhss.app.shifak.data.remote.pharmacy.model.ApproveUserOrderBody
import com.mhss.app.shifak.data.remote.pharmacy.model.CreatePharmacyBranchBody
import com.mhss.app.shifak.data.remote.pharmacy.model.GetAllPharmaciesDto
import com.mhss.app.shifak.data.remote.pharmacy.model.GetDonationDto
import com.mhss.app.shifak.data.remote.pharmacy.model.GetNetworkRequestsDto
import com.mhss.app.shifak.data.remote.pharmacy.model.NetworkRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.appendPathSegments
import io.ktor.http.isSuccess
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import kotlin.coroutines.CoroutineContext

@Single
class PharmacyApi(
    private val client: HttpClient,
    @Named("ioDispatcher") private val ioDispatcher: CoroutineContext,
) {

    suspend fun makeNetworkRequest(body: NetworkRequest) =
        withContext(ioDispatcher) {
            client.post {
                url {
                    appendPathSegments("pharmacy", "requests")
                }
                setBody(body)
            }.body<GetAllPharmaciesDto>()
        }

    suspend fun approveRequest(id: Int) =
        withContext(ioDispatcher) {
            client.post {
                url {
                    appendPathSegments("pharmacy", "network", "$id")
                }
                setBody(ApproveRequestBody())
            }.body<GetAllPharmaciesDto>()
        }

    suspend fun createPharmacyBranch(body: CreatePharmacyBranchBody) =
        withContext(ioDispatcher) {
            val success = client.post {
                url {
                    appendPathSegments("pharmacy", "pharmacy-branches")
                }
                setBody(body)
            }.status.isSuccess()
            if (!success) throw Exception("Create branch failed")
        }

    suspend fun approveDonateOrder(id: String) = withContext(ioDispatcher) {
        val response = client.post {
            url {
                appendPathSegments("pharmacy", "orders", id)
            }
            setBody(ApproveUserOrderBody())
        }
        if (!response.status.isSuccess()) throw Exception("Approve order failed")
    }

    suspend fun getAllNetworkRequests() = withContext(ioDispatcher) {
        client.get {
            url {
                appendPathSegments("pharmacy", "network")
            }
        }.body<List<GetNetworkRequestsDto>>()
    }

    suspend fun getDonationRequest(id: String) = withContext(ioDispatcher) {
        client.get {
            url {
                appendPathSegments("pharmacy", "orders", id)
            }
        }.body<GetDonationDto>()
    }

}