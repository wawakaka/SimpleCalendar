package io.github.wawakaka.simplecalendar.lib.view.calendar

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import io.github.wawakaka.simplecalendar.lib.R
import io.github.wawakaka.simplecalendar.lib.extension.dpToPx
import io.github.wawakaka.simplecalendar.lib.extension.setTextColorCompat


internal class SimpleCalendarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        orientation = VERTICAL
        addView(textYear())
        addView(textMonth())
        addView(monthView())
    }

    private fun textYear(): View {
        return TextView(context).apply {
            id = R.id.month_view_text_year
            val params = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
            )
            context?.let {
                params.setMargins(
                    it.dpToPx(16f),
                    it.dpToPx(16f),
                    it.dpToPx(16f),
                    it.dpToPx(16f)
                )
            }
            layoutParams = params
            gravity = Gravity.CENTER
            setTextColorCompat(R.color.black)
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 22f)
        }
    }

    private fun textMonth(): View {
        return TextView(context).apply {
            id = R.id.month_view_text_month_name
            val params = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
            )
            context?.let {
                params.setMargins(
                    it.dpToPx(16f),
                    it.dpToPx(16f),
                    it.dpToPx(16f),
                    it.dpToPx(16f)
                )
            }
            layoutParams = params
            gravity = Gravity.CENTER
            setTextColorCompat(R.color.black)
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 22f)
        }
    }

    private fun monthView(): View {
        return LinearLayout(context).apply {
            id = R.id.month_view
            val params = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
            )
            layoutParams = params
        }
    }
}