package com.uznewmax.viewpagerwithtablayout

import android.os.Bundle
import android.util.TypedValue
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.uznewmax.viewpagerwithtablayout.databinding.ActivityMainBinding
import com.uznewmax.viewpagerwithtablayout.utils.HorizontalSpaceItemDecoration
import com.uznewmax.viewpagerwithtablayout.utils.ResourceStore.Companion.itemList
import com.uznewmax.viewpagerwithtablayout.utils.StartSnapHelper
import com.uznewmax.viewpagerwithtablayout.utils.dpToPx

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        setupRecyclerViewWithTabs1()
        setupRecyclerViewWithTabs2()
        setupRecyclerViewWithTabs3()
    }

    private fun setupRecyclerViewWithTabs1() {
        val adapter = TabRecyclerAdapter(itemList)
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout1)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view1)

        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
            HorizontalSpaceItemDecoration(
                dpToPx(0),
                dpToPx(16),
                dpToPx(48)
            )
        )
        val headerList = listOf("Taxi", "Business", "Delivery")

        TabRecycler()
            .setRecyclerView(recyclerView)
            .setTabLayout(tabLayout)
            .setHeaders(headerList)
            .attach()

        adapter.onItemClick {
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupRecyclerViewWithTabs2() {
        val adapter = TabRecyclerAdapter(itemList)
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout2)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view2)
        val snapHelper = StartSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)

        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
            HorizontalSpaceItemDecoration(
                dpToPx(0),
                dpToPx(16),
                dpToPx(48)
            )
        )
        val headerList = listOf("Taxi", "Business", "Delivery")

        TabRecycler()
            .setRecyclerView(recyclerView)
            .setTabLayout(tabLayout)
            .setHeaders(headerList)
            .attach()

        adapter.onItemClick {
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupRecyclerViewWithTabs3() {
        val headerList = listOf("Taxi", "Business", "Delivery")
        val adapter = TabRecyclerAdapter(itemList)
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout3)
        val viewPager3 = findViewById<ViewPager2>(R.id.viewPager3)
        viewPager3.adapter = adapter

        for (index in headerList.indices) {
            val newTab = tabLayout.newTab()
            newTab.setCustomView(R.layout.item_tab_header)
            val tvHeader = newTab.customView?.findViewById<TextView>(R.id.tvHeader)
            tvHeader?.text = headerList[index]
            tabLayout.addTab(newTab, false)
        }
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.setTextSize(14f, R.color.black)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.setTextSize(14f, R.color.black)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                tab?.setTextSize(13f, R.color.primaryBlack)
            }

        })

        TabLayoutMediator(tabLayout, viewPager3) { tab, pos ->
            tab.text = headerList[pos]
        }.attach()
    }

    fun TabLayout.Tab.setTextSize(size: Float, textColor: Int) {
        val textView = customView?.findViewById<TextView>(R.id.tvHeader)
        textView?.let { tv ->
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, size)
            tv.setTextColor(textColor)
        }
    }
}