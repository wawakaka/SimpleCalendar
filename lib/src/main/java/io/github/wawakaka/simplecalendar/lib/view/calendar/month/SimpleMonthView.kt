package io.github.wawakaka.simplecalendar.lib.view.calendar.month

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import io.github.wawakaka.simplecalendar.lib.R
import io.github.wawakaka.simplecalendar.lib.data.SimpleDateData
import io.github.wawakaka.simplecalendar.lib.data.SimpleModes
import io.github.wawakaka.simplecalendar.lib.data.SimpleMonthData
import io.github.wawakaka.simplecalendar.lib.data.SimpleViewStates
import io.github.wawakaka.simplecalendar.lib.utils.LocalDateUtil
import io.github.wawakaka.simplecalendar.lib.utils.SimpleConstant.NUMBER_OF_DAYS_IN_ONE_WEEK
import io.github.wawakaka.simplecalendar.lib.utils.ViewUtil
import io.github.wawakaka.simplecalendar.lib.view.calendar.day.SimpleDayView

class SimpleMonthView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        orientation = VERTICAL
    }

    fun init(
        data: SimpleMonthData,
        clickListener: ((SimpleDateData) -> Unit)?
    ) {
        removeAllViews()
        when (data.dateData.size) {
            28 -> addRows(4, data, clickListener)
            35 -> addRows(5, data, clickListener)
            42 -> addRows(6, data, clickListener)
            else -> throw IllegalStateException("invalid size, size = ${data.dateData.size}")
        }
    }

    private fun addRows(
        numberOfRow: Int, data: SimpleMonthData,
        clickListener: ((SimpleDateData) -> Unit)?
    ) {
        for (row in 1..numberOfRow) {
            createRow(data.dateData.subList((row - 1) * 7, row * 7), clickListener)
        }
    }

    private fun createRow(
        dateData: MutableList<SimpleDateData>,
        clickListener: ((SimpleDateData) -> Unit)?
    ) {
        if (dateData.size == 7) {
            addView(
                LinearLayout(context).apply {
                    val params = LayoutParams(
                        LayoutParams.MATCH_PARENT,
                        LayoutParams.WRAP_CONTENT
                    )
                    orientation = HORIZONTAL
                    layoutParams = params
                    addColumns(this, dateData, clickListener)
                }
            )
        } else {
            throw IllegalStateException("invalid size to create row, size = ${dateData.size}")
        }
    }

    private fun addColumns(
        parent: LinearLayout,
        data: MutableList<SimpleDateData>,
        clickListener: ((SimpleDateData) -> Unit)?
    ) {
        for (index in 0 until NUMBER_OF_DAYS_IN_ONE_WEEK) {
            parent.addView(
                SimpleDayView(context).apply {
                    val params = LayoutParams(
                        ViewUtil.getDayViewWidthSize(context),
                        ViewUtil.getDayViewWidthSize(context)
                    )
                    layoutParams = params
                    val dateData = data[index]
                    val container = findViewById<FrameLayout>(R.id.day_view_container)
                    val textView = findViewById<TextView>(R.id.day_view_text_day)
                    textView.text = LocalDateUtil.getDayText(dateData.day)
                    setTextColor(dateData, textView)
                    setViewState(dateData, container)
                    setOnClickListener {
                        dateData.stateOnSingleMode = if (dateData.stateOnSingleMode == SimpleViewStates.NORMAL) {
                            SimpleViewStates.SELECTED
                        } else {
                            SimpleViewStates.NORMAL
                        }
                        clickListener?.invoke(dateData)
                    }
                }
            )
        }
    }

    private fun setTextColor(
        dateData: SimpleDateData,
        textView: TextView
    ) {
        if (dateData.day.monthOfYear != dateData.month) {
            textView.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.disabled_day_color
                )
            )
        } else {
            textView.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.default_day_color
                )
            )
        }
    }

    private fun setViewState(
        dateData: SimpleDateData,
        container: FrameLayout
    ) {
        when (dateData.mode) {
            SimpleModes.SINGLE -> {
                when (dateData.stateOnSingleMode) {
                    SimpleViewStates.NORMAL -> {
                        container.setBackgroundColor(ContextCompat.getColor(context,R.color.white))
                    }
                    SimpleViewStates.SELECTED -> {
                        container.setBackgroundColor(ContextCompat.getColor(context,R.color.red))
                    }
                }
            }
        }
    }

    fun findChildWithTag(tag: Int) {
        Log.e("SimpleMonthView", "child count: $childCount")
    }
}