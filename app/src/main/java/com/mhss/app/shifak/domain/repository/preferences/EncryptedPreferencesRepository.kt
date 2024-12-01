package com.mhss.app.shifak.domain.repository.preferences

import com.mhss.app.shifak.domain.model.preferences.PrefsKey

interface EncryptedPreferencesRepository {

    suspend fun <T> savePreference(key: PrefsKey, value: T)

    suspend fun <T> getPreference(key: PrefsKey, defaultValue: T): T
}