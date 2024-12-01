package com.mhss.app.shifak.presentation.pharmacy.nav

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.mhss.app.shifak.presentation.assistant.AssistantScreen
import com.mhss.app.shifak.presentation.assistant.AssistantViewModel
import com.mhss.app.shifak.presentation.common.Screen
import com.mhss.app.shifak.presentation.notifications.NotificationsScreen
import com.mhss.app.shifak.presentation.notifications.NotificationsViewModel
import com.mhss.app.shifak.presentation.pharmacy.branch.ManageBranchesScreen
import com.mhss.app.shifak.presentation.pharmacy.branch.ManageBranchesViewModel
import com.mhss.app.shifak.presentation.pharmacy.network.MakeNetworkRequestScreen
import com.mhss.app.shifak.presentation.pharmacy.network.NetworkRequestsScreen
import com.mhss.app.shifak.presentation.pharmacy.network.PharmacyNetworkEvent
import com.mhss.app.shifak.presentation.pharmacy.network.PharmacyNetworkViewModel
import com.mhss.app.shifak.presentation.user.profile.faq.FAQScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.pharmacyNestedGraph(navController: NavHostController) {
    navigation<Screen.PharmacyGraph>(
        startDestination = Screen.PharmacyMainScreen
    ) {
        composable<Screen.PharmacyMainScreen> {
            PharmacyMainScreen(navController)
        }
        composable<Screen.ManageBranchesScreen> {
            val viewModel = koinViewModel<ManageBranchesViewModel>()
            val state by viewModel.uiState.collectAsState()
            val pharmacyId = it.toRoute<Screen.ManageBranchesScreen>().pharmacyId
            ManageBranchesScreen(
                state = state,
                pharmacyId = pharmacyId,
                onEvent = viewModel::onEvent
            )
        }
        composable<Screen.RequestMedicineNetworkScreen> {
            val viewModel = koinViewModel<PharmacyNetworkViewModel>()
            val state by viewModel.uiState.collectAsState()
            MakeNetworkRequestScreen(
                state = state,
                onEvent = {
                    when(it) {
                        PharmacyNetworkEvent.NavigateUp -> navController.navigateUp()
                        else -> viewModel.onEvent(it)
                    }
                }
            )
        }
        composable<Screen.NotificationsScreen> {
            val viewModel = koinViewModel<NotificationsViewModel>()
            val state by viewModel.uiState.collectAsState()
            NotificationsScreen(
                state = state,
                onEvent = viewModel::onEvent,
                onNavigateUp = navController::navigateUp
            )
        }
        composable<Screen.SmartAssistantScreen> {
            val viewModel = koinViewModel<AssistantViewModel>()
            val state by viewModel.uiState.collectAsState()
            AssistantScreen(
                state = state,
                messages = viewModel.messages,
                onEvent = viewModel::onEvent
            )
        }
        composable<Screen.FAQScreen> {
            FAQScreen(
                onNavigateUp = navController::navigateUp
            )
        }
    }
}