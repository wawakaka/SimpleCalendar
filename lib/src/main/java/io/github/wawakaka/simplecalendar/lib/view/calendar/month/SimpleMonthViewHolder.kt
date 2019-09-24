package io.github.wawakaka.simplecalendar.lib.view.calendar.month

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.wawakaka.simplecalendar.lib.data.SimpleDayData
import io.github.wawakaka.simplecalendar.lib.data.SimpleMonthData
import io.github.wawakaka.simplecalendar.lib.utils.SimpleConstant
import io.github.wawakaka.simplecalendar.lib.view.calendar.month.day.SimpleDayAdapter
import kotlinx.android.synthetic.main.simple_month_view.view.*
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

internal class SimpleMonthViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindViews(data: SimpleMonthData) {
        setYear(data.days[SimpleConstant.MAGIC_INDEX])
        setMonthName(data.days[SimpleConstant.MAGIC_INDEX])
        setMonth(data)
    }

    private fun setYear(date: LocalDate) {
        itemView.text_year.text = try {
            date.format(DateTimeFormatter.ofPattern("YYYY", Locale("in", "ID")))
        } catch (e: IndexOutOfBoundsException) {
            "xx"
        }
    }

    private fun setMonthName(date: LocalDate) {
        itemView.text_month_name.text = try {
            date.format(DateTimeFormatter.ofPattern("MMMM", Locale("in", "ID")))
        } catch (e: IndexOutOfBoundsException) {
            "xx"
        }
    }

    private fun setMonth(data: SimpleMonthData) {
        val simpleDayData = data.days.map { SimpleDayData(month = data.month, day = it) }
        itemView.recycler_month.apply {
            layoutManager = GridLayoutManager(context, SimpleConstant.NUMBER_OF_DAYS_IN_ONE_WEEK)
            adapter = SimpleDayAdapter(simpleDayData).apply {
                clickListener =
                    { Toast.makeText(context, "item clicked", Toast.LENGTH_SHORT).show() }
            }
        }
    }

}