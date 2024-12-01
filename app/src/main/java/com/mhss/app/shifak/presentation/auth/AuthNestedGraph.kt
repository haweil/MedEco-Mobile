package com.mhss.app.shifak.presentation.auth

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.mhss.app.shifak.presentation.auth.login.LoginScreen
import com.mhss.app.shifak.presentation.auth.login.LoginScreenEvent
import com.mhss.app.shifak.presentation.auth.login.LoginViewModel
import com.mhss.app.shifak.presentation.auth.signup.PharmacySignUpScreen
import com.mhss.app.shifak.presentation.auth.signup.SignUpScreenEvent
import com.mhss.app.shifak.presentation.auth.signup.SignUpViewModel
import com.mhss.app.shifak.presentation.auth.signup.UserSignUpScreen
import com.mhss.app.shifak.presentation.common.Screen
import com.mhss.app.shifak.util.UserType
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.authNestedGraph(navController: NavHostController) {
    navigation<Screen.AuthGraph>(
        startDestination = Screen.AccountTypeScreen
    ) {
        composable<Screen.AccountTypeScreen>(
            enterTransition = { fadeIn(tween(0)) },
            exitTransition = { fadeOut(tween(0)) },
            popEnterTransition = { fadeIn(tween(0)) },
            popExitTransition = { fadeOut(tween(0)) }
        ) {
            AccountTypeScreen(
                onNavigate = { userType ->
                    navController.navigate(Screen.AuthScreen(userType))
                }
            )
        }
        composable<Screen.AuthScreen> { backStackEntry ->
            val userType =
                backStackEntry.toRoute<Screen.AuthScreen>().userType
            AuthScreen(
                onLogin = {
                    navController.navigate(Screen.LoginScreen(userType))
                },
                onSignUp = {
                    navController.navigate(
                        when (userType) {
                            UserType.USER -> Screen.UserSignUpScreen
                            UserType.PHARMACY -> Screen.PharmacySignUpScreen
                        }
                    )
                }
            )
        }
        composable<Screen.LoginScreen> { backStackEntry ->
            val userType =
                backStackEntry.toRoute<Screen.AuthScreen>().userType
            val viewModel = koinViewModel<LoginViewModel>(
                parameters = { parametersOf(userType) }
            )
            LoginScreen(
                state = viewModel.state,
                onEvent = { event ->
                    when (event) {
                        is LoginScreenEvent.Login -> viewModel.onEvent(event)
                        is LoginScreenEvent.Navigate -> navController.navigate(event.screen) {
                            if (event.popUp) popUpTo(event.screen) { inclusive = true }
                        }
                        LoginScreenEvent.NavigateUp -> navController.navigateUp()
                        LoginScreenEvent.ForgotPassword -> {/* TODO */}
                    }
                }
            )
        }

        composable<Screen.UserSignUpScreen> {
            val viewModel = koinViewModel<SignUpViewModel>(
                parameters = { parametersOf(UserType.USER) }
            )

            UserSignUpScreen(
                state = viewModel.state,
                onEvent = { event ->
                    when (event) {
                        is SignUpScreenEvent.SignUp -> viewModel.onEvent(event)
                        is SignUpScreenEvent.Navigate -> navController.navigate(event.screen) {
                            if (event.popUp) popUpTo(event.screen) { inclusive = true }
                        }
                        SignUpScreenEvent.NavigateUp -> navController.navigateUp()
                    }
                }
            )
        }

        composable<Screen.PharmacySignUpScreen> {
            val viewModel = koinViewModel<SignUpViewModel>(
                parameters = { parametersOf(UserType.PHARMACY) }
            )
            PharmacySignUpScreen(
        state = viewModel.state,
        onEvent = { event ->
            when (event) {
                is SignUpScreenEvent.SignUp -> viewModel.onEvent(event)
                is SignUpScreenEvent.Navigate -> navController.navigate(event.screen) {
                    if (event.popUp) popUpTo(event.screen) { inclusive = true }
                }
                SignUpScreenEvent.NavigateUp -> navController.navigateUp()
            }
        }
            )
        }
    }
}