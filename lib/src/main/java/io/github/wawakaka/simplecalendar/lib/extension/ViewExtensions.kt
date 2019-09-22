package io.github.wawakaka.simplecalendar.lib.extension

import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue

inline val Context.displayHeight: Int get() = resources.displayMetrics.heightPixels

inline val Context.displayWidth: Int get() = resources.displayMetrics.widthPixels

inline val Context.displayMetrics: DisplayMetrics? get() = resources.displayMetrics

fun Context.dpToPx(dp: Float): Int =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics).toInt()

fun Context.pxToDp(px: Float): Int =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px, displayMetrics).toInt()
