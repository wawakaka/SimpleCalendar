package io.github.wawakaka.simplecalendar.lib

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

class SimpleCalendar {

    fun init(application: Application) {
        AndroidThreeTen.init(application)
    }
}