package io.github.wawakaka.simplecalendar.lib

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.simple_calendar_view.view.*

class SimpleCalendarView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private lateinit var adapter: SimpleCalendarAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private val data = daysInThisMonth().toMutableList()

    init {

        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.simple_calendar_view, this, true)

        //Initialize views
        getViewRef(view)
    }

    private fun getViewRef(view: View) {
        setLayoutManagerAndAdapter(view)
    }

    private fun setLayoutManagerAndAdapter(view: View) {
        adapter = SimpleCalendarAdapter()
        linearLayoutManager = LinearLayoutManager(
            view.context, LinearLayoutManager.HORIZONTAL, false
        )
        recycler_month.layoutManager = linearLayoutManager
        recycler_month.adapter = adapter

        adapter.data = data

        adapter.notifyDataSetChanged()
    }
}
