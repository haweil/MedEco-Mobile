package com.mhss.app.shifak.util

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun Long.formattedForNetwork(): String {
    val localDateTime = Instant.fromEpochMilliseconds(this).toLocalDateTime(
        TimeZone.currentSystemDefault()
    )

    return DateTimeFormatter
        .ofPattern("yyyy-MM-dd", Locale.ENGLISH)
        .format(localDateTime.toJavaLocalDateTime())
}

fun Long.formatDate(showDay: Boolean = false): String {
    val localDateTime = Instant.fromEpochMilliseconds(this).toLocalDateTime(
        TimeZone.currentSystemDefault()
    )
    val pattern = if (showDay) "dd/MM/yyyy" else "MM/yyyy"
    return DateTimeFormatter
        .ofPattern(pattern, Locale.getDefault())
        .format(localDateTime.toJavaLocalDateTime())
}