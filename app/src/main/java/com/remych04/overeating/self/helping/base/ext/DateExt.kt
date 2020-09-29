package com.remych04.overeating.self.helping.base.ext

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

private val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

fun Long.epochToFormattedDate(): String {
    val localDateTime = LocalDateTime.ofInstant(
        Instant.ofEpochMilli(this),
        ZoneId.systemDefault()
    )
    return localDateTime.format(formatter)
}

fun CharSequence.formattedDateToEpochMilli(): Long {
    return LocalDate.parse(this, formatter)
        .atStartOfDay(ZoneId.systemDefault())
        .toInstant()
        .toEpochMilli()
}

fun CharSequence.formattedDateToZonedDateTime(): ZonedDateTime {
    return LocalDate.parse(this, formatter)
        .atStartOfDay(ZoneId.systemDefault())
}

fun ZonedDateTime.toFormattedDate(): String {
    return this.format(formatter)
}

fun Any.getCurrentTime(): String {
    return LocalDateTime.now(ZoneId.systemDefault()).format(formatter)
}