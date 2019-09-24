package io.github.wawakaka.simplecalendar.lib.data

import org.threeten.bp.LocalDate
import java.util.*

data class SimpleDayData(
    val id: Int = UUID.randomUUID().toString().hashCode(),
    val day: LocalDate
)