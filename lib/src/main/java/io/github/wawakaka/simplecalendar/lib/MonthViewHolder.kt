package io.github.wawakaka.simplecalendar.lib

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import io.github.wawakaka.simplecalendar.lib.MonthPresenter.getNextMonthDate
import io.github.wawakaka.simplecalendar.lib.MonthPresenter.getPreviousMonthDate
import io.github.wawakaka.simplecalendar.lib.SimpleConstant.MAX_NUMBER_OF_DATE
import kotlinx.android.synthetic.main.day.view.*
import kotlinx.android.synthetic.main.month.view.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

internal class MonthViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val dateInAMonthList: MutableList<View> = mutableListOf()
    private val presenter by lazy { MonthPresenter }

    fun bindViews(dates: MutableList<LocalDate>) {
        dateInAMonthList.clear()
        Log.e("dateInAMonthList.size", "------------------------")
        Log.e("dateInAMonthList.size", "${dateInAMonthList.size}")
        setDays(dates)
        Log.e("dateInAMonthList.size", "${dateInAMonthList.size}")
        Log.e("dateInAMonthList.size", "------------------------")
    }

    private fun setDays(dates: MutableList<LocalDate>) {
        val lastMonthDates = getPreviousMonthDate(dates.first())
        val nextMonthDates =
            getNextMonthDate(
                date = dates.last(),
                currentListSize = dates.size + lastMonthDates.size
            )

        for (index in 0 until lastMonthDates.size) {
            createView(lastMonthDates[index])
        }

        dates.forEach {
            createView(it)
        }

        nextMonthDates.forEach {
            createView(it)
        }

        check(dateInAMonthList.size >= MAX_NUMBER_OF_DATE) { "Something wrong with the code check your logic again, view size ${dateInAMonthList.size}" }

        dateInAMonthList.forEach {
            if (it.parent != null) {
                Log.e("parent", "not null ${it.id}")
                itemView.root_month_view.removeView(it)
            } else {
                itemView.root_month_view.addView(it)
            }
        }

        setConstraint()
    }

    private fun setConstraint() {
        for (index in 0 until dateInAMonthList.size) {

            val position = index + 1
            val view = dateInAMonthList[index]
            val constraintSet = ConstraintSet()

//                order of this set is important
            constraintSet.run {
                constrainWidth(view.id, ConstraintLayout.LayoutParams.MATCH_CONSTRAINT)
                constrainHeight(view.id, ConstraintLayout.LayoutParams.MATCH_CONSTRAINT)

                clone(itemView.root_month_view)

                when (presenter.getRow(position)) {
                    1 -> {
                        connect(
                            view.id,
                            ConstraintSet.TOP,
                            ConstraintSet.PARENT_ID,
                            ConstraintSet.TOP
                        )
                    }
                    else -> {
                        connect(
                            view.id,
                            ConstraintSet.TOP,
                            dateInAMonthList[presenter.getTopItemPosition(position)].id,
                            ConstraintSet.BOTTOM
                        )
                    }
                }

                when (presenter.getCoulomb(position)) {
                    1 -> {
                        connect(
                            view.id,
                            ConstraintSet.START,
                            ConstraintSet.PARENT_ID,
                            ConstraintSet.START
                        )
                        connect(
                            view.id,
                            ConstraintSet.END,
                            dateInAMonthList[index + 1].id,
                            ConstraintSet.START
                        )
                    }
                    2 -> {
                        connect(
                            view.id,
                            ConstraintSet.END,
                            dateInAMonthList[index + 1].id,
                            ConstraintSet.START
                        )
                        connect(
                            view.id,
                            ConstraintSet.START,
                            dateInAMonthList[index - 1].id,
                            ConstraintSet.END
                        )
                    }
                    else -> {
                        connect(
                            view.id,
                            ConstraintSet.END,
                            ConstraintSet.PARENT_ID,
                            ConstraintSet.END
                        )
                        connect(
                            view.id,
                            ConstraintSet.START,
                            dateInAMonthList[index - 1].id,
                            ConstraintSet.END
                        )
                    }
                }
                applyTo(itemView.root_month_view)
            }
        }
    }

    private fun createView(date: LocalDate) {
        val view = LayoutInflater.from(itemView.context).inflate(R.layout.day, null)
        view.text_day.text = date.format(DateTimeFormatter.ofPattern("dd"))
        view.container_day.setOnClickListener {
            //            todo do something
            Toast.makeText(view.context, "Date clicked $date", Toast.LENGTH_LONG).show()
        }
        view.id = ViewIdGenerator.generateViewId()
        dateInAMonthList.add(view)
    }

}