package io.github.wawakaka.simplecalendar.lib.view.calendar.month.day

import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import io.github.wawakaka.simplecalendar.lib.utils.ViewUtil
import kotlinx.android.synthetic.main.simple_day_view.view.*
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

internal class SimpleDayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindViews(date: LocalDate, clickListener: (() -> Unit)?) {
        setContainerSize()
        setDay(date)
        setClickListener(clickListener)
    }

    private fun setContainerSize() {
        val layoutParams = FrameLayout.LayoutParams(
            ViewUtil.getDayBoxSize(itemView.context),
            ViewUtil.getDayBoxSize(itemView.context)
        )
        itemView.text_day.layoutParams = layoutParams
    }

    private fun setDay(date: LocalDate) {
        itemView.text_day.text = try {
            date.format(DateTimeFormatter.ofPattern("dd"))
        } catch (e: IndexOutOfBoundsException) {
            "xx"
        }
    }

    private fun setClickListener(clickListener: (() -> Unit)?) {
        itemView.container.setOnClickListener { clickListener?.invoke() }
    }

}