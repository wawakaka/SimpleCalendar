package io.github.wawakaka.simplecalendar.lib.view.calendar.month

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.wawakaka.simplecalendar.lib.data.SimpleCalendarData
import io.github.wawakaka.simplecalendar.lib.data.SimpleDateData
import io.github.wawakaka.simplecalendar.lib.data.SimpleMode
import io.github.wawakaka.simplecalendar.lib.utils.LocalDateUtil
import org.joda.time.DateTimeZone
import org.joda.time.LocalDate

internal class SimpleMonthAdapter(@SimpleMode private val mode: Int) :
    RecyclerView.Adapter<SimpleMonthViewHolder>() {

    var data = mutableListOf<SimpleCalendarData>()
    var clickListener: ((SimpleDateData) -> Unit)? = null

    init {
        setHasStableIds(true)
    }

    fun initData(localDate: LocalDate) {
        this.data.apply {
            clear()
            addAll(LocalDateUtil.getDataFrom(localDate))
        }
        notifyDataSetChanged()
    }

    override fun getItemId(position: Int): Long {
        return data[position].id.hashCode().toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleMonthViewHolder {
        return SimpleMonthViewHolder(SimpleMonthView(parent.context))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: SimpleMonthViewHolder, position: Int) {
        holder.bindViews(data = data[position], clickListener = clickListener, mode = mode)
    }

    fun loadNextYear() {
        val next = LocalDate.now(DateTimeZone.getDefault())
            .withDayOfMonth(data.last().day)
            .withMonthOfYear(data.last().month)
            .withYear(data.last().year)
            .plusYears(1)
        data.addAll(LocalDateUtil.getDataFrom(next))
        notifyItemInserted(data.size)
    }

    fun loadPreviousYear() {
        val previous = LocalDate.now(DateTimeZone.getDefault())
            .withDayOfMonth(data.first().day)
            .withMonthOfYear(data.first().month)
            .withYear(data.first().year)
            .minusYears(1)
        val previousData = LocalDateUtil.getDataFrom(previous)
        data.addAll(0, previousData)
        notifyItemRangeInserted(0, 12)
    }
}
