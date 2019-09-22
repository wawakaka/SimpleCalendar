package io.github.wawakaka.simplecalendar.lib.view

import android.content.Context
import android.util.TypedValue
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import io.github.wawakaka.simplecalendar.lib.R
import io.github.wawakaka.simplecalendar.lib.utils.ViewUtil
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

internal class SimpleCalendarGenerator(
    private val context: Context,
    private val dates: MutableList<LocalDate>
) {

    private var index = 0

    fun createView(): LinearLayout {
        index = 0
        return LinearLayout(context).apply {
            val linearParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            weightSum = 6f
            layoutParams = linearParams
            orientation = LinearLayout.VERTICAL

            addView(createMonthName())
            addView(createRows())
            addView(createRows())
            addView(createRows())
            addView(createRows())
            addView(createRows())
            addView(createRows())
        }
    }

    private fun createMonthName(): TextView {
        val textViewParams = FrameLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            gravity = Gravity.CENTER
        }
        return TextView(context).apply {
            layoutParams = textViewParams
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 28f)
            gravity = Gravity.CENTER
            text = dates[8].month.name
        }
    }

    private fun createRows(): LinearLayout {
        return LinearLayout(context).apply {
            val linearParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            weightSum = 7f
            layoutParams = linearParams
            orientation = LinearLayout.HORIZONTAL

            addView(createTextContainer())
            addView(createTextContainer())
            addView(createTextContainer())
            addView(createTextContainer())
            addView(createTextContainer())
            addView(createTextContainer())
            addView(createTextContainer())
        }
    }

    private fun createTextContainer(): FrameLayout {
        return FrameLayout(context).apply {
            val frameParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f
            )
            val outValue = TypedValue()
            context.theme.resolveAttribute(
                R.attr.selectableItemBackground,
                outValue,
                true
            )
            foreground = ContextCompat.getDrawable(context, outValue.resourceId)
            layoutParams = frameParams
            addView(createTextView())
        }
    }

    private fun createTextView(): TextView {
        val textViewParams = FrameLayout.LayoutParams(
            ViewUtil.getDayBoxSize(context),
            ViewUtil.getDayBoxSize(context)
        ).apply {
            gravity = Gravity.CENTER
        }
        return TextView(context).apply {
            layoutParams = textViewParams
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 22f)
            text = try {
                dates[index].format(DateTimeFormatter.ofPattern("dd"))
            } catch (e: IndexOutOfBoundsException) {
                "xx"
            }
            gravity = Gravity.CENTER
            index++
        }
    }
}