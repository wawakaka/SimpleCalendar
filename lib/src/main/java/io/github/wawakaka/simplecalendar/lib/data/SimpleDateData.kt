package io.github.wawakaka.simplecalendar.lib.data

import org.joda.time.LocalDate
import java.util.*

data class SimpleDateData(
    val tag: Int = UUID.randomUUID().toString().hashCode(),
    val month: Int,
    val day: LocalDate,
    @SimpleMode var mode: Int = SimpleModes.SINGLE,
    @SimpleViewState var stateOnSingleMode: Int = SimpleViewStates.NORMAL,
    @SimpleRangedViewState var stateOnRangedMode: Int = SimpleRangedViewStates.NORMAL
)