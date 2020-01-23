package io.github.wawakaka.simplecalendar.lib.utils

import android.content.Context
import io.github.wawakaka.simplecalendar.lib.extension.displayWidth
import io.github.wawakaka.simplecalendar.lib.utils.SimpleConstant.NUMBER_OF_DAYS_IN_ONE_WEEK

internal object ViewUtil {

    fun getDayBoxSize(context: Context): Int {
        return context.displayWidth / NUMBER_OF_DAYS_IN_ONE_WEEK
    }
}