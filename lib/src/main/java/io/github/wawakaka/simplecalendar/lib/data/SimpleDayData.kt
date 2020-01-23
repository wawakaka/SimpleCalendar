package io.github.wawakaka.simplecalendar.lib.data

import org.threeten.bp.LocalDate
import org.threeten.bp.Month
import java.util.*

internal data class SimpleDayData(
    val id: Int = UUID.randomUUID().toString().hashCode(),
    val month: Month,
    val day: LocalDate
)