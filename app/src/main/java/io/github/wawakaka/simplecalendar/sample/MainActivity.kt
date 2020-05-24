package io.github.wawakaka.simplecalendar.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.wawakaka.simplecalendar.lib.view.calendar.SimpleCalendar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(SimpleCalendar(this).apply {

        })
    }
}
