package com.uznewmax.viewpagerwithtablayout

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.uznewmax.viewpagerwithtablayout.databinding.ActivityMainBinding
import com.uznewmax.viewpagerwithtablayout.databinding.ItemTabHeaderBinding
import com.uznewmax.viewpagerwithtablayout.utils.ResourceStore
import com.uznewmax.viewpagerwithtablayout.utils.ResourceStore.Companion.itemList

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
        val headerList = listOf("Asia", "Africa", "Europe")

        TabRecycler()
            .setRecyclerView(recyclerView)
            .setTabLayout(tabLayout)
            .setHeaders(headerList)
            .setData(itemList)
            .attach()
    }
}