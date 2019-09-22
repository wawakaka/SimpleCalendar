package io.github.wawakaka.simplecalendar.lib.view

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import io.github.wawakaka.simplecalendar.lib.data.SimpleMonthData
import io.github.wawakaka.simplecalendar.lib.utils.LocalDateUtil

internal class SimpleCalendarAdapter : RecyclerView.Adapter<MonthViewHolder>() {

    private var data = mutableListOf<SimpleMonthData>()

    init {
        setHasStableIds(true)

        for (index in 1..12) {
            data.add(SimpleMonthData(days = LocalDateUtil.daysInMonth(index).toMutableList()))
        }
    }

    override fun getItemId(position: Int): Long {
        return data[position].id.hashCode().toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthViewHolder {
        return MonthViewHolder(FrameLayout(parent.context))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MonthViewHolder, position: Int) {
        holder.itemView.post {
            holder.bindViews(data[position].days)
        }
    }

    fun loadNextYear() {
        for (index in 1..12) {
            data.add(
                SimpleMonthData(
                    days = LocalDateUtil.days(
                        data.size + 1,
                        index
                    ).toMutableList()
                )
            )
            notifyItemInserted(data.size)
        }
    }
}
