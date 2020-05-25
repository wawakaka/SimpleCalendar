package io.github.wawakaka.simplecalendar.lib.utils

import android.content.Context
import io.github.wawakaka.simplecalendar.lib.data.*
import io.github.wawakaka.simplecalendar.lib.extension.displayWidth
import io.github.wawakaka.simplecalendar.lib.utils.SimpleConstant.NUMBER_OF_DAYS_IN_ONE_WEEK

internal object ViewUtil {

    fun getDayViewWidthSize(context: Context): Int {
        return context.displayWidth / NUMBER_OF_DAYS_IN_ONE_WEEK
    }

//    private fun updateState(
//        data: SimpleDateData
//    ) {
//        return when (data.mode) {
//            is SimpleViewModeSingle -> {
//                when ((data.mode as SimpleViewModeSingle).state) {
//                    SimpleViewStates.NORMAL -> {
//                        (data.mode as SimpleViewModeSingle).state = SimpleViewStates.SELECTED
//                    }
//                    SimpleViewStates.SELECTED -> {
//                        (data.mode as SimpleViewModeSingle).state = SimpleViewStates.NORMAL
//                    }
//                    else -> {
//                        throw IllegalStateException("unhandled state")
//                    }
//                }
//            }
//            is SimpleViewModeRanged -> {
//                when ((data.mode as SimpleViewModeRanged).state) {
//                    SimpleRangedViewStates.SELECTED_START -> {
//                        (data.mode as SimpleViewModeRanged).state = SimpleRangedViewStates.NORMAL
//                    }
//                    SimpleRangedViewStates.SELECTED_MID -> {
//                        (data.mode as SimpleViewModeRanged).state =
//                            SimpleRangedViewStates.SELECTED_START
//                    }
//                    SimpleRangedViewStates.SELECTED_END -> {
//                        (data.mode as SimpleViewModeRanged).state =
//                            SimpleRangedViewStates.SELECTED_START
//                    }
//                    SimpleRangedViewStates.NORMAL -> {
//                        (data.mode as SimpleViewModeRanged).state =
//                            SimpleRangedViewStates.SELECTED_START
//                    }
//                    else -> {
//                        throw IllegalStateException("unhandled state")
//                    }
//                }
//            }
//        }
//    }
}