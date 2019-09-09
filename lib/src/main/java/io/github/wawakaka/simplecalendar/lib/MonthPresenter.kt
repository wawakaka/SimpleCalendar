package io.github.wawakaka.simplecalendar.lib

internal object MonthPresenter {

    fun getCoulomb(position: Int): Int {
        return when {
            position % 7 == 0 -> 3
            position % 7 == 1 -> 1
            else -> 2
        }
    }

    fun getRow(position: Int): Int {
        return when {
            (position.toFloat() / 7.0f) <= 1.0f -> 1
            (position.toFloat() / 7.0f) <= 2.0f -> 2
            (position.toFloat() / 7.0f) <= 3.0f -> 3
            (position.toFloat() / 7.0f) <= 4.0f -> 4
            (position.toFloat() / 7.0f) <= 5.0f -> 5
            else -> 6
        }
    }

    fun getTopItemPosition(position: Int): Int {
        return when {
            position >= 8 -> {
                position - 8
            }
            else -> {
                0
            }
        }
    }
}