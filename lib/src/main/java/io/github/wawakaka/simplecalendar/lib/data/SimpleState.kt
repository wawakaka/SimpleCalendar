package io.github.wawakaka.simplecalendar.lib.data

sealed class SimpleState

internal data class SimpleViewItem(@SimpleViewState var state: Int) : SimpleState()

internal data class SimpleRangedViewItem(@SimpleRangedViewState var state: Int) : SimpleState()

