package io.github.wawakaka.simplecalendar.lib.view.calendar

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import io.github.wawakaka.simplecalendar.lib.R
import io.github.wawakaka.simplecalendar.lib.data.*
import io.github.wawakaka.simplecalendar.lib.utils.EndlessRecyclerViewScrollListener
import io.github.wawakaka.simplecalendar.lib.utils.LocalDateUtil
import net.danlew.android.joda.JodaTimeAndroid
import org.joda.time.DateTimeZone
import org.joda.time.LocalDate

class SimpleCalendar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var adapter: SimpleCalendarAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener
    private var mode: Int = SimpleModes.SINGLE
    private var temp: Pair<SimpleMonthData, SimpleDateData>? = null

    init {
        JodaTimeAndroid.init(context)
        layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT
        )
        addView(recyclerView())
    }

    fun setMode(@SimpleMode mode: Int) {
        this.mode = mode
    }

    private fun recyclerView(): View {
        return RecyclerView(context).apply {
            id = R.id.calendar_recycler
            val params = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
            )
            layoutParams = params
            init(this)
        }
    }

    private fun init(recyclerView: RecyclerView) {
        linearLayoutManager = LinearLayoutManager(
            recyclerView.context, LinearLayoutManager.HORIZONTAL, false
        )
        scrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadNext(page: Int, totalItemsCount: Int, view: RecyclerView) {
                adapter.loadNextYear()
            }

            override fun onLoadPrevious(page: Int, totalItemsCount: Int, view: RecyclerView) {
                adapter.loadPreviousYear()
            }
        }
        adapter = SimpleCalendarAdapter(mode)
        recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = this@SimpleCalendar.adapter
            addOnScrollListener(scrollListener)
            PagerSnapHelper().attachToRecyclerView(this)
        }
        adapter.apply {
            initData(LocalDate.now(DateTimeZone.forID("Asia/Jakarta")))
            clickListener = { clickListenerData, monthData ->

                // clearing the old states
                temp?.let { pair ->
                    val oldIndex = data.indexOf(data.find { it.id == pair.first.id })
                    if (pair.second != clickListenerData)
                        pair.second.stateOnSingleMode = SimpleViewStates.NORMAL
                    notifyItemChanged(oldIndex)
                }

                // updating the states
                val index = data.indexOf(data.find { it.id == monthData.id })

                clickListenerData.stateOnSingleMode =
                    if (clickListenerData.stateOnSingleMode == SimpleViewStates.SELECTED) {
                        SimpleViewStates.NORMAL
                    } else {
                        SimpleViewStates.SELECTED
                    }

                // add the changes
                temp = Pair(monthData, clickListenerData)

                // update the adapter
                notifyItemChanged(index)
                Log.e("SimpleCalendar", "adapter.data : ${adapter.data}")
            }
            bindingDataListener = {
                scrollListener.disableLoading()
            }
        }

        scrollToInitialPosition()
    }

    private fun scrollToInitialPosition() {
        linearLayoutManager.scrollToPosition(LocalDateUtil.getCurrentMonthValue() - 1)
    }
}