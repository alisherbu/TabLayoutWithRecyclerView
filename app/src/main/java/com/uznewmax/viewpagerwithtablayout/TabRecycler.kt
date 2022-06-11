package com.uznewmax.viewpagerwithtablayout

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.uznewmax.viewpagerwithtablayout.utils.Tariff
import com.uznewmax.viewpagerwithtablayout.utils.showLog

/**
 * Created by Alisher Kazakbaev on 11.06.2022.
 */

class TabRecycler {
    private var tabLayout: TabLayout? = null
    private var recyclerView: RecyclerView? = null
    private var data: List<List<Any>>? = null
    private var headerList: List<String>? = null
    private var isHeaderList: ArrayList<Boolean> = arrayListOf()
    private var headerMap: Map<Int, Int>? = null
    private var itemMap: Map<Int, Int>? = null

    fun setTabLayout(tabLayout: TabLayout): TabRecycler {
        this.tabLayout = tabLayout
        return this
    }

    private fun getTabLayout(): TabLayout {
        if (tabLayout != null) return tabLayout as TabLayout
        else throw NullPointerException("TabLayout is null")
    }

    fun setRecyclerView(recyclerView: RecyclerView): TabRecycler {
        this.recyclerView = recyclerView
        return this
    }

    private fun getRecyclerView(): RecyclerView {
        if (recyclerView != null) return recyclerView as RecyclerView
        else throw NullPointerException("RecyclerView is null")
    }

    fun setHeaders(headers: List<String>): TabRecycler {
        headerList = headers
        isHeaderList.clear()
        repeat(headers.size) { this.isHeaderList.add(true) }
        headerMap = setHeaderMap(isHeaderList)
        itemMap = setItemMap(isHeaderList)
        return this
    }

    fun setData(data: List<List<Tariff>>): TabRecycler {
        this.data = data
        recyclerView?.adapter = TabRecyclerAdapter(data)
        return this
    }

    fun attach() {
        TabLayoutRecyclerViewMediator(
            getRecyclerView(),
            headerList!!.size,
            getTabLayout(),
            { position: Int -> itemMap!![position] },
            { position: Int -> headerMap!![position] }
        ) { tab, position ->
            val tvHeader = tab.customView?.findViewById<TextView>(R.id.tvHeader)
            tvHeader?.text = headerList!![position]
        }.attach()
    }

    private fun setItemMap(isHeader: List<Boolean>): Map<Int, Int> {
        val itemMap = mutableMapOf<Int, Int>()
        var headerIndex = -1
        for ((itemCount, hIndex) in isHeader.indices.withIndex()) {
            if (isHeader[hIndex]) {
                headerIndex++
                itemMap[headerIndex] = itemCount
            }
        }
        return itemMap
    }

    private fun setHeaderMap(headerList: List<Boolean>): Map<Int, Int> {
        var currentHeader = -1
        val headerMap = mutableMapOf<Int, Int>()
        for (index in headerList.indices) {
            if (headerList[index]) currentHeader++
            headerMap[index] = currentHeader
        }
        return headerMap
    }


}