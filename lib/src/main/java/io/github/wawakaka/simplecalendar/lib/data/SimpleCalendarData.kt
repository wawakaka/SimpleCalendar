package io.github.wawakaka.simplecalendar.lib.data

import java.util.*

internal class SimpleCalendarData(
    val id: Int = UUID.randomUUID().toString().hashCode(),
    val year: Int,
    val month: Int,
    val day: Int
)