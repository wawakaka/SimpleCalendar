package io.github.wawakaka.simplecalendar.lib.view.calendar.month

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.wawakaka.simplecalendar.lib.R
import io.github.wawakaka.simplecalendar.lib.data.SimpleDateData
import io.github.wawakaka.simplecalendar.lib.data.SimpleMode
import io.github.wawakaka.simplecalendar.lib.data.SimpleMonthData
import io.github.wawakaka.simplecalendar.lib.utils.LocalDateUtil
import io.github.wawakaka.simplecalendar.lib.utils.SimpleConstant
import io.github.wawakaka.simplecalendar.lib.view.calendar.day.SimpleDayAdapter

internal class SimpleMonthViewHolder(
    itemView: View,
    @SimpleMode
    private val mode: Int,
    private val clickListener: ((Int, Int) -> Unit)?
) : RecyclerView.ViewHolder(itemView) {

    private val textYear = itemView.findViewById<TextView>(R.id.month_view_text_year)
    private val textMonth = itemView.findViewById<TextView>(R.id.month_view_text_month_name)
    private val recyclerDays = itemView.findViewById<RecyclerView>(R.id.month_view_recycler_days)
    private var adapter: SimpleDayAdapter? = null

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
        adapter?.setData(monthData.dateData)
    }

    private fun setYear(data: SimpleMonthData) {
        textYear.text = LocalDateUtil.getYearText(data)
    }

    private fun setMonthName(data: SimpleMonthData) {
        textMonth.text = LocalDateUtil.getMonthText(data)
    }

    private fun setMonth(data: SimpleMonthData) {
        val dates = mutableListOf<SimpleDateData>()
        LocalDateUtil.days(data.year, data.month).forEach {
            dates.add(
                SimpleDateData(
                    day = it,
                    month = LocalDateUtil.getMonth(data.month),
                    mode = mode
                )
            )
        }
        adapter = SimpleDayAdapter()
        adapter?.apply {
            setData(dates)
            clickListener = interceptListener(this@SimpleMonthViewHolder.clickListener)
        }
        recyclerDays.apply {
            layoutManager = GridLayoutManager(context, SimpleConstant.NUMBER_OF_DAYS_IN_ONE_WEEK)
            this.adapter = this@SimpleMonthViewHolder.adapter
        }
    }

    private fun interceptListener(
        clickListener: ((Int, Int) -> Unit)?
    ): ((Int) -> Unit)? =
        { position ->
            // todo do something here
            clickListener?.invoke(position, adapterPosition)
        }

}