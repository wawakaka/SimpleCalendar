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
        val day = LocalDate.now(DateTimeZone.forID("Asia/Jakarta"))
            .withDayOfMonth(1)
            .withMonthOfYear(month)
            .withYear(year)
//        val previousMonthDateList = getLastMonthDate(day)

//        result.addAll(previousMonthDateList)

        for (index in day.dayOfMonth - 1 until day.dayOfMonth().maximumValue) {
            result.add(day.plusDays(index))
        }

//        val nextMonthDateList = getNextMonthDate(
//            day,
//            result.size
//        )

//        result.addAll(nextMonthDateList)

        return result
    }

    fun daysInMonth(month: Int): List<LocalDate> {
        val result = mutableListOf<LocalDate>()
        val day = LocalDate.now(DateTimeZone.forID("Asia/Jakarta"))
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
        val day = LocalDate.now(DateTimeZone.forID("Asia/Jakarta"))
            .withDayOfMonth(1)
            .withMonthOfYear(month)
            .withYear(year)

        for (index in day.dayOfMonth - 1 until day.dayOfMonth().maximumValue) {
            result.add(day.plusDays(index))
        }

        return result
    }

    fun getCurrentYear(): Int {
        val localDate = LocalDate.now(DateTimeZone.forID("Asia/Jakarta"))
        return localDate.year
    }

    fun getCurrentMonthValue(): Int {
        val localDate = LocalDate.now(DateTimeZone.forID("Asia/Jakarta"))
        return localDate.monthOfYear
    }

    fun getCurrentMonth(): LocalDate.Property {
        val localDate = LocalDate.now(DateTimeZone.forID("Asia/Jakarta"))
        return localDate.monthOfYear()
    }

    fun getCurrentDayOfMonth(): Int {
        val localDate = LocalDate.now(DateTimeZone.forID("Asia/Jakarta"))
        return localDate.dayOfMonth
    }

    fun getCurrentDayOfWeek(): LocalDate.Property {
        val localDate = LocalDate.now(DateTimeZone.forID("Asia/Jakarta"))
        return localDate.dayOfWeek()
    }

    fun getYearText(data: SimpleMonthData): String {
        return LocalDate.now(DateTimeZone.forID("Asia/Jakarta"))
            .withYear(data.year)
            .toString("YYYY", Locale("in", "ID"))
    }

    fun getMonthText(data: SimpleMonthData): String {
        return LocalDate.now(DateTimeZone.forID("Asia/Jakarta"))
            .withMonthOfYear(data.month)
            .toString("MMMM", Locale("in", "ID"))
    }

    fun getDayText(data: SimpleMonthData): String {
        return LocalDate.now(DateTimeZone.forID("Asia/Jakarta"))
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

    fun getListOfDayInWeekOfMonth(
        week: Int,
        listOfDaysInAMonth: MutableList<LocalDate>
    ): MutableList<LocalDate> {
        val firstDayOfTheWeekValue = LocalDate.now(DateTimeZone.forID("Asia/Jakarta"))
            .dayOfWeek()
            .minimumValue
        val firstDayOfTheMonthValue = listOfDaysInAMonth[0].dayOfWeek().get()
        val firstWeek = mutableListOf<LocalDate>()
        val secondWeek = mutableListOf<LocalDate>()
        val thirdWeek = mutableListOf<LocalDate>()
        val fourthWeek = mutableListOf<LocalDate>()
        val fifthWeek = mutableListOf<LocalDate>()
        val sixthWeek = mutableListOf<LocalDate>()
        if (firstDayOfTheWeekValue == DateTimeConstants.SUNDAY) {
            when (firstDayOfTheMonthValue) {
                DateTimeConstants.SUNDAY -> {
                    firstWeek.addAll(listOfDaysInAMonth.subList(0, 7))
                    secondWeek.addAll(listOfDaysInAMonth.subList(7, 14))
                    thirdWeek.addAll(listOfDaysInAMonth.subList(14, 21))
                    fourthWeek.addAll(listOfDaysInAMonth.subList(21, 28))
                    fifthWeek.addAll(listOfDaysInAMonth.subList(28, listOfDaysInAMonth.size))
                }
                DateTimeConstants.MONDAY -> {
                    firstWeek.addAll(listOfDaysInAMonth.subList(0, 6))
                    secondWeek.addAll(listOfDaysInAMonth.subList(6, 13))
                    thirdWeek.addAll(listOfDaysInAMonth.subList(13, 20))
                    fourthWeek.addAll(listOfDaysInAMonth.subList(20, 27))
                    fifthWeek.addAll(listOfDaysInAMonth.subList(27, listOfDaysInAMonth.size))
                }
                DateTimeConstants.TUESDAY -> {
                    firstWeek.addAll(listOfDaysInAMonth.subList(0, 5))
                    secondWeek.addAll(listOfDaysInAMonth.subList(5, 12))
                    thirdWeek.addAll(listOfDaysInAMonth.subList(12, 19))
                    fourthWeek.addAll(listOfDaysInAMonth.subList(19, 26))
                    fifthWeek.addAll(listOfDaysInAMonth.subList(26, listOfDaysInAMonth.size))
                }
                DateTimeConstants.WEDNESDAY -> {
                    firstWeek.addAll(listOfDaysInAMonth.subList(0, 4))
                    secondWeek.addAll(listOfDaysInAMonth.subList(4, 11))
                    thirdWeek.addAll(listOfDaysInAMonth.subList(11, 18))
                    fourthWeek.addAll(listOfDaysInAMonth.subList(18, 25))
                    fifthWeek.addAll(listOfDaysInAMonth.subList(25, listOfDaysInAMonth.size))
                }
                DateTimeConstants.THURSDAY -> {
                    firstWeek.addAll(listOfDaysInAMonth.subList(0, 3))
                    secondWeek.addAll(listOfDaysInAMonth.subList(3, 10))
                    thirdWeek.addAll(listOfDaysInAMonth.subList(10, 17))
                    fourthWeek.addAll(listOfDaysInAMonth.subList(17, 24))
                    fifthWeek.addAll(listOfDaysInAMonth.subList(24, listOfDaysInAMonth.size))
                }
                DateTimeConstants.FRIDAY -> {
                    firstWeek.addAll(listOfDaysInAMonth.subList(0, 2))
                    secondWeek.addAll(listOfDaysInAMonth.subList(2, 9))
                    thirdWeek.addAll(listOfDaysInAMonth.subList(9, 16))
                    fourthWeek.addAll(listOfDaysInAMonth.subList(16, 23))
                    fifthWeek.addAll(listOfDaysInAMonth.subList(23, 30))
                    sixthWeek.addAll(listOfDaysInAMonth.subList(30, listOfDaysInAMonth.size))
                }
                DateTimeConstants.SATURDAY -> {
                    firstWeek.addAll(listOfDaysInAMonth.subList(0, 1))
                    secondWeek.addAll(listOfDaysInAMonth.subList(1, 8))
                    thirdWeek.addAll(listOfDaysInAMonth.subList(8, 15))
                    fourthWeek.addAll(listOfDaysInAMonth.subList(15, 22))
                    fifthWeek.addAll(listOfDaysInAMonth.subList(22, 29))
                    sixthWeek.addAll(listOfDaysInAMonth.subList(29, listOfDaysInAMonth.size))
                }
            }
        } else {
            when (firstDayOfTheMonthValue) {
                DateTimeConstants.MONDAY -> {
                    firstWeek.addAll(listOfDaysInAMonth.subList(0, 7))
                    secondWeek.addAll(listOfDaysInAMonth.subList(7, 14))
                    thirdWeek.addAll(listOfDaysInAMonth.subList(14, 21))
                    fourthWeek.addAll(listOfDaysInAMonth.subList(21, 28))
                    fifthWeek.addAll(listOfDaysInAMonth.subList(28, listOfDaysInAMonth.size))
                }
                DateTimeConstants.TUESDAY -> {
                    firstWeek.addAll(listOfDaysInAMonth.subList(0, 6))
                    secondWeek.addAll(listOfDaysInAMonth.subList(6, 13))
                    thirdWeek.addAll(listOfDaysInAMonth.subList(13, 20))
                    fourthWeek.addAll(listOfDaysInAMonth.subList(20, 27))
                    fifthWeek.addAll(listOfDaysInAMonth.subList(27, listOfDaysInAMonth.size))
                }
                DateTimeConstants.WEDNESDAY -> {
                    firstWeek.addAll(listOfDaysInAMonth.subList(0, 5))
                    secondWeek.addAll(listOfDaysInAMonth.subList(5, 12))
                    thirdWeek.addAll(listOfDaysInAMonth.subList(12, 19))
                    fourthWeek.addAll(listOfDaysInAMonth.subList(19, 26))
                    fifthWeek.addAll(listOfDaysInAMonth.subList(26, listOfDaysInAMonth.size))
                }
                DateTimeConstants.THURSDAY -> {
                    firstWeek.addAll(listOfDaysInAMonth.subList(0, 4))
                    secondWeek.addAll(listOfDaysInAMonth.subList(4, 11))
                    thirdWeek.addAll(listOfDaysInAMonth.subList(11, 18))
                    fourthWeek.addAll(listOfDaysInAMonth.subList(18, 25))
                    fifthWeek.addAll(listOfDaysInAMonth.subList(25, listOfDaysInAMonth.size))
                }
                DateTimeConstants.FRIDAY -> {
                    firstWeek.addAll(listOfDaysInAMonth.subList(0, 3))
                    secondWeek.addAll(listOfDaysInAMonth.subList(3, 10))
                    thirdWeek.addAll(listOfDaysInAMonth.subList(10, 17))
                    fourthWeek.addAll(listOfDaysInAMonth.subList(17, 24))
                    fifthWeek.addAll(listOfDaysInAMonth.subList(24, listOfDaysInAMonth.size))
                }
                DateTimeConstants.SATURDAY -> {
                    firstWeek.addAll(listOfDaysInAMonth.subList(0, 2))
                    secondWeek.addAll(listOfDaysInAMonth.subList(2, 9))
                    thirdWeek.addAll(listOfDaysInAMonth.subList(9, 16))
                    fourthWeek.addAll(listOfDaysInAMonth.subList(16, 23))
                    fifthWeek.addAll(listOfDaysInAMonth.subList(23, 30))
                    sixthWeek.addAll(listOfDaysInAMonth.subList(30, listOfDaysInAMonth.size))
                }
                DateTimeConstants.SUNDAY -> {
                    firstWeek.addAll(listOfDaysInAMonth.subList(0, 1))
                    secondWeek.addAll(listOfDaysInAMonth.subList(1, 8))
                    thirdWeek.addAll(listOfDaysInAMonth.subList(8, 15))
                    fourthWeek.addAll(listOfDaysInAMonth.subList(15, 22))
                    fifthWeek.addAll(listOfDaysInAMonth.subList(22, 29))
                    sixthWeek.addAll(listOfDaysInAMonth.subList(29, listOfDaysInAMonth.size))
                }
            }
        }
        return when (week) {
            1 -> firstWeek
            2 -> secondWeek
            3 -> thirdWeek
            4 -> fourthWeek
            5 -> fifthWeek
            6 -> sixthWeek
            else -> throw Exception("invalid week value")
        }
    }

    fun getListOfDataInWeekOfMonth(
        week: Int,
        listOfDaysInAMonth: MutableList<SimpleDateData>
    ): MutableList<SimpleDateData> {
        val firstDayOfTheWeekValue = LocalDate.now(DateTimeZone.forID("Asia/Jakarta"))
            .dayOfWeek()
            .minimumValue
        val firstDayOfTheMonthValue = listOfDaysInAMonth[0].day.dayOfWeek().get()
        val firstWeek = mutableListOf<SimpleDateData>()
        val secondWeek = mutableListOf<SimpleDateData>()
        val thirdWeek = mutableListOf<SimpleDateData>()
        val fourthWeek = mutableListOf<SimpleDateData>()
        val fifthWeek = mutableListOf<SimpleDateData>()
        val sixthWeek = mutableListOf<SimpleDateData>()
        if (firstDayOfTheWeekValue == DateTimeConstants.SUNDAY) {
            when (firstDayOfTheMonthValue) {
                DateTimeConstants.SUNDAY -> {
                    firstWeek.addAll(listOfDaysInAMonth.subList(0, 7))
                    secondWeek.addAll(listOfDaysInAMonth.subList(7, 14))
                    thirdWeek.addAll(listOfDaysInAMonth.subList(14, 21))
                    fourthWeek.addAll(listOfDaysInAMonth.subList(21, 28))
                    fifthWeek.addAll(listOfDaysInAMonth.subList(28, listOfDaysInAMonth.size))
                }
                DateTimeConstants.MONDAY -> {
                    firstWeek.addAll(listOfDaysInAMonth.subList(0, 6))
                    secondWeek.addAll(listOfDaysInAMonth.subList(6, 13))
                    thirdWeek.addAll(listOfDaysInAMonth.subList(13, 20))
                    fourthWeek.addAll(listOfDaysInAMonth.subList(20, 27))
                    fifthWeek.addAll(listOfDaysInAMonth.subList(27, listOfDaysInAMonth.size))
                }
                DateTimeConstants.TUESDAY -> {
                    firstWeek.addAll(listOfDaysInAMonth.subList(0, 5))
                    secondWeek.addAll(listOfDaysInAMonth.subList(5, 12))
                    thirdWeek.addAll(listOfDaysInAMonth.subList(12, 19))
                    fourthWeek.addAll(listOfDaysInAMonth.subList(19, 26))
                    fifthWeek.addAll(listOfDaysInAMonth.subList(26, listOfDaysInAMonth.size))
                }
                DateTimeConstants.WEDNESDAY -> {
                    firstWeek.addAll(listOfDaysInAMonth.subList(0, 4))
                    secondWeek.addAll(listOfDaysInAMonth.subList(4, 11))
                    thirdWeek.addAll(listOfDaysInAMonth.subList(11, 18))
                    fourthWeek.addAll(listOfDaysInAMonth.subList(18, 25))
                    fifthWeek.addAll(listOfDaysInAMonth.subList(25, listOfDaysInAMonth.size))
                }
                DateTimeConstants.THURSDAY -> {
                    firstWeek.addAll(listOfDaysInAMonth.subList(0, 3))
                    secondWeek.addAll(listOfDaysInAMonth.subList(3, 10))
                    thirdWeek.addAll(listOfDaysInAMonth.subList(10, 17))
                    fourthWeek.addAll(listOfDaysInAMonth.subList(17, 24))
                    fifthWeek.addAll(listOfDaysInAMonth.subList(24, listOfDaysInAMonth.size))
                }
                DateTimeConstants.FRIDAY -> {
                    firstWeek.addAll(listOfDaysInAMonth.subList(0, 2))
                    secondWeek.addAll(listOfDaysInAMonth.subList(2, 9))
                    thirdWeek.addAll(listOfDaysInAMonth.subList(9, 16))
                    fourthWeek.addAll(listOfDaysInAMonth.subList(16, 23))
                    if (listOfDaysInAMonth.size <= 30) {
                        fifthWeek.addAll(listOfDaysInAMonth.subList(23, listOfDaysInAMonth.size))
                    } else {
                        fifthWeek.addAll(listOfDaysInAMonth.subList(23, 30))
                        sixthWeek.addAll(listOfDaysInAMonth.subList(30, listOfDaysInAMonth.size))
                    }
                }
                DateTimeConstants.SATURDAY -> {
                    firstWeek.addAll(listOfDaysInAMonth.subList(0, 1))
                    secondWeek.addAll(listOfDaysInAMonth.subList(1, 8))
                    thirdWeek.addAll(listOfDaysInAMonth.subList(8, 15))
                    fourthWeek.addAll(listOfDaysInAMonth.subList(15, 22))
                    if (listOfDaysInAMonth.size <= 29) {
                        fifthWeek.addAll(listOfDaysInAMonth.subList(22, listOfDaysInAMonth.size))
                    } else {
                        fifthWeek.addAll(listOfDaysInAMonth.subList(22, 29))
                        sixthWeek.addAll(listOfDaysInAMonth.subList(29, listOfDaysInAMonth.size))
                    }
                }
            }
        } else {
            when (firstDayOfTheMonthValue) {
                DateTimeConstants.MONDAY -> {
                    firstWeek.addAll(listOfDaysInAMonth.subList(0, 7))
                    secondWeek.addAll(listOfDaysInAMonth.subList(7, 14))
                    thirdWeek.addAll(listOfDaysInAMonth.subList(14, 21))
                    fourthWeek.addAll(listOfDaysInAMonth.subList(21, 28))
                    fifthWeek.addAll(listOfDaysInAMonth.subList(28, listOfDaysInAMonth.size))
                }
                DateTimeConstants.TUESDAY -> {
                    firstWeek.addAll(listOfDaysInAMonth.subList(0, 6))
                    secondWeek.addAll(listOfDaysInAMonth.subList(6, 13))
                    thirdWeek.addAll(listOfDaysInAMonth.subList(13, 20))
                    fourthWeek.addAll(listOfDaysInAMonth.subList(20, 27))
                    fifthWeek.addAll(listOfDaysInAMonth.subList(27, listOfDaysInAMonth.size))
                }
                DateTimeConstants.WEDNESDAY -> {
                    firstWeek.addAll(listOfDaysInAMonth.subList(0, 5))
                    secondWeek.addAll(listOfDaysInAMonth.subList(5, 12))
                    thirdWeek.addAll(listOfDaysInAMonth.subList(12, 19))
                    fourthWeek.addAll(listOfDaysInAMonth.subList(19, 26))
                    fifthWeek.addAll(listOfDaysInAMonth.subList(26, listOfDaysInAMonth.size))
                }
                DateTimeConstants.THURSDAY -> {
                    firstWeek.addAll(listOfDaysInAMonth.subList(0, 4))
                    secondWeek.addAll(listOfDaysInAMonth.subList(4, 11))
                    thirdWeek.addAll(listOfDaysInAMonth.subList(11, 18))
                    fourthWeek.addAll(listOfDaysInAMonth.subList(18, 25))
                    fifthWeek.addAll(listOfDaysInAMonth.subList(25, listOfDaysInAMonth.size))
                }
                DateTimeConstants.FRIDAY -> {
                    firstWeek.addAll(listOfDaysInAMonth.subList(0, 3))
                    secondWeek.addAll(listOfDaysInAMonth.subList(3, 10))
                    thirdWeek.addAll(listOfDaysInAMonth.subList(10, 17))
                    fourthWeek.addAll(listOfDaysInAMonth.subList(17, 24))
                    fifthWeek.addAll(listOfDaysInAMonth.subList(24, listOfDaysInAMonth.size))
                }
                DateTimeConstants.SATURDAY -> {
                    firstWeek.addAll(listOfDaysInAMonth.subList(0, 2))
                    secondWeek.addAll(listOfDaysInAMonth.subList(2, 9))
                    thirdWeek.addAll(listOfDaysInAMonth.subList(9, 16))
                    fourthWeek.addAll(listOfDaysInAMonth.subList(16, 23))
                    if (listOfDaysInAMonth.size <= 30) {
                        fifthWeek.addAll(listOfDaysInAMonth.subList(23, listOfDaysInAMonth.size))
                    } else {
                        fifthWeek.addAll(listOfDaysInAMonth.subList(23, 30))
                        sixthWeek.addAll(listOfDaysInAMonth.subList(30, listOfDaysInAMonth.size))
                    }
                }
                DateTimeConstants.SUNDAY -> {
                    firstWeek.addAll(listOfDaysInAMonth.subList(0, 1))
                    secondWeek.addAll(listOfDaysInAMonth.subList(1, 8))
                    thirdWeek.addAll(listOfDaysInAMonth.subList(8, 15))
                    fourthWeek.addAll(listOfDaysInAMonth.subList(15, 22))
                    if (listOfDaysInAMonth.size <= 29) {
                        fifthWeek.addAll(listOfDaysInAMonth.subList(22, listOfDaysInAMonth.size))
                    } else {
                        fifthWeek.addAll(listOfDaysInAMonth.subList(22, 29))
                        sixthWeek.addAll(listOfDaysInAMonth.subList(29, listOfDaysInAMonth.size))
                    }
                }
            }
        }
        return when (week) {
            1 -> firstWeek
            2 -> secondWeek
            3 -> thirdWeek
            4 -> fourthWeek
            5 -> fifthWeek
            6 -> sixthWeek
            else -> throw Exception("invalid week value")
        }
    }

    fun countNumberOfWeekInAMonth(firstDayOfTheMonth: LocalDate): Int {
        val firstDayOfTheWeekValue = LocalDate.now(DateTimeZone.forID("Asia/Jakarta"))
            .dayOfWeek()
            .minimumValue
        val firstDayOfTheMonthValue = firstDayOfTheMonth.dayOfWeek().get()
        return if (firstDayOfTheWeekValue == DateTimeConstants.SUNDAY) {
            when (firstDayOfTheMonthValue) {
                DateTimeConstants.FRIDAY ->
                    if (firstDayOfTheMonth.monthOfYear().maximumValue <= 30) 5
                    else 6
                DateTimeConstants.SATURDAY ->
                    if (firstDayOfTheMonth.monthOfYear().maximumValue <= 29) 5
                    else 6
                else -> 5
            }
        } else {
            when (firstDayOfTheMonthValue) {
                DateTimeConstants.FRIDAY ->
                    if (firstDayOfTheMonth.monthOfYear().maximumValue <= 30) 5
                    else 6
                DateTimeConstants.SATURDAY ->
                    if (firstDayOfTheMonth.monthOfYear().maximumValue <= 29) 5
                    else 6
                else -> 5
            }
        }
    }
}