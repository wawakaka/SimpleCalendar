package io.github.wawakaka.simplecalendar.lib

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.*

internal class SimpleCalendarAdapter : RecyclerView.Adapter<SimpleCalendarViewHolder>() {

    var data: MutableList<Date> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleCalendarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.month, parent, false
        )
        return SimpleCalendarViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: SimpleCalendarViewHolder, position: Int) {
        holder.bindViews(data)
    }
}
