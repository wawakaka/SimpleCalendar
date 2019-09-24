package io.github.wawakaka.simplecalendar.lib.view.calendar.month

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.wawakaka.simplecalendar.lib.R
import io.github.wawakaka.simplecalendar.lib.data.SimpleMonthData
import io.github.wawakaka.simplecalendar.lib.utils.LocalDateUtil
import io.github.wawakaka.simplecalendar.lib.utils.SimpleConstant
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId

internal class SimpleMonthAdapter : RecyclerView.Adapter<SimpleMonthViewHolder>() {

    private var data = mutableListOf<SimpleMonthData>()

    init {
        setHasStableIds(true)

        for (index in 1..12) {
            data.add(
                SimpleMonthData(
                    year = LocalDate.now(ZoneId.systemDefault()).year,
                    month = LocalDateUtil.getMonth(index),
                    days = LocalDateUtil.daysInMonth(index).toMutableList()
                )
            )
        }
    }

    override fun getItemId(position: Int): Long {
        return data[position].id.hashCode().toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleMonthViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.simple_month_view,
            null
        )
        return SimpleMonthViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: SimpleMonthViewHolder, position: Int) {
        holder.bindViews(data[position])
    }

    fun loadNextYear() {
        val year = data.last().days[SimpleConstant.MAGIC_INDEX].plusYears(1).year
        for (index in 1..12) {
            data.add(
                SimpleMonthData(
                    year = year,
                    month = LocalDateUtil.getMonth(index),
                    days = LocalDateUtil.days(
                        year,
                        index
                    ).toMutableList()
                )
            )
            notifyItemInserted(data.size)
        }
    }

    fun loadPreviousYear() {
        val year = data[0].days[SimpleConstant.MAGIC_INDEX].minusYears(1).year
        for (index in 12 downTo 1) {
            data.add(
                0,
                SimpleMonthData(
                    year = year,
                    month = LocalDateUtil.getMonth(index),
                    days = LocalDateUtil.days(
                        year,
                        index
                    ).toMutableList()
                )
            )
            notifyItemInserted(0)
        }
    }
}
