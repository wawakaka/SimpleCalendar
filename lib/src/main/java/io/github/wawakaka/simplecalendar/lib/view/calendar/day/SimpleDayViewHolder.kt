package io.github.wawakaka.simplecalendar.lib.view.calendar.day

import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import io.github.wawakaka.simplecalendar.lib.R
import io.github.wawakaka.simplecalendar.lib.data.SimpleDateData
import io.github.wawakaka.simplecalendar.lib.data.SimpleModes
import io.github.wawakaka.simplecalendar.lib.data.SimpleViewStates
import io.github.wawakaka.simplecalendar.lib.utils.LocalDateUtil
import io.github.wawakaka.simplecalendar.lib.utils.ViewUtil

internal class SimpleDayViewHolder(
    itemView: View,
    private val clickListener: ((Int) -> Unit)?
) : RecyclerView.ViewHolder(itemView) {

    private val textDay = itemView.findViewById<TextView>(R.id.day_view_text_day)
    private val container = itemView.findViewById<FrameLayout>(R.id.day_view_container)

    fun bindViews(data: SimpleDateData) {
        setContainerSize()
        setDay(data)
        setState(data)
        setClickListener(data, clickListener)
    }

    fun bindPayload(data: SimpleDateData) {
        setState(data)
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
        textDay.text = LocalDateUtil.getDayText(data.day)
        textDay.setTextColor(
            if (data.day.monthOfYear != data.month) {
                ContextCompat.getColor(itemView.context, R.color.disabled_day_color)
            } else {
                ContextCompat.getColor(itemView.context, R.color.default_day_color)
            }
        )
    }

    private fun setClickListener(
        data: SimpleDateData,
        clickListener: ((Int) -> Unit)?
    ) {
        container.setOnClickListener {
            clickListener?.invoke(
                adapterPosition
            )
        }
    }

    private fun setState(data: SimpleDateData) {
        Log.e("SimpleDayViewHolder","setState: $data")
        when (data.mode) {
            SimpleModes.SINGLE -> {
                when (data.stateOnSingleMode) {
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
            SimpleModes.RANGED -> {
                //todo implementation
//                when ((data.mode as SimpleViewModeRanged).state) {
//                    SimpleRangedViewStates.SELECTED_START -> {
//                        container.setBackgroundColor(Color.GREEN)
//                    }
//                    SimpleRangedViewStates.SELECTED_MID -> {
//                        container.setBackgroundColor(Color.GREEN)
//                    }
//                    SimpleRangedViewStates.SELECTED_END -> {
//                        container.setBackgroundColor(Color.GREEN)
//                    }
//                    SimpleRangedViewStates.NORMAL -> {
//                        container.setBackgroundColor(Color.WHITE)
//                    }
//                    else -> {
//                        throw IllegalStateException("unhandled state")
//                    }
//                }
            }
        }
    }

}