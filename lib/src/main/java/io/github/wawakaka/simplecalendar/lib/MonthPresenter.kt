package io.github.wawakaka.simplecalendar.lib

import io.github.wawakaka.simplecalendar.lib.SimpleConstant.MAX_NUMBER_OF_DATE
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.WeekFields
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

    fun getNextMonthDate(date: LocalDate, currentListSize: Int): MutableList<LocalDate> {
        val numberOfDays = MAX_NUMBER_OF_DATE - currentListSize
        return getNextMonthDateList(date, numberOfDays)
    }

    fun getPreviousMonthDate(date: LocalDate): MutableList<LocalDate> {
        return if (getFirstDayOfTheWeek() == DayOfWeek.SUNDAY.ordinal) {
            when (date.dayOfWeek.ordinal) {
                DayOfWeek.SUNDAY.ordinal -> mutableListOf()
                DayOfWeek.MONDAY.ordinal -> getLastMonthDateList(1, date)
                DayOfWeek.TUESDAY.ordinal -> getLastMonthDateList(2, date)
                DayOfWeek.WEDNESDAY.ordinal -> getLastMonthDateList(3, date)
                DayOfWeek.THURSDAY.ordinal -> getLastMonthDateList(4, date)
                DayOfWeek.FRIDAY.ordinal -> getLastMonthDateList(5, date)
                else -> getLastMonthDateList(6, date)
            }
        } else {
            when (date.dayOfWeek.ordinal) {
                DayOfWeek.MONDAY.ordinal -> mutableListOf()
                DayOfWeek.TUESDAY.ordinal -> getLastMonthDateList(1, date)
                DayOfWeek.WEDNESDAY.ordinal -> getLastMonthDateList(2, date)
                DayOfWeek.THURSDAY.ordinal -> getLastMonthDateList(3, date)
                DayOfWeek.FRIDAY.ordinal -> getLastMonthDateList(4, date)
                DayOfWeek.SATURDAY.ordinal -> getLastMonthDateList(5, date)
                else -> getLastMonthDateList(6, date)
            }
        }
    }

    private fun getLastMonthDateList(numberOfDays: Int, date: LocalDate): MutableList<LocalDate> {
        val listOfDate = mutableListOf<LocalDate>()
        for (index in numberOfDays downTo 1) {
            val day = date
                .minusMonths(1)
                .withDayOfMonth(date.minusMonths(1).lengthOfMonth())
            if (index == numberOfDays) listOfDate.add(day)
            else listOfDate.add(day.minusDays(index.toLong()))
        }
        return listOfDate
    }

    private fun getNextMonthDateList(date: LocalDate, numberOfDays: Int): MutableList<LocalDate> {
        val listOfDate = mutableListOf<LocalDate>()
        for (index in 0 until numberOfDays) {
            listOfDate.add(date.plusMonths(1).withDayOfMonth(1).plusDays(index.toLong()))
        }
        return listOfDate
    }

    private fun getFirstDayOfTheWeek(): Int {
        val now = LocalDate.now().withDayOfMonth(1)
        val fieldISO = WeekFields.of(Locale.getDefault()).dayOfWeek()
        return now.with(fieldISO, 1).dayOfWeek.ordinal
    }
}