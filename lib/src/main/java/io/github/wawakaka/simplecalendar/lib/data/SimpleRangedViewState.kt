package io.github.wawakaka.simplecalendar.lib.data

import androidx.annotation.IntDef

@Retention(AnnotationRetention.SOURCE)
@IntDef(
    SimpleRangedViewStates.SELECTED_START,
    SimpleRangedViewStates.SELECTED_MID,
    SimpleRangedViewStates.SELECTED_END,
    SimpleRangedViewStates.NORMAL
)
internal annotation class SimpleRangedViewState

internal object SimpleRangedViewStates {
    const val NORMAL = 0
    const val SELECTED_START = 1
    const val SELECTED_MID = 2
    const val SELECTED_END = 3
}