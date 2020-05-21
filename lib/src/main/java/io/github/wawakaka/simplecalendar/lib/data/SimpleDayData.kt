package io.github.wawakaka.simplecalendar.lib.data

import org.threeten.bp.LocalDate
import org.threeten.bp.Month
import java.util.*

data class SimpleDateData(
    val id: Int = UUID.randomUUID().toString().hashCode(),
    val month: Month,
    val day: LocalDate,
    val mode: SimpleState
)