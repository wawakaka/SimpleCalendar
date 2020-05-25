package io.github.wawakaka.simplecalendar.lib.utils

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

internal abstract class EndlessRecyclerViewScrollListener : RecyclerView.OnScrollListener {
    companion object {
        private const val HIDE_THRESHOLD = 50
    }

    // The total number of items in the dataset after the last load
    private var previousTotalItemCount = 0

    // True if we are still waiting for the last set of data to load.
    private var loading = false

    // Sets the starting page index
    private val startingPageIndex = 1

    // Sets how many item being loaded in one page
    private var numberOfItemInOnePage = 12

    private var numberOfNonPaginatedItem = 0

    private var scrolledDistance = 0

    private var controlsVisible = true

    private var mLayoutManager: RecyclerView.LayoutManager

    constructor(
        layoutManager: LinearLayoutManager,
        numberOfItemInOnePage: Int = 12,
        numberOfNonPaginatedItem: Int = 0
    ) {
        this.mLayoutManager = layoutManager
        this.numberOfItemInOnePage = numberOfItemInOnePage
        this.numberOfNonPaginatedItem = numberOfNonPaginatedItem
    }

    constructor(
        layoutManager: GridLayoutManager,
        numberOfItemInOnePage: Int = 12,
        numberOfNonPaginatedItem: Int = 0
    ) {
        this.mLayoutManager = layoutManager
        this.numberOfItemInOnePage = numberOfItemInOnePage
        this.numberOfNonPaginatedItem = numberOfNonPaginatedItem
    }

    constructor(
        layoutManager: StaggeredGridLayoutManager,
        numberOfItemInOnePage: Int = 12,
        numberOfNonPaginatedItem: Int = 0
    ) {
        this.mLayoutManager = layoutManager
        this.numberOfItemInOnePage = numberOfItemInOnePage
        this.numberOfNonPaginatedItem = numberOfNonPaginatedItem
    }

    fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
        var maxSize = 0
        for (i in lastVisibleItemPositions.indices) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i]
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i]
            }
        }
        return maxSize
    }

    // This happens many times a second during a scroll, so be wary of the code you place here.
    // We are given a few useful parameters to help us work out if we need to load some more data,
    // but first we check if we are waiting for the previous load to finish.
    override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {
        var lastVisibleItemPosition = 0
        val totalItemCount = mLayoutManager.itemCount

        when (mLayoutManager) {
            is StaggeredGridLayoutManager -> {
                val lastVisibleItemPositions =
                    (mLayoutManager as StaggeredGridLayoutManager).findLastVisibleItemPositions(null)
                // get maximum element within the list
                lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions)
            }
            is GridLayoutManager -> lastVisibleItemPosition =
                (mLayoutManager as GridLayoutManager).findLastVisibleItemPosition()
            is LinearLayoutManager -> lastVisibleItemPosition =
                (mLayoutManager as LinearLayoutManager).findLastVisibleItemPosition()
        }

        if (scrolledDistance > HIDE_THRESHOLD && controlsVisible) {
            onScrollDown()
            controlsVisible = false
            scrolledDistance = 0
        } else if (scrolledDistance < -HIDE_THRESHOLD && !controlsVisible) {
            onScrollUp()
            controlsVisible = true
            scrolledDistance = 0
        }

        if ((controlsVisible && dy > 0) || (!controlsVisible && dy < 0)) {
            scrolledDistance += dy
        }

        // If the total item count is zero and the previous isn't, assume the
        // list is invalidated and should be reset back to initial state
        if (totalItemCount - numberOfNonPaginatedItem < previousTotalItemCount) {
            this.previousTotalItemCount = totalItemCount
            if (totalItemCount == 0) {
                this.loading = false
            }
        }

        // If it isn’t currently loading, we check to see if we have reach
        // the end of the list
        if (!loading && (lastVisibleItemPosition + 1).rem(numberOfItemInOnePage) == 0) {
            onLoadNext(totalItemCount, view)
            loading = true
        }
        if (!loading && lastVisibleItemPosition == 0) {
            onLoadPrevious(totalItemCount, view)
            loading = true
        }
    }

    // Call this method after loading more item
    fun disableLoading() {
        val totalItemCount = mLayoutManager.itemCount
        loading = false
        previousTotalItemCount = totalItemCount
    }

    // Call this method whenever performing new searches
    fun resetState() {
        previousTotalItemCount = 0
        loading = false
    }

    // Defines the process for actually loading more data based on page
    // page is invalid for right and left mode
    open fun onLoadNext(totalItemsCount: Int, view: RecyclerView) = Unit
    open fun onLoadPrevious(totalItemsCount: Int, view: RecyclerView) = Unit

    open fun onScrollDown() = Unit
    open fun onScrollUp() = Unit

}