package io.github.wawakaka.simplecalendar.lib.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import io.github.wawakaka.simplecalendar.lib.R
import kotlinx.android.synthetic.main.simple_calendar_view.view.*

class SimpleCalendarView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private lateinit var adapter: SimpleCalendarAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener

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
        scrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                adapter.loadNextYear()
            }
        }

        val snapHelper = PagerSnapHelper()
        recycler_month.layoutManager = linearLayoutManager
        recycler_month.adapter = adapter
        recycler_month.addOnScrollListener(scrollListener)
        snapHelper.attachToRecyclerView(recycler_month)

        previous_month.post {
            previous_month.setOnClickListener {
                val position = (recycler_month.layoutManager as LinearLayoutManager)
                    .findLastCompletelyVisibleItemPosition()
                try {
                    recycler_month.smoothScrollToPosition(position - 1)
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
                val position = (recycler_month.layoutManager as LinearLayoutManager)
                    .findLastCompletelyVisibleItemPosition()
                recycler_month.smoothScrollToPosition(position + 1)
                Toast.makeText(context, "next button clicked ${position + 1}", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
}