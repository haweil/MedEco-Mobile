package com.mhss.app.shifak.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhss.app.shifak.domain.model.preferences.booleanPreferencesKey
import com.mhss.app.shifak.domain.model.preferences.intPreferencesKey
import com.mhss.app.shifak.domain.model.preferences.stringPreferencesKey
import com.mhss.app.shifak.domain.use_case.preferences.GetEncryptedPreferenceUseCase
import com.mhss.app.shifak.domain.use_case.preferences.GetPreferenceUseCase
import com.mhss.app.shifak.domain.use_case.preferences.SavePreferenceUseCase
import com.mhss.app.shifak.presentation.common.Screen
import com.mhss.app.shifak.util.PrefsConstants.COMPLETED_ONBOARDING
import com.mhss.app.shifak.util.PrefsConstants.TOKEN_KEY
import com.mhss.app.shifak.util.PrefsConstants.USER_TYPE
import com.mhss.app.shifak.util.UserType
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class MainViewModel(
    private val getPreference: GetPreferenceUseCase,
    private val savePreference: SavePreferenceUseCase,
    private val getEncryptedPreference: GetEncryptedPreferenceUseCase
): ViewModel() {

    var showSplashScreen = true
        private set

    private val mainScreenChannel = Channel<MainEvent>()
    val mainActivityEventFlow = mainScreenChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            val completedOnboarding = getPreference(booleanPreferencesKey(COMPLETED_ONBOARDING), false).first()
            if (completedOnboarding) {
                val token = getEncryptedPreference(stringPreferencesKey(TOKEN_KEY), "")
                if (token.isBlank()) {
                    mainScreenChannel.send(MainEvent.Navigate(Screen.AccountTypeScreen))
                } else {
                    val type = getPreference(intPreferencesKey(USER_TYPE), UserType.USER.prefsValue).first()
                    mainScreenChannel.send(
                        MainEvent.Navigate(
                            if (type == UserType.USER.prefsValue) Screen.UserMainScreen
                            else Screen.PharmacyMainScreen
                        )
                    )
                }
            }
            delay(400)
            showSplashScreen = false
        }
    }

    fun onBoardingFinished() = viewModelScope.launch {
        savePreference(booleanPreferencesKey(COMPLETED_ONBOARDING), true)
    }


}