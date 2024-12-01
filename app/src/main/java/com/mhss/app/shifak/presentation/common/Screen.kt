package com.mhss.app.shifak.presentation.common

import com.mhss.app.shifak.util.UserType
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {

    @Serializable
    data object AuthGraph : Screen()

    @Serializable
    data object UserGraph : Screen()

    @Serializable
    data object PharmacyGraph : Screen()

    @Serializable
    data object OnboardingScreen : Screen()

    @Serializable
    data object NotificationsScreen : Screen()

    @Serializable
    data object AccountTypeScreen : Screen()

    @Serializable
    data class AuthScreen(val userType: UserType) : Screen()

    @Serializable
    data class LoginScreen(val userType: UserType) : Screen()

    @Serializable
    data object UserSignUpScreen : Screen()

    @Serializable
    data object PharmacySignUpScreen : Screen()

    @Serializable
    data object UserHomeScreen : Screen()

    @Serializable
    data object PharmacyHomeScreen : Screen()

    @Serializable
    data object PharmacyMainScreen : Screen()

    @Serializable
    data class ManageBranchesScreen(val pharmacyId: Int) : Screen()

    @Serializable
    data object RequestMedicineNetworkScreen : Screen()

    @Serializable
    data object NetworkRequestsScreen : Screen()

    @Serializable
    data object UserMainScreen : Screen()

    @Serializable
    data object NearbyPharmaciesScreen: Screen()

    @Serializable
    data class DonationDataScreen(val branchId: Int, val pharmacyName: String): Screen()

    @Serializable
    data object ApproveDonationScreen : Screen()

    @Serializable
    data object SmartAssistantScreen : Screen()

    @Serializable
    data object UserProfileScreen : Screen()

    @Serializable
    data object TransactionsScreen : Screen()

    @Serializable
    data object FAQScreen : Screen()

}