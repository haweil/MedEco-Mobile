package com.mhss.app.shifak.data.local.preferences

import android.content.SharedPreferences
import com.mhss.app.shifak.domain.model.preferences.PrefsKey
import com.mhss.app.shifak.domain.repository.preferences.EncryptedPreferencesRepository
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import kotlin.coroutines.CoroutineContext

@Single
class EncryptedPreferencesRepositoryImpl(
    private val prefs: SharedPreferences,
    @Named("ioDispatcher") val ioDispatcher: CoroutineContext
): EncryptedPreferencesRepository {
    override suspend fun <T> savePreference(key: PrefsKey, value: T) {
        withContext(ioDispatcher) {
            prefs.edit().apply {
                putValue(key, value)
                apply()
            }
        }
    }

    override suspend fun <T> getPreference(key: PrefsKey, defaultValue: T): T {
        return withContext(ioDispatcher) {
            prefs.getValue(key, defaultValue)
        }
    }
}

private fun <T> SharedPreferences.Editor.putValue(key: PrefsKey, value: T) {
    when (key) {
        is PrefsKey.IntKey -> putInt(key.name, value as Int)
        is PrefsKey.BooleanKey -> putBoolean(key.name, value as Boolean)
        is PrefsKey.StringKey -> putString(key.name, value as String)
    }
}

@Suppress("UNCHECKED_CAST")
private fun <T> SharedPreferences.getValue(key: PrefsKey, defaultValue: T): T {
    return when (key) {
        is PrefsKey.IntKey -> getInt(key.name, defaultValue as Int) as T
        is PrefsKey.BooleanKey -> getBoolean(key.name, defaultValue as Boolean) as T
        is PrefsKey.StringKey -> getString(key.name, defaultValue as String) as T
    }
}