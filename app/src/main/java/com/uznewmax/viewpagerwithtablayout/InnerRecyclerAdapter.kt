package com.uznewmax.viewpagerwithtablayout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class InnerRecyclerAdapter(private val data: List<String>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TabsItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.tariff_item, parent, false)
        )
    }
    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as TabsItemViewHolder).tvTariffName.text = data[position]
    }

    class TabsItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTariffName: TextView = itemView.findViewById(R.id.tvTariffName)
    }
}