package io.github.wawakaka.simplecalendar.lib

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import kotlinx.android.synthetic.main.simple_calendar_view.view.*

class SimpleCalendarView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private lateinit var adapter: SimpleCalendarAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    init {

        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.simple_calendar_view, this, true)

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

        val snapHelper = PagerSnapHelper()
        recycler_month.layoutManager = linearLayoutManager
        recycler_month.adapter = adapter
        recycler_month.recycledViewPool.setMaxRecycledViews(0, 12)
        recycler_month.setItemViewCacheSize(12)
        snapHelper.attachToRecyclerView(recycler_month)

        previous_month.post {
            previous_month.setOnClickListener {
                val position = (recycler_month.layoutManager as LinearLayoutManager)
                    .findFirstVisibleItemPosition()
                recycler_month.smoothScrollToPosition(position - 1)
                Toast.makeText(
                    context,
                    "previous button clicked ${position - 1}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        next_month.post {
            next_month.setOnClickListener {
                adapter.next()
                val position = (recycler_month.layoutManager as LinearLayoutManager)
                    .findFirstVisibleItemPosition()
                recycler_month.smoothScrollToPosition(position + 1)
                Toast.makeText(context, "next button clicked ${position + 1}", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
}
