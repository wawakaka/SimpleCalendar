package io.github.wawakaka.simplecalendar.lib.view.calendar.month

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.wawakaka.simplecalendar.lib.data.SimpleCalendarData
import io.github.wawakaka.simplecalendar.lib.data.SimpleDateData
import io.github.wawakaka.simplecalendar.lib.data.SimpleMode
import io.github.wawakaka.simplecalendar.lib.utils.LocalDateUtil
import org.threeten.bp.LocalDate

internal class SimpleMonthAdapter(@SimpleMode private val mode: Int) :
    RecyclerView.Adapter<SimpleMonthViewHolder>() {

    var data = mutableListOf<SimpleCalendarData>()
    var clickListener: ((SimpleDateData) -> Unit)? = null
    private var reference: LocalDate? = null

    init {
        setHasStableIds(true)
    }

    fun initData(localDate: LocalDate) {
        this.data.apply {
            clear()
            addAll(LocalDateUtil.getDataFrom(localDate))
            reference = localDate
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
        val next = LocalDate.of(data.last().year, data.last().month, data.last().day)
            .plusYears(1)
        data.addAll(LocalDateUtil.getDataFrom(next))
        notifyItemInserted(data.size)
    }

    fun loadPreviousYear() {
        val previous = LocalDate.of(data.first().year, data.first().month, data.first().day)
            .minusYears(1)
        val previousData = LocalDateUtil.getDataFrom(previous)
        data.addAll(0, previousData)
        notifyItemRangeInserted(0, 12)
    }
}
