package io.github.wawakaka.simplecalendar.lib.view.calendar.day

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import io.github.wawakaka.simplecalendar.lib.R
import io.github.wawakaka.simplecalendar.lib.extension.setTextColorCompat
import io.github.wawakaka.simplecalendar.lib.utils.SimpleConstant.DEFAULT_TEXT_SIZE

internal class SimpleDayView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT
        )
        id = R.id.day_view_container
        addView(textDay())
    }

    private fun textDay(): TextView {
        return TextView(context).apply {
            id = R.id.day_view_text_day
            val params = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
            ).apply {
                gravity = Gravity.CENTER
            }
            textSize = DEFAULT_TEXT_SIZE
            layoutParams = params
            gravity = Gravity.CENTER
            setTextColorCompat(R.color.black)
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 22f)

            val outValue = TypedValue()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                context.theme.resolveAttribute(
                    android.R.attr.selectableItemBackgroundBorderless,
                    outValue,
                    true
                )
//            foreground = ContextCompat.getDrawable(context, outValue.resourceId)
                setBackgroundResource(outValue.resourceId)
            }
        }
    }
}