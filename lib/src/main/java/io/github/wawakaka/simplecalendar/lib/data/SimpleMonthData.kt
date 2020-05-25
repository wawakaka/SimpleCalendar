package io.github.wawakaka.simplecalendar.lib.data

import java.util.*

data class SimpleMonthData(
    val id: Int = UUID.randomUUID().toString().hashCode(),
    val year: Int,
    val month: Int,
    val dateData: MutableList<SimpleDateData>
)
