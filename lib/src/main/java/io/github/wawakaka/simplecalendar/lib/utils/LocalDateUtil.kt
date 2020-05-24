package io.github.wawakaka.simplecalendar.lib.utils

import android.util.Log
import io.github.wawakaka.simplecalendar.lib.data.SimpleDateData
import io.github.wawakaka.simplecalendar.lib.data.SimpleMode
import io.github.wawakaka.simplecalendar.lib.data.SimpleMonthData
import org.joda.time.DateTimeConstants
import org.joda.time.DateTimeZone
import org.joda.time.LocalDate
import java.util.*

internal object LocalDateUtil {

    fun days(year: Int, month: Int): List<LocalDate> {
        val result = mutableListOf<LocalDate>()
        val day = LocalDate.now(DateTimeZone.getDefault())
            .withDayOfMonth(1)
            .withMonthOfYear(month)
            .withYear(year)
        val previousMonthDateList = getLastMonthDate(day)

        result.addAll(previousMonthDateList)

        for (index in day.dayOfMonth - 1 until day.dayOfMonth().maximumValue) {
            result.add(day.plusDays(index))
        }

        val nextMonthDateList = getNextMonthDate(
            day,
            result.size
        )

        result.addAll(nextMonthDateList)

        return result
    }

    fun daysInMonth(month: Int): List<LocalDate> {
        val result = mutableListOf<LocalDate>()
        val day = LocalDate.now(DateTimeZone.getDefault())
            .withDayOfMonth(1)
            .withMonthOfYear(month)
        val previousMonthDateList = getLastMonthDate(day)

        result.addAll(previousMonthDateList)

        for (index in day.dayOfMonth - 1 until day.dayOfMonth().maximumValue) {
            result.add(day.plusDays(index))
        }

        val nextMonthDateList = getNextMonthDate(
            day,
            result.size
        )

        result.addAll(nextMonthDateList)

        return result
    }

    private fun getLastMonthDate(firstDayOfTheMonth: LocalDate): MutableList<LocalDate> {
        val firstDayOfTheWeekValue =
            LocalDate.now(DateTimeZone.forID("Asia/Jakarta")).dayOfWeek().minimumValue
        val firstDayOfTheMonthValue = firstDayOfTheMonth.dayOfWeek().get()
        return if (firstDayOfTheWeekValue == DateTimeConstants.SUNDAY) {
            when (firstDayOfTheMonthValue) {
                DateTimeConstants.SUNDAY -> mutableListOf()
                DateTimeConstants.MONDAY -> getLastMonthDateList(
                    1,
                    firstDayOfTheMonth
                )
                DateTimeConstants.TUESDAY -> getLastMonthDateList(
                    2,
                    firstDayOfTheMonth
                )
                DateTimeConstants.WEDNESDAY -> getLastMonthDateList(
                    3,
                    firstDayOfTheMonth
                )
                DateTimeConstants.THURSDAY -> getLastMonthDateList(
                    4,
                    firstDayOfTheMonth
                )
                DateTimeConstants.FRIDAY -> getLastMonthDateList(
                    5,
                    firstDayOfTheMonth
                )
                else -> getLastMonthDateList(
                    6,
                    firstDayOfTheMonth
                )
            }
        } else {
            when (firstDayOfTheMonthValue) {
                DateTimeConstants.MONDAY -> mutableListOf()
                DateTimeConstants.TUESDAY -> getLastMonthDateList(
                    1,
                    firstDayOfTheMonth
                )
                DateTimeConstants.WEDNESDAY -> getLastMonthDateList(
                    2,
                    firstDayOfTheMonth
                )
                DateTimeConstants.THURSDAY -> getLastMonthDateList(
                    3,
                    firstDayOfTheMonth
                )
                DateTimeConstants.FRIDAY -> getLastMonthDateList(
                    4,
                    firstDayOfTheMonth
                )
                DateTimeConstants.SATURDAY -> getLastMonthDateList(
                    5,
                    firstDayOfTheMonth
                )
                else -> getLastMonthDateList(
                    6,
                    firstDayOfTheMonth
                )
            }
        }
    }

    private fun getLastMonthDateList(numberOfDays: Int, date: LocalDate): MutableList<LocalDate> {
        val listOfDate = mutableListOf<LocalDate>()
        for (index in numberOfDays - 1 downTo 0) {
            val day = date
                .minusMonths(1)
                .withDayOfMonth(date.minusMonths(1).dayOfMonth().maximumValue)
            listOfDate.add(day.minusDays(index))
        }
        return listOfDate
    }

