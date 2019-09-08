package io.github.wawakaka.simplecalendar.lib

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.day.view.*
import kotlinx.android.synthetic.main.month.view.*
import java.util.*

internal class SimpleCalendarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val viewList: MutableList<View> = mutableListOf()

    fun bindViews(dates: MutableList<Date>) {
        setDays(dates)
    }

    private fun setDays(dates: MutableList<Date>) {
        dates.forEach {
            createView(it)
        }

//            this is hack to fill the calendar
        while (viewList.size < 42) {
            createView(Date())
        }

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

                when (getRow(position)) {
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
                            viewList[getTopItemPosition(position)].id,
                            ConstraintSet.BOTTOM
                        )
                    }
                }

                when (getCoulomb(position)) {
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
            Log.e("createView", "$date")
        }
        view.id = ViewIdGenerator.generateViewId()
        viewList.add(view)
    }

    private fun getCoulomb(position: Int): Int {
        return when {
            position % 7 == 0 -> 3
            position % 7 == 1 -> 1
            else -> 2
        }
    }

    private fun getRow(position: Int): Int {
        return when {
            (position.toFloat() / 7.0f) <= 1.0f -> 1
            (position.toFloat() / 7.0f) <= 2.0f -> 2
            (position.toFloat() / 7.0f) <= 3.0f -> 3
            (position.toFloat() / 7.0f) <= 4.0f -> 4
            (position.toFloat() / 7.0f) <= 5.0f -> 5
            else -> 6
        }
    }

    private fun getTopItemPosition(position: Int): Int {
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