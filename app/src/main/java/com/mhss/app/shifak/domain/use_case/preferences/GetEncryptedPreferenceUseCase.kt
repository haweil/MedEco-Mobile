package com.mhss.app.shifak.domain.use_case.preferences

import com.mhss.app.shifak.domain.model.preferences.PrefsKey
import com.mhss.app.shifak.domain.repository.preferences.EncryptedPreferencesRepository
import org.koin.core.annotation.Single

@Single
class GetEncryptedPreferenceUseCase(
    private val repository: EncryptedPreferencesRepository
) {
    suspend operator fun <T> invoke(key: PrefsKey, defaultValue: T) = repository.getPreference(key, defaultValue)
}