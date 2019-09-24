package io.github.wawakaka.simplecalendar.lib.data

import org.threeten.bp.LocalDate
import org.threeten.bp.Month
import java.util.*

data class SimpleMonthData(
    val id: Int = UUID.randomUUID().toString().hashCode(),
    val year: Int,
    val month: Month,
    val days: MutableList<LocalDate> = mutableListOf()
)