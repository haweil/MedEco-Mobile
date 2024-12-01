package com.mhss.app.shifak.presentation.auth.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhss.app.shifak.domain.model.preferences.intPreferencesKey
import com.mhss.app.shifak.domain.model.preferences.stringPreferencesKey
import com.mhss.app.shifak.domain.use_case.auth.SignUpUseCase
import com.mhss.app.shifak.domain.use_case.preferences.SaveEncryptedPreferenceUseCase
import com.mhss.app.shifak.domain.use_case.preferences.SavePreferenceUseCase
import com.mhss.app.shifak.util.PrefsConstants.TOKEN_KEY
import com.mhss.app.shifak.util.PrefsConstants.USER_ID
import com.mhss.app.shifak.util.PrefsConstants.USER_IMAGE_KEY
import com.mhss.app.shifak.util.PrefsConstants.USER_NAME_KEY
import com.mhss.app.shifak.util.PrefsConstants.USER_TYPE
import com.mhss.app.shifak.util.UserType
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SignUpViewModel(
    private val signUp: SignUpUseCase,
    private val saveEncryptedPreference: SaveEncryptedPreferenceUseCase,
    private val savePreference: SavePreferenceUseCase,

    userType: UserType
) : ViewModel() {

    var state by mutableStateOf(SignUpUiState(userType = userType))
        private set

    fun onEvent(event: SignUpScreenEvent) {
        when (event) {
            is SignUpScreenEvent.SignUp -> viewModelScope.launch {
                state = state.copy(loading = true, error = null)
                try {
                    val response = signUp(event.signUpData)
                    saveEncryptedPreference(stringPreferencesKey(TOKEN_KEY), response.token)
                    savePreference(stringPreferencesKey(USER_NAME_KEY), event.signUpData.fullName)
                    savePreference(intPreferencesKey(USER_TYPE), event.signUpData.type.prefsValue)
                    savePreference(intPreferencesKey(USER_ID), response.user.id)
                    response.user.avatar?.let {
                        savePreference(stringPreferencesKey(USER_IMAGE_KEY), it)
                    }
                    state = state.copy(loading = false, done = true)
                } catch (e: Exception) {
                    e.printStackTrace()
                    state = state.copy(loading = false, error = e.localizedMessage)
                }
            }
            else -> Unit
        }
    }

}