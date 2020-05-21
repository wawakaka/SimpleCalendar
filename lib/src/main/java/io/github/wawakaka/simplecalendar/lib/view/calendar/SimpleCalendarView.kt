package io.github.wawakaka.simplecalendar.lib.view.calendar

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.threetenabp.AndroidThreeTen
import io.github.wawakaka.simplecalendar.lib.R
import io.github.wawakaka.simplecalendar.lib.data.SimpleDateData
import io.github.wawakaka.simplecalendar.lib.data.SimpleMode
import io.github.wawakaka.simplecalendar.lib.data.SimpleModes
import io.github.wawakaka.simplecalendar.lib.utils.EndlessRecyclerViewScrollListener
import io.github.wawakaka.simplecalendar.lib.utils.LocalDateUtil
import io.github.wawakaka.simplecalendar.lib.view.calendar.month.SimpleMonthAdapter
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId

class SimpleCalendarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var adapter: SimpleMonthAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener
    private var mode: Int = SimpleModes.NORMAL

    init {
        AndroidThreeTen.init(context)
        layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        addView(recyclerView())
    }

    fun setClickListener(clickListener: ((SimpleDateData) -> Unit)? = null) {
        adapter.clickListener = clickListener
    }

    fun setMode(@SimpleMode mode: Int) {
        this.mode = mode
    }

    private fun recyclerView(): View {
        return RecyclerView(context).apply {
            id = R.id.calendar_view_recycler
            val params = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
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
        adapter = SimpleMonthAdapter(mode)
        recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = this@SimpleCalendarView.adapter
            addOnScrollListener(scrollListener)
            PagerSnapHelper().attachToRecyclerView(this)
        }
        adapter.initData(LocalDate.now(ZoneId.systemDefault()))
        scrollToInitialPosition()
    }

    private fun scrollToInitialPosition() {
        linearLayoutManager.scrollToPosition(LocalDateUtil.getCurrentMonth().value)
    }
}