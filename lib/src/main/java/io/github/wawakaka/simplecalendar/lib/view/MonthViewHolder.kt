package io.github.wawakaka.simplecalendar.lib.view

import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import org.threeten.bp.LocalDate

internal class MonthViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindViews(dates: MutableList<LocalDate>) {
        val rootView = itemView as FrameLayout
        rootView.removeAllViews()
        rootView.addView(
            SimpleCalendarGenerator(
                itemView.context,
                dates
            ).createView()
        )
    }

}