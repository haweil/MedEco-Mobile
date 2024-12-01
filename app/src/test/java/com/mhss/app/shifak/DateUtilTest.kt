package com.mhss.app.shifak

import com.mhss.app.shifak.util.formatDate
import com.mhss.app.shifak.util.formattedForNetwork
import org.junit.Test

import org.junit.Assert.*


class DateUtilTest {
    @Test
    fun `format for network formats correctly`() {
        val dateLong = 1727053588055
        val formattedDate = dateLong.formattedForNetwork()
        assertEquals(formattedDate, "2024-09-23")
    }

    @Test
    fun `format date formats correctly`() {
        val dateLong = 1727053588055
        val formattedDate = dateLong.formatDate()
        assertEquals(formattedDate, "09/2024")
    }

    @Test
    fun `format date formats correctly with showDay`() {
        val dateLong = 1727053588055
        val formattedDate = dateLong.formatDate(showDay = true)
        assertEquals(formattedDate, "23/09/2024")
    }


}