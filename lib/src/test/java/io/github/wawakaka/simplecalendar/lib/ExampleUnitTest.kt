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

    @Test
    fun days_isCorrect() {
        val list = LocalDateUtil.days(2020, 5)
        println(list)
        assertEquals(31, list.size)
    }

    @Test
    fun getListOfDays_isCorrect() {
        val list = LocalDateUtil.getListOfDays(2020, 5)
        println(list)
        assertEquals(31, list.size)
    }

}
