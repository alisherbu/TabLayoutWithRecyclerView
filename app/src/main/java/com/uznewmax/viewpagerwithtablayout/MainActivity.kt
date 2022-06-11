package com.uznewmax.viewpagerwithtablayout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.uznewmax.viewpagerwithtablayout.databinding.ActivityMainBinding
import com.uznewmax.viewpagerwithtablayout.utils.HorizontalSpaceItemDecoration
import com.uznewmax.viewpagerwithtablayout.utils.ResourceStore.Companion.itemList
import com.uznewmax.viewpagerwithtablayout.utils.dpToPx

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        setupRecyclerViewWithTabs()
    }

    private fun setupRecyclerViewWithTabs() {
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
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
            .setData(itemList)
            .attach()
    }
}