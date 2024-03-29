package io.github.wawakaka.simplecalendar.lib.data

import androidx.annotation.IntDef

@Retention(AnnotationRetention.SOURCE)
@IntDef(
    SimpleModes.SINGLE,
    SimpleModes.RANGED
)
internal annotation class SimpleMode

internal object SimpleModes {
    const val SINGLE = 0
    const val RANGED = 1
}