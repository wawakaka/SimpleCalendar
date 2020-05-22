package io.github.wawakaka.simplecalendar.lib.data

import org.joda.time.LocalDate
import java.util.*

data class SimpleDateData(
    val id: Int = UUID.randomUUID().toString().hashCode(),
    val month: Int,
    val day: LocalDate,
    val mode: SimpleState
)