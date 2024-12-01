package com.mhss.app.shifak.data.remote.pharmacy

import com.mhss.app.shifak.data.remote.pharmacy.model.CreatePharmacyBranchBody
import com.mhss.app.shifak.data.remote.pharmacy.model.NetworkRequest
import com.mhss.app.shifak.data.remote.user.UserApi
import org.koin.core.annotation.Single

@Single
class PharmacyRepository(
    private val pharmacyApi: PharmacyApi
) {

    suspend fun createBranch(data: CreatePharmacyBranchBody) = pharmacyApi.createPharmacyBranch(data)

    suspend fun makeNetworkRequest(description: NetworkRequest) = pharmacyApi.makeNetworkRequest(description)

    suspend fun getAllNetworkRequests() = pharmacyApi.getAllNetworkRequests()

    suspend fun approveRequest(requestId: Int) = pharmacyApi.approveRequest(requestId)

    suspend fun approveDonateOrder(id: String) = pharmacyApi.approveDonateOrder(id)

    suspend fun getDonationRequest(id: String) = pharmacyApi.getDonationRequest(id)
}