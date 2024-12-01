package com.mhss.app.shifak.data.local.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.mhss.app.shifak.domain.model.preferences.PrefsKey
import com.mhss.app.shifak.domain.repository.preferences.PreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import kotlin.coroutines.CoroutineContext

@Single
class PreferenceRepositoryImpl(
    private val preferences: DataStore<Preferences>,
    @Named("ioDispatcher") private val ioDispatcher: CoroutineContext
) : PreferencesRepository {

    override suspend fun <T> savePreference(key: PrefsKey, value: T) {
        withContext(ioDispatcher) {
            preferences.edit { settings ->
                val dataStoreKey = key.toDatastoreKey<T>()
                if (settings[dataStoreKey] != value)
                    settings[dataStoreKey] = value
            }
        }
    }

    override fun <T> getPreference(key: PrefsKey, defaultValue: T): Flow<T> {
        return preferences.data.map { preferences -> preferences[key.toDatastoreKey()] ?: defaultValue }
    }
}

@Suppress("UNCHECKED_CAST")
fun <T> PrefsKey.toDatastoreKey(): Preferences.Key<T> {
    return when (this) {
        is PrefsKey.IntKey -> intPreferencesKey(name)
        is PrefsKey.BooleanKey -> booleanPreferencesKey(name)
        is PrefsKey.StringKey -> stringPreferencesKey(name)
    } as Preferences.Key<T>
}