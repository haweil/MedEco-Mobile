package com.mhss.app.shifak.presentation.main

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mhss.app.shifak.presentation.auth.authNestedGraph
import com.mhss.app.shifak.presentation.common.Screen
import com.mhss.app.shifak.presentation.onboarding.OnboardingScreen
import com.mhss.app.shifak.presentation.pharmacy.nav.pharmacyNestedGraph
import com.mhss.app.shifak.presentation.ui.theme.MedEcoTheme
import com.mhss.app.shifak.presentation.user.userNestedGraph
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                scrim = Color.TRANSPARENT,
                darkScrim = Color.BLACK
            )
        )
        installSplashScreen().apply {
            setKeepOnScreenCondition { mainViewModel.showSplashScreen }
        }
        setContent {
            MedEcoTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.consumeWindowInsets(WindowInsets.navigationBars)
                ) {
                    val navController = rememberNavController()
                    LaunchedEffect(Unit) {
                        lifecycleScope.launch {
                            repeatOnLifecycle(Lifecycle.State.STARTED) {
                                mainViewModel.mainActivityEventFlow.collect { event ->
                                    when (event) {
                                        is MainEvent.Navigate -> navController.navigate(event.screen) {
                                            popUpTo(event.screen) {
                                                inclusive = true
                                            }

                                        }
                                    }

                                }
                            }
                        }
                    }
                    NavHost(
                        navController = navController,
                        startDestination = Screen.OnboardingScreen
                    ) {
                        composable<Screen.OnboardingScreen>(
                            enterTransition = { fadeIn(tween(0)) },
                            exitTransition = { fadeOut(tween(0)) },
                            popEnterTransition = { fadeIn(tween(0)) },
                            popExitTransition = { fadeOut(tween(0)) },
                        ) {
                            OnboardingScreen(
                                onFinish = {
                                    mainViewModel.onBoardingFinished()
                                    navController.navigate(Screen.AuthGraph) {
                                        popUpTo(Screen.AuthGraph) {
                                            inclusive = true
                                        }
                                    }
                                }
                            )
                        }
                        authNestedGraph(navController)
                        userNestedGraph(navController)
                        pharmacyNestedGraph(navController)
                    }
                }
            }
        }
    }
}