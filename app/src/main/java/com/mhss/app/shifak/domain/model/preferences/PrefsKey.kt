package com.mhss.app.shifak.domain.model.preferences

sealed class PrefsKey(val name: String) {
    class IntKey(name: String): PrefsKey(name)
    class BooleanKey(name: String): PrefsKey(name)
    class StringKey(name: String): PrefsKey(name)
}
fun intPreferencesKey(name: String) = PrefsKey.IntKey(name)
fun booleanPreferencesKey(name: String) = PrefsKey.BooleanKey(name)
fun stringPreferencesKey(name: String) = PrefsKey.StringKey(name)