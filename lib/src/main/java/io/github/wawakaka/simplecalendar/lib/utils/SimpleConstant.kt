package io.github.wawakaka.simplecalendar.lib.utils

internal object SimpleConstant {

    const val MAX_NUMBER_OF_DATE = 42
    const val NUMBER_OF_DAYS_IN_ONE_WEEK = 7
    //    why 21?. because the number of list always 42 and item in the middle of the list
//    always represent actual date in current month. not like first 7 or last 14 item
//    that might be belong to other month
    const val MAGIC_INDEX = 21
}