package io.github.wawakaka.simplecalendar.lib.view.calendar.day

import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import io.github.wawakaka.simplecalendar.lib.R
import io.github.wawakaka.simplecalendar.lib.data.SimpleDateData
import io.github.wawakaka.simplecalendar.lib.utils.LocalDateUtil
import io.github.wawakaka.simplecalendar.lib.utils.ViewUtil

internal class SimpleDayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val textDay = itemView.findViewById<TextView>(R.id.day_view_text_day)
    private val container = itemView.findViewById<FrameLayout>(R.id.day_view_container)

    fun bindViews(data: SimpleDateData, clickListener: (() -> Unit)?) {
        setContainerSize()
        setDay(data)
        setClickListener(clickListener)
    }

    private fun setContainerSize() {
        val dayBoxSize = ViewUtil.getDayBoxSize(itemView.context)
        val layoutParams = FrameLayout.LayoutParams(
            dayBoxSize,
            dayBoxSize
        )
        textDay.layoutParams = layoutParams
    }

    private fun setDay(data: SimpleDateData) {
        // todo try to remember why i put try catch here
        textDay.text = LocalDateUtil.getDayText(data.day)
        textDay.setTextColor(
            if (data.day.month != data.month) {
                ContextCompat.getColor(itemView.context, R.color.disabled_day_color)
            } else {
                ContextCompat.getColor(itemView.context, R.color.default_day_color)
            }
        )
    }

    private fun setClickListener(clickListener: (() -> Unit)?) {
        container.setOnClickListener {
            clickListener?.invoke()
            Log.e("setClickListener", "clicked")
        }
    }

}