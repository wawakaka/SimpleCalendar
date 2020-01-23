package io.github.wawakaka.simplecalendar.lib.view.calendar.day

import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import io.github.wawakaka.simplecalendar.lib.R
import io.github.wawakaka.simplecalendar.lib.data.SimpleDayData
import io.github.wawakaka.simplecalendar.lib.utils.ViewUtil
import kotlinx.android.synthetic.main.simple_day_view.view.*
import org.threeten.bp.format.DateTimeFormatter
import java.lang.Exception

internal class SimpleDayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindViews(data: SimpleDayData, clickListener: (() -> Unit)?) {
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
        itemView.text_day.layoutParams = layoutParams
    }

    private fun setDay(data: SimpleDayData) {
        // todo try to remember why i put try catch here
        itemView.text_day.text = try {
            data.day.format(DateTimeFormatter.ofPattern("d"))
        } catch (e: Exception) {
            "xx"
        }
        itemView.text_day.setTextColor(
            if (data.day.month != data.month) {
                ContextCompat.getColor(itemView.context, R.color.disabled_day_color)
            } else {
                ContextCompat.getColor(itemView.context, R.color.default_day_color)
            }
        )
    }

    private fun setClickListener(clickListener: (() -> Unit)?) {
        itemView.container.setOnClickListener { clickListener?.invoke() }
    }

}