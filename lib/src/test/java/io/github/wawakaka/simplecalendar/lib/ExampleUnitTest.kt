package io.github.wawakaka.simplecalendar.lib

import io.github.wawakaka.simplecalendar.lib.utils.LocalDateUtil
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    init {
        initThreeTen()
    }

//    @Test
//    fun getRow_isCorrect() {
//        assertEquals(1, getRow(1))
//        assertEquals(1, getRow(7))
//        assertEquals(2, getRow(8))
//        assertEquals(2, getRow(14))
//        assertEquals(3, getRow(15))
//        assertEquals(3, getRow(21))
//        assertEquals(4, getRow(22))
//        assertEquals(4, getRow(28))
//        assertEquals(5, getRow(29))
//        assertEquals(5, getRow(35))
//        assertEquals(6, getRow(36))
//        assertEquals(6, getRow(42))
//    }
//
//    @Test
//    fun getCoulomb_isCorrect() {
//
//        assertEquals(1, getCoulomb(1))
//        assertEquals(2, getCoulomb(2))
//        assertEquals(2, getCoulomb(3))
//        assertEquals(2, getCoulomb(4))
//        assertEquals(2, getCoulomb(5))
//        assertEquals(2, getCoulomb(6))
//        assertEquals(3, getCoulomb(7))
//    }
//
//    @Test
//    fun getTopItemPosition_isCorrect() {
//        assertEquals(1, getTopItemPosition(8))
//        assertEquals(7, getTopItemPosition(14))
//        assertEquals(8, getTopItemPosition(15))
//        assertEquals(14, getTopItemPosition(21))
//        assertEquals(15, getTopItemPosition(22))
//        assertEquals(21, getTopItemPosition(28))
//        assertEquals(22, getTopItemPosition(29))
//        assertEquals(28, getTopItemPosition(35))
//        assertEquals(29, getTopItemPosition(36))
//        assertEquals(35, getTopItemPosition(42))
//    }
//
//    private fun getTopItemPosition(position: Int): Int {
//        return when {
//            position >= 8 -> {
//                position - 8
//            }
//            else -> {
//                0
//            }
//        }
//    }
//
//    private fun getCoulomb(position: Int): Int {
//        return when {
//            position % 7 == 0 -> 3
//            position % 7 == 1 -> 1
//            else -> 2
//        }
//    }
//
//    private fun getRow(position: Int): Int {
//        return when {
//            (position.toFloat() / 7.0f) <= 1.0f -> 1
//            (position.toFloat() / 7.0f) <= 2.0f -> 2
//            (position.toFloat() / 7.0f) <= 3.0f -> 3
//            (position.toFloat() / 7.0f) <= 4.0f -> 4
//            (position.toFloat() / 7.0f) <= 5.0f -> 5
//            else -> 6
//        }
//    }

    @Test
    fun getListOfDays_isCorrect() {
        val list = LocalDateUtil.getListOfDays(2020, 5)
        println(list)
        assertEquals(31, list.size)
    }

}
