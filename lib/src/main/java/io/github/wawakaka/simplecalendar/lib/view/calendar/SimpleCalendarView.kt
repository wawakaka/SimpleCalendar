package io.github.wawakaka.simplecalendar.lib.view.calendar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.threetenabp.AndroidThreeTen
import io.github.wawakaka.simplecalendar.lib.R
import io.github.wawakaka.simplecalendar.lib.utils.EndlessRecyclerViewScrollListener
import io.github.wawakaka.simplecalendar.lib.view.calendar.month.SimpleMonthAdapter
import kotlinx.android.synthetic.main.simple_calendar_view.view.*

class SimpleCalendarView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    var clickListener: (() -> Unit)? = null
    private lateinit var adapter: SimpleMonthAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener

    init {
        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.simple_calendar_view, this, true)
        AndroidThreeTen.init(context)
        getViewRef(view)
    }

    private fun getViewRef(view: View) {
        setLayoutManagerAndAdapter(view)
    }

    private fun setLayoutManagerAndAdapter(view: View) {
        adapter = SimpleMonthAdapter().apply {
            clickListener = this@SimpleCalendarView.clickListener
        }
        linearLayoutManager = LinearLayoutManager(
            view.context, LinearLayoutManager.HORIZONTAL, false
        )
        scrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadNext(page: Int, totalItemsCount: Int, view: RecyclerView) {
                adapter.loadNextYear()
            }

            override fun onLoadPrevious(page: Int, totalItemsCount: Int, view: RecyclerView) {
                adapter.loadPreviousYear()
            }
        }

        val snapHelper = PagerSnapHelper()
        recycler_calendar.layoutManager = linearLayoutManager
        recycler_calendar.adapter = adapter
        recycler_calendar.addOnScrollListener(scrollListener)
//        snapHelper.attachToRecyclerView(recycler_calendar)

        previous_month.post {
            previous_month.setOnClickListener {
                val position = (recycler_calendar.layoutManager as LinearLayoutManager)
                    .findLastCompletelyVisibleItemPosition()
                try {
                    recycler_calendar.smoothScrollToPosition(position - 1)
                    Toast.makeText(
                        context,
                        "previous button clicked ${position - 1}",
                        Toast.LENGTH_SHORT
                    ).show()
                } catch (e: IllegalArgumentException) {
                    Toast.makeText(
                        context,
                        "$e",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        next_month.post {
            next_month.setOnClickListener {
                val position = (recycler_calendar.layoutManager as LinearLayoutManager)
                    .findLastCompletelyVisibleItemPosition()
                recycler_calendar.smoothScrollToPosition(position + 1)
                Toast.makeText(context, "next button clicked ${position + 1}", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
}
