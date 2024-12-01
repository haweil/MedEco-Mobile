package com.mhss.app.shifak

import com.mhss.app.shifak.domain.model.preferences.PrefsKey
import com.mhss.app.shifak.domain.repository.preferences.EncryptedPreferencesRepository

class FakeEncryptedPreferencesRepository : EncryptedPreferencesRepository {

    private val preferencesMap = mutableMapOf<String, Any?>()

    override suspend fun <T> savePreference(key: PrefsKey, value: T) {
        preferencesMap[key.name] = value
    }

    @Suppress("UNCHECKED_CAST")
    override suspend fun <T> getPreference(key: PrefsKey, defaultValue: T): T {
        return preferencesMap[key.name] as? T ?: defaultValue
    }
}