package io.github.wawakaka.simplecalendar.lib.data

import androidx.annotation.IntDef

@Retention(AnnotationRetention.SOURCE)
@IntDef(
    SimpleViewStates.SELECTED,
    SimpleViewStates.NORMAL
)

internal annotation class SimpleViewState

internal object SimpleViewStates {
    const val NORMAL = 0
    const val SELECTED = 1
}