package io.github.wawakaka.simplecalendar.lib.view.calendar.month

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
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
        val numberOfWeeks = LocalDateUtil.countNumberOfWeekInAMonth(data.dateData.first().day)
        for (week in 1..numberOfWeeks) {
            createRow(
                week,
                LocalDateUtil.getListOfDataInWeekOfMonth(week, data.dateData),
                clickListener
            )
        }
    }

    private fun createRow(
        week: Int,
        dateData: MutableList<SimpleDateData>,
        clickListener: ((SimpleDateData) -> Unit)?
    ) {
        addView(
            LinearLayout(context).apply {
                val params = LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT
                )
                gravity = if (week == 1) {
                    Gravity.END
                } else {
                    Gravity.START
                }
                orientation = HORIZONTAL
                layoutParams = params
                addColumns(this, dateData, clickListener)
            }
        )
    }

    private fun addColumns(
        parent: LinearLayout,
        data: MutableList<SimpleDateData>,
        clickListener: ((SimpleDateData) -> Unit)?
    ) {
        for (index in 0 until data.size) {
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
                        dateData.stateOnSingleMode =
                            if (dateData.stateOnSingleMode == SimpleViewStates.NORMAL) {
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
                        container.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
                    }
                    SimpleViewStates.SELECTED -> {
                        container.setBackgroundColor(ContextCompat.getColor(context, R.color.red))
                    }
                }
            }
        }
    }

    fun findChildWithTag(tag: Int) {
        Log.e("SimpleMonthView", "child count: $childCount")
    }
}