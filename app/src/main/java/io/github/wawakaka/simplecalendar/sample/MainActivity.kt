package io.github.wawakaka.simplecalendar.sample

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.github.wawakaka.simplecalendar.lib.view.calendar.SimpleCalendarView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(SimpleCalendarView(this).apply {
            setClickListener {
                Toast.makeText(context, "item clicked", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
