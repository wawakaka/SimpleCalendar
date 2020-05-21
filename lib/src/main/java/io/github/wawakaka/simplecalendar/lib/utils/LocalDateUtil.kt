package io.github.wawakaka.simplecalendar.lib.utils

import android.util.Log
import io.github.wawakaka.simplecalendar.lib.data.SimpleCalendarData
import io.github.wawakaka.simplecalendar.lib.utils.SimpleConstant.MAX_NUMBER_OF_DATE
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import org.threeten.bp.Month
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.temporal.WeekFields
import java.util.*

internal object LocalDateUtil {

    fun days(year: Int, month: Int): List<LocalDate> {
        val result = mutableListOf<LocalDate>()
        val day =
            LocalDate.now(ZoneId.systemDefault())
                .withDayOfMonth(1)
                .withMonth(month)
                .withYear(year)
        val previousMonthDateList = getLastMonthDate(day)

        result.addAll(previousMonthDateList)

        for (index in day.dayOfMonth - 1 until day.lengthOfMonth()) {
            result.add(day.plusDays(index.toLong()))
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
        val day = LocalDate.now(ZoneId.systemDefault())
            .withDayOfMonth(1)
            .withMonth(month)
        val previousMonthDateList = getLastMonthDate(day)

        result.addAll(previousMonthDateList)

        for (index in day.dayOfMonth - 1 until day.lengthOfMonth()) {
            result.add(day.plusDays(index.toLong()))
        }

        val nextMonthDateList = getNextMonthDate(
            day,
            result.size
        )

        result.addAll(nextMonthDateList)

        return result
    }

    private fun getLastMonthDate(firstDayOfTheMonth: LocalDate): MutableList<LocalDate> {
        val firstDayOfTheWeekName = getStartDayOfTheWeek()
            .name
        val firstDayOfTheMonthName = firstDayOfTheMonth.dayOfWeek.name
        return if (firstDayOfTheWeekName == DayOfWeek.SUNDAY.name) {
            when (firstDayOfTheMonthName) {
                DayOfWeek.SUNDAY.name -> mutableListOf()
                DayOfWeek.MONDAY.name -> getLastMonthDateList(
                    1,
                    firstDayOfTheMonth
                )
                DayOfWeek.TUESDAY.name -> getLastMonthDateList(
                    2,
                    firstDayOfTheMonth
                )
                DayOfWeek.WEDNESDAY.name -> getLastMonthDateList(
                    3,
                    firstDayOfTheMonth
                )
                DayOfWeek.THURSDAY.name -> getLastMonthDateList(
                    4,
                    firstDayOfTheMonth
                )
                DayOfWeek.FRIDAY.name -> getLastMonthDateList(
                    5,
                    firstDayOfTheMonth
                )
                else -> getLastMonthDateList(
                    6,
                    firstDayOfTheMonth
                )
            }
        } else {
            when (firstDayOfTheMonthName) {
                DayOfWeek.MONDAY.name -> mutableListOf()
                DayOfWeek.TUESDAY.name -> getLastMonthDateList(
                    1,
                    firstDayOfTheMonth
                )
                DayOfWeek.WEDNESDAY.name -> getLastMonthDateList(
                    2,
                    firstDayOfTheMonth
                )
                DayOfWeek.THURSDAY.name -> getLastMonthDateList(
                    3,
                    firstDayOfTheMonth
                )
                DayOfWeek.FRIDAY.name -> getLastMonthDateList(
                    4,
                    firstDayOfTheMonth
                )
                DayOfWeek.SATURDAY.name -> getLastMonthDateList(
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

    private fun getStartDayOfTheWeek(): DayOfWeek {
        Log.e("Locale", Locale.getDefault().language)
        Log.e("Locale", Locale.getDefault().country)
        return WeekFields.of(Locale("in", "ID")).firstDayOfWeek
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

    private fun getNextMonthDate(date: LocalDate, currentListSize: Int): MutableList<LocalDate> {
        val numberOfDays = MAX_NUMBER_OF_DATE - currentListSize
        return getNextMonthDateList(
            date,
            numberOfDays
        )
    }

    private fun getNextMonthDateList(date: LocalDate, numberOfDays: Int): MutableList<LocalDate> {
        val listOfDate = mutableListOf<LocalDate>()
        for (index in 0 until numberOfDays) {
            listOfDate.add(date.plusMonths(1).withDayOfMonth(1).plusDays(index.toLong()))
        }
        return listOfDate
    }

    fun getMonth(month: Int): Month {
        return when (month) {
            1 -> Month.JANUARY
            2 -> Month.FEBRUARY
            3 -> Month.MARCH
            4 -> Month.APRIL
            5 -> Month.MAY
            6 -> Month.JUNE
            7 -> Month.JULY
            8 -> Month.AUGUST
            9 -> Month.SEPTEMBER
            10 -> Month.OCTOBER
            11 -> Month.NOVEMBER
            else -> Month.DECEMBER
        }
    }

    fun getListOfDays(year: Int, month: Int): List<LocalDate> {
        val result = mutableListOf<LocalDate>()
        val day = LocalDate.now(ZoneId.systemDefault())
            .withDayOfMonth(1)
            .withMonth(month)
            .withYear(year)

        for (index in day.dayOfMonth - 1 until day.lengthOfMonth()) {
            result.add(day.plusDays(index.toLong()))
        }

        return result
    }

    fun getCurrentYear(): Int {
        val localDate = LocalDate.now(ZoneId.systemDefault())
        return localDate.year
    }

    fun getCurrentMonthValue(): Int {
        val localDate = LocalDate.now(ZoneId.systemDefault())
        return localDate.monthValue
    }

    fun getCurrentMonth(): Month {
        val localDate = LocalDate.now(ZoneId.systemDefault())
        return localDate.month
    }

    fun getCurrentDayOfMonth(): Int {
        val localDate = LocalDate.now(ZoneId.systemDefault())
        return localDate.dayOfMonth
    }

    fun getCurrentDayOfWeek(): DayOfWeek {
        val localDate = LocalDate.now(ZoneId.systemDefault())
        return localDate.dayOfWeek
    }

    fun getYearText(data: SimpleCalendarData): String {
        return try {
            LocalDate
                .of(data.year, data.month, data.day)
                .format(DateTimeFormatter.ofPattern("YYYY", Locale("in", "ID")))
        } catch (e: IndexOutOfBoundsException) {
            "xx"
        }
    }

    fun getMonthText(data: SimpleCalendarData): String {
        return try {
            LocalDate
                .of(data.year, data.month, data.day)
                .format(DateTimeFormatter.ofPattern("MMMM", Locale("in", "ID")))
        } catch (e: IndexOutOfBoundsException) {
            "xx"
        }
    }

    fun getDayText(data: SimpleCalendarData): String {
        return try {
            LocalDate
                .of(data.year, data.month, data.day)
                .format(DateTimeFormatter.ofPattern("d"))
        } catch (e: IndexOutOfBoundsException) {
            "xx"
        }
    }

    fun getDayText(date: LocalDate): String {
        return try {
            date.format(DateTimeFormatter.ofPattern("d"))
        } catch (e: IndexOutOfBoundsException) {
            "xx"
        }
    }

    fun getDataFrom(localDate: LocalDate): MutableList<SimpleCalendarData> {
        val data = mutableListOf<SimpleCalendarData>()
        for (month in 1..12) {
            data.add(
                SimpleCalendarData(
                    year = localDate.year,
                    month = localDate.withMonth(month).monthValue,
                    day = getMonthMaxLength(localDate.withMonth(month))
                )
            )
        }
        return data
    }

    fun getMonthMaxLength(localDate: LocalDate): Int {
        return if (localDate.month == Month.FEBRUARY && localDate.isLeapYear.not()) {
            localDate.month.maxLength() - 1
        } else {
            localDate.month.maxLength()
        }
    }
}