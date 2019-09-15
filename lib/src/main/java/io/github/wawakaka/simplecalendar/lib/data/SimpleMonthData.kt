package io.github.wawakaka.simplecalendar.lib.data

import java.io.Serializable
import java.time.LocalDate
import java.util.*

data class SimpleMonthData(
    val id: Int = UUID.randomUUID().toString().hashCode(),
    val days: MutableList<LocalDate> = mutableListOf()
) : Serializable