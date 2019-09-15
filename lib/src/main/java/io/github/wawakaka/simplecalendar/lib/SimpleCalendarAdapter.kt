package io.github.wawakaka.simplecalendar.lib

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.wawakaka.simplecalendar.lib.data.SimpleMonthData

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
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.month, parent, false
        )
        return MonthViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MonthViewHolder, position: Int) {
        holder.itemView.post {
            holder.bindViews(data[position].days)
        }
    }

    fun next() {
    }

    fun previous() {

    }
}
