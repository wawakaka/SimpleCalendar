package io.github.wawakaka.simplecalendar.lib.extension

import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue

internal inline val Context.displayHeight: Int get() = resources.displayMetrics.heightPixels

internal inline val Context.displayWidth: Int get() = resources.displayMetrics.widthPixels

internal inline val Context.displayMetrics: DisplayMetrics? get() = resources.displayMetrics

internal fun Context.dpToPx(dp: Float): Int =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics).toInt()

internal fun Context.pxToDp(px: Float): Int =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px, displayMetrics).toInt()
