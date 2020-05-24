package io.github.wawakaka.simplecalendar.lib.view.calendar

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.wawakaka.simplecalendar.lib.R
import io.github.wawakaka.simplecalendar.lib.data.SimpleMode
import io.github.wawakaka.simplecalendar.lib.data.SimpleMonthData
import io.github.wawakaka.simplecalendar.lib.utils.LocalDateUtil

internal class SimpleCalendarViewHolder(
    itemView: View,
    @SimpleMode
    private val mode: Int,
    private val clickListener: ((Int, Int) -> Unit)?
) : RecyclerView.ViewHolder(itemView) {

    private val textYear = itemView.findViewById<TextView>(R.id.month_view_text_year)
    private val textMonth = itemView.findViewById<TextView>(R.id.month_view_text_month_name)
    private val monthView = itemView.findViewById<RecyclerView>(R.id.month_view)

    fun bindViews(
        data: SimpleMonthData
    ) {
        setYear(data)
        setMonthName(data)
        setMonth(data)
    }

    fun bindPayload(
        monthData: SimpleMonthData
    ) {
    }

    private fun setYear(data: SimpleMonthData) {
        textYear.text = LocalDateUtil.getYearText(data)
    }

    private fun setMonthName(data: SimpleMonthData) {
        textMonth.text = LocalDateUtil.getMonthText(data)
    }

    private fun setMonth(data: SimpleMonthData) {

    }

}