package com.uznewmax.viewpagerwithtablayout


import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.uznewmax.viewpagerwithtablayout.adapters.TabRecyclerAdapter
import com.uznewmax.viewpagerwithtablayout.databinding.ActivityMainBinding
import com.uznewmax.viewpagerwithtablayout.utils.ResourceStore.Companion.itemList
import com.uznewmax.viewpagerwithtablayout.utils.dpToPx


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        setupRecyclerViewWithTabs3()
    }

    private fun setupRecyclerViewWithTabs3() {
        val headerList = listOf("Taxi", "Business", "Delivery", "Business", "Delivery", "Taxi")
        val adapter = TabRecyclerAdapter(itemList, this)
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout3)
        val viewPager2 = findViewById<ViewPager2>(R.id.viewPager3)

        adapter.onItemClick {
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
        }

        viewPager2.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager2) { tab, pos ->
            tab.text = headerList[pos]
        }.attach()

        val tabs = tabLayout.getChildAt(0) as ViewGroup
        for (i in 0 until tabs.childCount) {
            val tab = tabs.getChildAt(i)
            val layoutParams = tab.layoutParams as LinearLayout.LayoutParams
            if (i == 0) layoutParams.marginStart = dpToPx(16)
            if (i == tabs.childCount - 1) layoutParams.marginEnd = dpToPx(48)
            tab.layoutParams = layoutParams
            tabLayout.requestLayout()
        }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                adapter.smoothScrollToPosition(0)
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })
    }
}