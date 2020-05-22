package io.github.wawakaka.simplecalendar.lib.view.calendar.day

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import io.github.wawakaka.simplecalendar.lib.data.SimpleDateData

internal class SimpleDayAdapter : RecyclerView.Adapter<SimpleDayViewHolder>() {

    private val data: MutableList<SimpleDateData> = mutableListOf()
    var clickListener: ((Int) -> Unit)? = null

    init {
        setHasStableIds(true)
    }

    fun setData(data: MutableList<SimpleDateData>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemId(position: Int): Long {
        return data[position].id.hashCode().toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleDayViewHolder {
        return SimpleDayViewHolder(
            SimpleDayView(parent.context),
            clickListener
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: SimpleDayViewHolder, position: Int) {
        if (position != NO_POSITION) {
            holder.bindViews(data[position])
        }
    }

    override fun onBindViewHolder(
        holder: SimpleDayViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (position != NO_POSITION && payloads.isNotEmpty()) {
            //todo update the data here, maybe?
            Log.e("SimpleDayAdapter", "Payloads: $payloads")
            holder.bindPayload(data[position])
        }
        super.onBindViewHolder(holder, position, payloads)
    }
}