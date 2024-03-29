package io.github.wawakaka.simplecalendar.lib.view.calendar

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import io.github.wawakaka.simplecalendar.lib.data.SimpleDateData
import io.github.wawakaka.simplecalendar.lib.data.SimpleMode
import io.github.wawakaka.simplecalendar.lib.data.SimpleMonthData
import io.github.wawakaka.simplecalendar.lib.utils.LocalDateUtil
import org.joda.time.DateTimeZone
import org.joda.time.LocalDate

internal class SimpleCalendarAdapter(@SimpleMode private val mode: Int) :
    RecyclerView.Adapter<SimpleCalendarViewHolder>() {

    var data = mutableListOf<SimpleMonthData>()
    var clickListener: ((SimpleDateData, SimpleMonthData) -> Unit)? = null
    var bindingDataListener: (() -> Unit)? = null

    init {
        setHasStableIds(true)
    }

    fun initData(localDate: LocalDate) {
        this.data.apply {
            clear()
            addAll(LocalDateUtil.getDataFrom(localDate, mode))
        }
        notifyDataSetChanged()
    }

    fun loadNextYear() {
        val next = LocalDate.now(DateTimeZone.forID("Asia/Jakarta"))
            .withMonthOfYear(data.last().month)
            .withYear(data.last().year)
            .plusYears(1)
        data.addAll(LocalDateUtil.getDataFrom(next, mode))
        notifyItemInserted(data.size)
    }

    fun loadPreviousYear() {
        val previous = LocalDate.now(DateTimeZone.forID("Asia/Jakarta"))
            .withMonthOfYear(data.first().month)
            .withYear(data.first().year)
            .minusYears(1)
        val previousData = LocalDateUtil.getDataFrom(previous, mode)
        data.addAll(0, previousData)
        notifyItemRangeInserted(0, 12)
    }

    override fun getItemId(position: Int): Long {
        return data[position].id.hashCode().toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleCalendarViewHolder {
        return SimpleCalendarViewHolder(
            itemView = SimpleCalendarView(
                parent.context
            ),
            clickListener = clickListener,
            mode = mode
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: SimpleCalendarViewHolder, position: Int) {
        if (position != NO_POSITION) {
            holder.bindViews(data = data[position])
            bindingDataListener?.invoke()
        }
    }

    override fun onBindViewHolder(
        holder: SimpleCalendarViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (position != NO_POSITION && payloads.isNullOrEmpty().not()) {
            Log.e("SimpleMonthAdapter", "Payloads: $payloads")
            holder.bindPayload(data[position])
            bindingDataListener?.invoke()
        }
        super.onBindViewHolder(holder, position, payloads)
    }
}
