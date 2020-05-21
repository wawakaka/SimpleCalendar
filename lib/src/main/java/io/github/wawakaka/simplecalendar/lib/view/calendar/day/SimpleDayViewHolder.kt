package io.github.wawakaka.simplecalendar.lib.view.calendar.day

import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import io.github.wawakaka.simplecalendar.lib.R
import io.github.wawakaka.simplecalendar.lib.data.*
import io.github.wawakaka.simplecalendar.lib.utils.LocalDateUtil
import io.github.wawakaka.simplecalendar.lib.utils.ViewUtil

internal class SimpleDayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val textDay = itemView.findViewById<TextView>(R.id.day_view_text_day)
    private val container = itemView.findViewById<FrameLayout>(R.id.day_view_container)

    fun bindViews(data: SimpleDateData, clickListener: ((SimpleDateData) -> Unit)?) {
        setContainerSize()
        setDay(data)
        setState(data)
        setClickListener(data, clickListener)
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

    private fun setClickListener(
        data: SimpleDateData,
        clickListener: ((SimpleDateData) -> Unit)?
    ) {
        container.setOnClickListener {
            Log.e("setClickListener", "clicked")
            updateState(data)
            setState(data)
            clickListener?.invoke(data)
        }
    }

    private fun updateState(
        data: SimpleDateData
    ) {
        when (data.mode) {
            is SimpleViewItem -> {
                when (data.mode.state) {
                    SimpleViewStates.NORMAL -> {
                        data.mode.state = SimpleViewStates.SELECTED
                    }
                    SimpleViewStates.SELECTED -> {
                        data.mode.state = SimpleViewStates.NORMAL
                    }
                    else -> {
                        throw IllegalStateException("unhandled state")
                    }
                }
            }
            is SimpleRangedViewItem -> {
                when (data.mode.state) {
                    SimpleRangedViewStates.SELECTED_START -> {
                        data.mode.state = SimpleRangedViewStates.NORMAL
                    }
                    SimpleRangedViewStates.SELECTED_MID -> {
                        data.mode.state = SimpleRangedViewStates.SELECTED_START
                    }
                    SimpleRangedViewStates.SELECTED_END -> {
                        data.mode.state = SimpleRangedViewStates.SELECTED_START
                    }
                    SimpleRangedViewStates.NORMAL -> {
                        data.mode.state = SimpleRangedViewStates.SELECTED_START
                    }
                    else -> {
                        throw IllegalStateException("unhandled state")
                    }
                }
            }
        }
    }

    private fun setState(data: SimpleDateData) {
        when (data.mode) {
            is SimpleViewItem -> {
                when (data.mode.state) {
                    SimpleViewStates.NORMAL -> {
                        container.setBackgroundColor(Color.WHITE)
                    }
                    SimpleViewStates.SELECTED -> {
                        container.setBackgroundColor(Color.RED)
                    }
                    else -> {
                        throw IllegalStateException("unhandled state")
                    }
                }
            }
            is SimpleRangedViewItem -> {
                when (data.mode.state) {
                    SimpleRangedViewStates.SELECTED_START -> {
                        container.setBackgroundColor(Color.GREEN)
                    }
                    SimpleRangedViewStates.SELECTED_MID -> {
                        container.setBackgroundColor(Color.GREEN)
                    }
                    SimpleRangedViewStates.SELECTED_END -> {
                        container.setBackgroundColor(Color.GREEN)
                    }
                    SimpleRangedViewStates.NORMAL -> {
                        container.setBackgroundColor(Color.WHITE)
                    }
                    else -> {
                        throw IllegalStateException("unhandled state")
                    }
                }
            }
        }
    }

}