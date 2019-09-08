package io.github.wawakaka.simplecalendar.lib

import android.icu.util.Calendar
import android.os.Build
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

internal fun daysInThisMonth(): List<Date> {
    Log.e("daysInThisMonth", System.currentTimeMillis().toString())
    val thisMonth = mutableListOf<Date>()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        val calendar = Calendar.getInstance()

        calendar.set(Calendar.DAY_OF_MONTH, 1)

        val month = calendar.get(Calendar.MONTH)

        while (month == calendar.get(Calendar.MONTH)) {
            thisMonth.add(calendar.time)
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }
        Log.e("daysInThisMonth", System.currentTimeMillis().toString())

        return thisMonth
    } else {
        val calendar = java.util.Calendar.getInstance()

        calendar.set(java.util.Calendar.DAY_OF_MONTH, 1)

        val month = calendar.get(java.util.Calendar.MONTH)

        while (month == calendar.get(java.util.Calendar.MONTH)) {
            thisMonth.add(calendar.time)
            calendar.add(java.util.Calendar.DAY_OF_MONTH, 1)
        }
        Log.e("daysInThisMonth", System.currentTimeMillis().toString())

        return thisMonth
    }
}

internal fun Date.getDays(): List<Date> {
    val thisMonth = mutableListOf<Date>()

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        val calendar = Calendar.getInstance()

        calendar.time = this
        calendar.set(Calendar.DAY_OF_MONTH, 1)

        val month = calendar.get(Calendar.MONTH)

        while (month == calendar.get(Calendar.MONTH)) {
            thisMonth.add(calendar.time)
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }
    } else {
        val calendar = java.util.Calendar.getInstance()

        calendar.time = this
        calendar.set(java.util.Calendar.DAY_OF_MONTH, 1)

        val month = calendar.get(java.util.Calendar.MONTH)

        while (month == calendar.get(java.util.Calendar.MONTH)) {
            thisMonth.add(calendar.time)
            calendar.add(java.util.Calendar.DAY_OF_MONTH, 1)
        }
    }

    return thisMonth
}

internal fun Date.transformDate(
    toFormat: String = "dd",
    locale: Locale = Locale.getDefault()
): String {
    return SimpleDateFormat(toFormat, locale).format(this)
}