package com.mhss.app.shifak.domain.use_case.preferences

import com.mhss.app.shifak.domain.model.preferences.PrefsKey
import com.mhss.app.shifak.domain.repository.preferences.PreferencesRepository
import org.koin.core.annotation.Single

@Single
class SavePreferenceUseCase(
    private val repository: PreferencesRepository
) {
    suspend operator fun <T> invoke(key: PrefsKey, value: T) = repository.savePreference(key, value)
}