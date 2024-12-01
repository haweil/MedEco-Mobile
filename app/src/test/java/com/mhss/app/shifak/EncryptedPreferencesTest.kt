package com.mhss.app.shifak

import com.mhss.app.shifak.domain.model.preferences.intPreferencesKey
import com.mhss.app.shifak.domain.model.preferences.stringPreferencesKey
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class EncryptedPreferencesRepositoryTest {

    private val fakeRepository = FakeEncryptedPreferencesRepository()

    @Test
    fun `test saving and retrieving preference`() = runTest {
        val key = stringPreferencesKey("theme")
        val value = "dark_mode"

        fakeRepository.savePreference(key, value)

        val result = fakeRepository.getPreference(key, "light_mode")
        assertEquals("dark_mode", result)
    }

    @Test
    fun `test retrieving preference with default value`() = runTest {
        val key = intPreferencesKey("font_size")

        val result = fakeRepository.getPreference(key, 14)
        assertEquals(14, result)
    }

    @Test
    fun `test saving and retrieving multiple preferences`() = runTest {
        val themeKey = stringPreferencesKey("theme")
        val fontSizeKey = intPreferencesKey("font_size")

        fakeRepository.savePreference(themeKey, "dark_mode")
        fakeRepository.savePreference(fontSizeKey, 16)

        val theme = fakeRepository.getPreference(themeKey, "light_mode")
        val fontSize = fakeRepository.getPreference(fontSizeKey, 12)

        assertEquals("dark_mode", theme)
        assertEquals(16, fontSize)
    }

    @Test
    fun `test overwriting existing preference`() = runTest {
        val key = stringPreferencesKey("theme")

        fakeRepository.savePreference(key, "light_mode")

        fakeRepository.savePreference(key, "dark_mode")

        val result = fakeRepository.getPreference(key, "light_mode")
        assertEquals("dark_mode", result)
    }
}