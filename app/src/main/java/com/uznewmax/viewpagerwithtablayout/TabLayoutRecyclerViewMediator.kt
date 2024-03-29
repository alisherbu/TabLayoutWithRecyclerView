package com.uznewmax.viewpagerwithtablayout

import android.util.TypedValue
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.uznewmax.viewpagerwithtablayout.utils.CenterLayoutManager
import com.uznewmax.viewpagerwithtablayout.utils.showLog

/**
 * Created by Shivansh ON 13/07/20.
 *
 * A mediator class used to link tabs in TabLayout to items in a RecyclerView. This can be used to map different sections of
 * a RecyclerView to a common header represented by a Tab in TabLayout.
 */
class TabLayoutRecyclerViewMediator(
    private val recyclerView: RecyclerView,
    var headerCount: Int,
    private val tabLayout: TabLayout,
    var mapHeaderPositionToItemPosition: (headerPosition: Int) -> Int?,
    var mapItemPositionToHeaderPosition: (itemPosition: Int) -> Int?,
    private val autoRefresh: Boolean = true,
    private val tabConfigurationStrategy: (tab: TabLayout.Tab, position: Int) -> Unit
) {

    private var isProgrammaticallyScrolled = false
    private var tabProgrammaticallySelected = true
    private var recyclerAdapterObserver: RecyclerView.AdapterDataObserver? = null
    private var onScrollListener: RecyclerView.OnScrollListener? = null
    private var tabSelectedListener: TabLayout.OnTabSelectedListener? = null

    private val smoothScroller: RecyclerView.SmoothScroller =
        object : LinearSmoothScroller(recyclerView.context) {
            override fun getVerticalSnapPreference(): Int {
                return SNAP_TO_START
            }

            override fun calculateDtToFit(
                viewStart: Int,
                viewEnd: Int,
                boxStart: Int,
                boxEnd: Int,
                snapPreference: Int
            ): Int {
                return boxStart - viewStart
            }
        }

    fun attach() {
        if (autoRefresh && recyclerView.adapter != null) {
            recyclerAdapterObserver = object : RecyclerView.AdapterDataObserver() {
                override fun onChanged() {
                    populateTabsLayout()
                }

                override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                    populateTabsLayout()
                }

                override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
                    populateTabsLayout()
                }

                override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                    populateTabsLayout()
                }

                override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
                    populateTabsLayout()
                }

                override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
                    populateTabsLayout()
                }
            }
            recyclerView.adapter!!.registerAdapterDataObserver(recyclerAdapterObserver as RecyclerView.AdapterDataObserver)
        }

        // Add onScrollListener to update tabs corresponding to recyclerView scroll
        onScrollListener = TabLayoutOnPageChangeCallback()
        recyclerView.addOnScrollListener(onScrollListener as TabLayoutOnPageChangeCallback)

        // Add tab selected listener to update recycler view when a tab is selected
        tabSelectedListener = object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                showLog("tabProgrammaticallySelected=$tabProgrammaticallySelected")
                if (!tabProgrammaticallySelected) {
                    val itemPosition = tab?.position?.let { mapHeaderPositionToItemPosition(it) }
                    showLog("onTabSelected: itemPosition=$itemPosition")
                    if (itemPosition != null) {
                        smoothScroller.targetPosition = itemPosition
                        isProgrammaticallyScrolled = true
                        recyclerView.layoutManager?.startSmoothScroll(smoothScroller)
                    }
                } else tabProgrammaticallySelected = false
                tab?.setTextSize(14f, R.color.black)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                tabProgrammaticallySelected = false
                tab?.setTextSize(14f, R.color.black)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.setTextSize(13f, R.color.primaryBlack)
            }
        }
        tabLayout.addOnTabSelectedListener(tabSelectedListener as TabLayout.OnTabSelectedListener)
        populateTabsLayout()
    }

    fun TabLayout.Tab.setTextSize(size: Float, textColor: Int) {
        val textView = customView?.findViewById<TextView>(R.id.tvHeader)
        textView?.let { tv ->
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, size)
            tv.setTextColor(textColor)
        }
    }

    fun detach() {
        onScrollListener?.let { recyclerView.removeOnScrollListener(it) }
        tabSelectedListener?.let { tabLayout.removeOnTabSelectedListener(it) }
        recyclerAdapterObserver?.let { recyclerView.adapter?.unregisterAdapterDataObserver(it) }

        onScrollListener = null
        tabSelectedListener = null
        recyclerAdapterObserver = null
    }

    private fun populateTabsLayout() {
        tabLayout.removeAllTabs()
        for (index in 0 until headerCount) {
            val newTab = tabLayout.newTab()
            newTab.setCustomView(R.layout.item_tab_header)
            tabConfigurationStrategy.invoke(newTab, index)
            tabLayout.addTab(newTab, false)
        }
    }

    private inner class TabLayoutOnPageChangeCallback : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            if (!isProgrammaticallyScrolled && recyclerView.layoutManager is CenterLayoutManager) {
                val visibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                mapItemPositionToHeaderPosition(visibleItemPosition)?.let { selectTab(it) }
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                isProgrammaticallyScrolled = false
                val visibleItemHeader =
                    mapItemPositionToHeaderPosition((recyclerView.layoutManager as CenterLayoutManager).findFirstVisibleItemPosition())
                if (visibleItemHeader != null && tabLayout.selectedTabPosition != visibleItemHeader)
                    selectTab(visibleItemHeader)
            }
        }

        private fun selectTab(position: Int) {
            tabProgrammaticallySelected = true
            tabLayout.setScrollPosition(position, 0f, true, true)
            tabLayout.selectTab(tabLayout.getTabAt(position), true)
        }
    }
}