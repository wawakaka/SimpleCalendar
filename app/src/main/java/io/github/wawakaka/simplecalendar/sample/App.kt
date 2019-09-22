package io.github.wawakaka.simplecalendar.sample

import android.app.Application
import io.github.wawakaka.simplecalendar.lib.SimpleCalendar

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        SimpleCalendar().init(this)
    }
}