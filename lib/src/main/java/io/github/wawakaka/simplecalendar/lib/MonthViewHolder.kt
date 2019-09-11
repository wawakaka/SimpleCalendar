package io.github.wawakaka.simplecalendar.lib

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import io.github.wawakaka.simplecalendar.lib.MonthPresenter.getLastMonthDate
import io.github.wawakaka.simplecalendar.lib.MonthPresenter.getNextMonthDate
import io.github.wawakaka.simplecalendar.lib.SimpleConstant.MAX_NUMBER_OF_DATE
import kotlinx.android.synthetic.main.day.view.*
import kotlinx.android.synthetic.main.month.view.*
import java.util.*

internal class MonthViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val viewList: MutableList<View> = mutableListOf()
    private val presenter by lazy { MonthPresenter }

    fun bindViews(dates: MutableList<Date>) {
        setDays(dates)
    }

    private fun setDays(dates: MutableList<Date>) {
        val lastMonthDates = getLastMonthDate(dates.first())
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

        check(viewList.size == MAX_NUMBER_OF_DATE) { "Something wrong with the code check your logic again" }

        viewList.forEach {
            itemView.root_view.addView(it)
        }

        setConstraint()
    }

    private fun setConstraint() {
        for (index in 0 until viewList.size) {

            val position = index + 1
            val view = viewList[index]
            val constraintSet = ConstraintSet()

//                order of this set is important
            constraintSet.run {
                constrainWidth(view.id, ConstraintLayout.LayoutParams.MATCH_CONSTRAINT)
                constrainHeight(view.id, ConstraintLayout.LayoutParams.MATCH_CONSTRAINT)

                clone(itemView.root_view)

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
                            viewList[presenter.getTopItemPosition(position)].id,
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
                            viewList[index + 1].id,
                            ConstraintSet.START
                        )
                    }
                    2 -> {
                        connect(
                            view.id,
                            ConstraintSet.END,
                            viewList[index + 1].id,
                            ConstraintSet.START
                        )
                        connect(
                            view.id,
                            ConstraintSet.START,
                            viewList[index - 1].id,
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
                            viewList[index - 1].id,
                            ConstraintSet.END
                        )
                    }
                }
                applyTo(itemView.root_view)
            }
        }
    }

    private fun createView(date: Date) {
        val view = LayoutInflater.from(itemView.context).inflate(R.layout.day, null)
        view.text_day.text = date.transformDate()
        view.container_day.setOnClickListener {
            //            todo do something
            Log.e("createView", "$date")
        }
        view.id = ViewIdGenerator.generateViewId()
        viewList.add(view)
    }

}