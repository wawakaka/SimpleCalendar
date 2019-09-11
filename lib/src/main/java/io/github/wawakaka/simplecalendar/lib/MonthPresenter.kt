package io.github.wawakaka.simplecalendar.lib

import android.icu.util.Calendar
import android.icu.util.ULocale
import android.os.Build
import io.github.wawakaka.simplecalendar.lib.SimpleConstant.MAX_NUMBER_OF_DATE
import java.util.*

internal object MonthPresenter {

    fun getCoulomb(position: Int): Int {
        return when {
            position % 7 == 0 -> 3
            position % 7 == 1 -> 1
            else -> 2
        }
    }

    fun getRow(position: Int): Int {
        return when {
            (position.toFloat() / 7.0f) <= 1.0f -> 1
            (position.toFloat() / 7.0f) <= 2.0f -> 2
            (position.toFloat() / 7.0f) <= 3.0f -> 3
            (position.toFloat() / 7.0f) <= 4.0f -> 4
            (position.toFloat() / 7.0f) <= 5.0f -> 5
            else -> 6
        }
    }

    fun getTopItemPosition(position: Int): Int {
        return when {
            position >= 8 -> {
                position - 8
            }
            else -> {
                0
            }
        }
    }

    fun getNextMonthDate(date: Date, currentListSize: Int): MutableList<Date> {
        val numberOfDays = MAX_NUMBER_OF_DATE - currentListSize
        return getNextMonthDateList(date, numberOfDays)
    }

    fun getLastMonthDate(date: Date): MutableList<Date> {
        return if (getFirstDayOfTheWeek() == Calendar.SUNDAY) {
            when (getFirstDayOfTheMonthPosition(date)) {
                Calendar.SUNDAY -> mutableListOf()
                Calendar.MONDAY -> getLastMonthDateList(1, date)
                Calendar.TUESDAY -> getLastMonthDateList(2, date)
                Calendar.WEDNESDAY -> getLastMonthDateList(3, date)
                Calendar.THURSDAY -> getLastMonthDateList(4, date)
                Calendar.FRIDAY -> getLastMonthDateList(5, date)
                else -> getLastMonthDateList(6, date)
            }
        } else {
            when (getFirstDayOfTheMonthPosition(date)) {
                Calendar.MONDAY -> mutableListOf()
                Calendar.TUESDAY -> getLastMonthDateList(1, date)
                Calendar.WEDNESDAY -> getLastMonthDateList(2, date)
                Calendar.THURSDAY -> getLastMonthDateList(3, date)
                Calendar.FRIDAY -> getLastMonthDateList(4, date)
                Calendar.SATURDAY -> getLastMonthDateList(5, date)
                else -> getLastMonthDateList(6, date)
            }
        }
    }

    private fun getFirstDayOfTheMonthPosition(date: Date): Any {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val calendar = Calendar.getInstance(ULocale.getDefault())
            calendar.run {
                time = date
                set(Calendar.HOUR_OF_DAY, 0)
                get(Calendar.DAY_OF_WEEK)
            }
        } else {
            val calendar = java.util.Calendar.getInstance(Locale.getDefault())
            calendar.run {
                time = date
                set(java.util.Calendar.HOUR_OF_DAY, 0)
                get(java.util.Calendar.DAY_OF_WEEK)
            }
        }
    }

    private fun getLastMonthDateList(numberOfDays: Int, date: Date): MutableList<Date> {
        val listOfDate = mutableListOf<Date>()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val calendar = Calendar.getInstance(ULocale.getDefault())
            calendar.run {
                time = date
                add(Calendar.MONTH, -1)
                set(Calendar.DAY_OF_MONTH, getActualMaximum(Calendar.DAY_OF_MONTH))
            }
            for (index in numberOfDays..1) {
                listOfDate.add(calendar.time)
                calendar.run {
                    add(Calendar.DAY_OF_MONTH, 1)
                }
            }
        } else {
            val calendar = java.util.Calendar.getInstance(Locale.getDefault())
            calendar.run {
                time = date
                add(java.util.Calendar.MONTH, -1)
                set(
                    java.util.Calendar.DAY_OF_MONTH,
                    getActualMaximum(java.util.Calendar.DAY_OF_MONTH)
                )
            }
            for (index in numberOfDays..1) {
                listOfDate.add(calendar.time)
                calendar.run {
                    add(java.util.Calendar.DAY_OF_MONTH, -1)
                }
            }
        }
        return listOfDate
    }

    private fun getNextMonthDateList(date: Date, numberOfDays: Int): MutableList<Date> {
        val listOfDate = mutableListOf<Date>()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val calendar = Calendar.getInstance(ULocale.getDefault())
            calendar.run {
                time = date
                add(Calendar.MONTH, 1)
                set(Calendar.DAY_OF_MONTH, getActualMaximum(Calendar.DAY_OF_MONTH))
            }
            for (index in 1..numberOfDays) {
                listOfDate.add(calendar.time)
                calendar.run {
                    add(Calendar.DAY_OF_MONTH, 1)
                }
            }
        } else {
            val calendar = java.util.Calendar.getInstance(Locale.getDefault())
            calendar.run {
                time = date
                add(java.util.Calendar.MONTH, 1)
                set(
                    java.util.Calendar.DAY_OF_MONTH,
                    getActualMaximum(java.util.Calendar.DAY_OF_MONTH)
                )
            }
            for (index in 1..numberOfDays) {
                listOfDate.add(calendar.time)
                calendar.run {
                    add(java.util.Calendar.DAY_OF_MONTH, 1)
                }
            }
        }
        return listOfDate
    }

    private fun getFirstDayOfTheWeek(): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Calendar.getInstance(ULocale.getDefault()).firstDayOfWeek
        } else {
            java.util.Calendar.getInstance(Locale.getDefault()).firstDayOfWeek
        }
    }

    private fun getLastDayOfTheMonthPosition(date: Date): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val calendar = Calendar.getInstance(ULocale.getDefault())
            calendar.run {
                time = date
                set(Calendar.HOUR_OF_DAY, 0)
                add(Calendar.MONTH, 1)
                get(Calendar.DAY_OF_WEEK)
            }
        } else {
            val calendar = java.util.Calendar.getInstance(Locale.getDefault())
            calendar.run {
                time = date
                set(java.util.Calendar.HOUR_OF_DAY, 0)
                add(java.util.Calendar.MONTH, 1)
                get(java.util.Calendar.DAY_OF_WEEK)
            }
        }
    }
}