    private fun getNextMonthDate(date: LocalDate, currentListSize: Int): MutableList<LocalDate> {
        return when (currentListSize) {
            28, 35, 42 -> {
                mutableListOf()
            }
            else -> {
                getNextMonthDateList(
                    date = date,
                    numberOfDays = when {
                        currentListSize in 0..27 -> 28 - currentListSize
                        currentListSize in 29..34 -> 35 - currentListSize
                        currentListSize in 36..41 -> 42 - currentListSize
                        currentListSize > 42 -> throw Exception("something not right")
                        else -> currentListSize
                    }
                )
            }
        }
    }

    private fun getNextMonthDateList(date: LocalDate, numberOfDays: Int): MutableList<LocalDate> {
        val listOfDate = mutableListOf<LocalDate>()
        for (index in 0 until numberOfDays) {
            listOfDate.add(date.plusMonths(1).withDayOfMonth(1).plusDays(index))
        }
        return listOfDate
    }

    fun getMonth(month: Int): Int {
        return when (month) {
            1 -> DateTimeConstants.JANUARY
            2 -> DateTimeConstants.FEBRUARY
            3 -> DateTimeConstants.MARCH
            4 -> DateTimeConstants.APRIL
            5 -> DateTimeConstants.MAY
            6 -> DateTimeConstants.JUNE
            7 -> DateTimeConstants.JULY
            8 -> DateTimeConstants.AUGUST
            9 -> DateTimeConstants.SEPTEMBER
            10 -> DateTimeConstants.OCTOBER
            11 -> DateTimeConstants.NOVEMBER
            else -> DateTimeConstants.DECEMBER
        }
    }

    fun getListOfDays(year: Int, month: Int): List<LocalDate> {
        val result = mutableListOf<LocalDate>()
        val day = LocalDate.now(DateTimeZone.getDefault())
            .withDayOfMonth(1)
            .withMonthOfYear(month)
            .withYear(year)

        for (index in day.dayOfMonth - 1 until day.dayOfMonth().maximumValue) {
            result.add(day.plusDays(index))
        }

        return result
    }

    fun getCurrentYear(): Int {
        val localDate = LocalDate.now(DateTimeZone.getDefault())
        return localDate.year
    }

    fun getCurrentMonthValue(): Int {
        val localDate = LocalDate.now(DateTimeZone.getDefault())
        return localDate.monthOfYear
    }

    fun getCurrentMonth(): LocalDate.Property {
        val localDate = LocalDate.now(DateTimeZone.getDefault())
        return localDate.monthOfYear()
    }

    fun getCurrentDayOfMonth(): Int {
        val localDate = LocalDate.now(DateTimeZone.getDefault())
        return localDate.dayOfMonth
    }

    fun getCurrentDayOfWeek(): LocalDate.Property {
        val localDate = LocalDate.now(DateTimeZone.getDefault())
        return localDate.dayOfWeek()
    }

    fun getYearText(data: SimpleMonthData): String {
        return LocalDate.now(DateTimeZone.getDefault())
            .withYear(data.year)
            .toString("YYYY", Locale("in", "ID"))
    }

    fun getMonthText(data: SimpleMonthData): String {
        return LocalDate.now(DateTimeZone.getDefault())
            .withMonthOfYear(data.month)
            .toString("MMMM", Locale("in", "ID"))
    }

    fun getDayText(data: SimpleMonthData): String {
        return LocalDate.now(DateTimeZone.getDefault())
            .toString("d", Locale("in", "ID"))
    }

    fun getDayText(date: LocalDate): String {
        return date.toString("d", Locale("in", "ID"))
    }

    fun getDataFrom(localDate: LocalDate, @SimpleMode mode: Int): MutableList<SimpleMonthData> {
        val data = mutableListOf<SimpleMonthData>()
        for (month in 1..12) {
            val year = localDate.year
            val monthOfYear = localDate.withMonthOfYear(month).monthOfYear
            data.add(
                SimpleMonthData(
                    year = year,
                    month = monthOfYear,
                    dateData = calculateDateThisMonth(year, monthOfYear, mode)
                )
            )
        }
        return data
    }

    private fun calculateDateThisMonth(
        year: Int,
        month: Int,
        @SimpleMode mode: Int
    ): MutableList<SimpleDateData> {
        val dates = mutableListOf<SimpleDateData>()
        days(year, month).forEach {
            dates.add(
                SimpleDateData(
                    day = it,
                    month = getMonth(month),
                    mode = mode
                )
            )
        }
        return dates
    }

    private fun getMonthMaxLength(localDate: LocalDate): Int {
        val result = localDate.withDayOfMonth(localDate.dayOfMonth().maximumValue).dayOfMonth
        Log.e("getMonthMaxLength", "year: ${localDate.year}-${localDate.monthOfYear}-$result")
        return result
    }
}