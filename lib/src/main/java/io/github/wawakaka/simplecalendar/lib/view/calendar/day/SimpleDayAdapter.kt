package io.github.wawakaka.simplecalendar.lib.view.calendar.day

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.wawakaka.simplecalendar.lib.data.SimpleDateData

internal class SimpleDayAdapter(
    private val data: List<SimpleDateData>,
    private val clickListener: (() -> Unit)? = null
) :
    RecyclerView.Adapter<SimpleDayViewHolder>() {

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return data[position].id.hashCode().toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleDayViewHolder {
        return SimpleDayViewHolder(SimpleDayView(parent.context))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: SimpleDayViewHolder, position: Int) {
        holder.bindViews(data[position], clickListener)
    }
}