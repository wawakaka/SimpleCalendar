package io.github.wawakaka.simplecalendar.lib.extension

import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import android.util.TypedValue
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import io.github.wawakaka.simplecalendar.lib.R

internal inline val Context.displayHeight: Int get() = resources.displayMetrics.heightPixels

internal inline val Context.displayWidth: Int get() = resources.displayMetrics.widthPixels

internal inline val Context.displayMetrics: DisplayMetrics? get() = resources.displayMetrics

internal fun Context.dpToPx(dp: Float): Int =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics).toInt()

internal fun Context.pxToDp(px: Float): Int =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px, displayMetrics).toInt()

internal fun TextView.setTextColorCompat(@ColorRes colorRes:Int){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        setTextColor(resources.getColor(colorRes, null))
    } else {
        setTextColor(resources.getColor(colorRes))
    }
}
