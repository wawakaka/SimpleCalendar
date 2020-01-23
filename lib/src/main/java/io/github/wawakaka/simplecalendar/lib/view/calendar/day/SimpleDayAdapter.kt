package io.github.wawakaka.simplecalendar.lib.view.calendar.day

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.wawakaka.simplecalendar.lib.R
import io.github.wawakaka.simplecalendar.lib.data.SimpleDayData

internal class SimpleDayAdapter(private val data: List<SimpleDayData>) :
    RecyclerView.Adapter<SimpleDayViewHolder>() {

    var clickListener: (() -> Unit)? = null

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return data[position].id.hashCode().toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleDayViewHolder {
        return SimpleDayViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.simple_day_view,
                null
            )
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: SimpleDayViewHolder, position: Int) {
        holder.bindViews(data[position], clickListener)
    }
}