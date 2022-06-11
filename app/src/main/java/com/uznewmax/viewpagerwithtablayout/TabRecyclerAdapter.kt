package com.uznewmax.viewpagerwithtablayout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class TabRecyclerAdapter(private val data: List<List<String>>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TabsItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false)
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as TabsItemViewHolder).recyclerview.adapter = InnerRecyclerAdapter(data[position])
    }

    class TabsItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recyclerview: RecyclerView = itemView.findViewById(R.id.recyclerViewItem)
    }
}