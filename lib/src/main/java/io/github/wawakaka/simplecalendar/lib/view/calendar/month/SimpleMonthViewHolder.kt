package io.github.wawakaka.simplecalendar.lib.view.calendar.month

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.wawakaka.simplecalendar.lib.R
import io.github.wawakaka.simplecalendar.lib.data.*
import io.github.wawakaka.simplecalendar.lib.utils.LocalDateUtil
import io.github.wawakaka.simplecalendar.lib.utils.SimpleConstant
import io.github.wawakaka.simplecalendar.lib.view.calendar.day.SimpleDayAdapter

internal class SimpleMonthViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    private val textYear = itemView.findViewById<TextView>(R.id.month_view_text_year)
    private val textMonth = itemView.findViewById<TextView>(R.id.month_view_text_month_name)
    private val recyclerDays = itemView.findViewById<RecyclerView>(R.id.month_view_recycler_days)

    fun bindViews(
        data: SimpleCalendarData,
        clickListener: ((SimpleDateData) -> Unit)?,
        @SimpleMode mode: Int
    ) {
        setYear(data)
        setMonthName(data)
        setMonth(data, clickListener, mode)
    }

    private fun setYear(data: SimpleCalendarData) {
        textYear.text = LocalDateUtil.getYearText(data)
    }

    private fun setMonthName(data: SimpleCalendarData) {
        textMonth.text = LocalDateUtil.getMonthText(data)
    }

    private fun setMonth(
        data: SimpleCalendarData,
        clickListener: ((SimpleDateData) -> Unit)?,
        @SimpleMode mode: Int
    ) {
        val dates = mutableListOf<SimpleDateData>()
        LocalDateUtil.days(data.year, data.month).forEach {
            dates.add(
                SimpleDateData(
                    day = it,
                    month = LocalDateUtil.getMonth(data.month),
                    mode = getState(mode)
                )
            )
        }

        recyclerDays.apply {
            layoutManager = GridLayoutManager(context, SimpleConstant.NUMBER_OF_DAYS_IN_ONE_WEEK)
            adapter = SimpleDayAdapter(dates, clickListener)
        }
    }

    private fun getState(@SimpleMode mode: Int): SimpleState {
        return when (mode) {
            SimpleModes.NORMAL -> {
                SimpleViewItem(SimpleViewStates.NORMAL)
            }
            SimpleModes.RANGED -> {
                SimpleRangedViewItem(SimpleRangedViewStates.NORMAL)
            }
            else -> {
                throw IllegalStateException("unhandled mode")
            }
        }
    }

}