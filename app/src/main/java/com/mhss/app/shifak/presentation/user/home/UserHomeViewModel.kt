package com.mhss.app.shifak.presentation.user.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhss.app.shifak.domain.model.preferences.stringPreferencesKey
import com.mhss.app.shifak.domain.use_case.preferences.GetPreferenceUseCase
import com.mhss.app.shifak.util.PrefsConstants.USER_NAME_KEY
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class UserHomeViewModel(
    private val getPreference: GetPreferenceUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserHomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val name = getPreference(stringPreferencesKey(USER_NAME_KEY), "").first()
            _uiState.update {
                it.copy(
                    userName = name
                )
            }

        }
    }
}