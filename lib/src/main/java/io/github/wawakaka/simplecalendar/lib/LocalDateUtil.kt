package io.github.wawakaka.simplecalendar.lib

import java.time.LocalDate
import java.time.ZoneId

internal object LocalDateUtil {

    fun daysInThisMonth(): List<LocalDate> {
        val result = mutableListOf<LocalDate>()
        val day = LocalDate.now(ZoneId.systemDefault()).withDayOfMonth(1)
        for (index in day.dayOfMonth - 1 until day.lengthOfMonth()) {
            result.add(day.plusDays(index.toLong()))
        }
        return result
    }

    fun daysInMonth(month: Int): List<LocalDate> {
        val result = mutableListOf<LocalDate>()
        val day = LocalDate.now(ZoneId.systemDefault()).withDayOfMonth(1).withMonth(month)
        for (index in day.dayOfMonth - 1 until day.lengthOfMonth()) {
            result.add(day.plusDays(index.toLong()))
        }
        return result
    }
}