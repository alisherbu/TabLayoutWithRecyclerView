package com.uznewmax.viewpagerwithtablayout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uznewmax.viewpagerwithtablayout.databinding.TariffItemBinding
import com.uznewmax.viewpagerwithtablayout.utils.Tariff

class InnerRecyclerAdapter(private val data: List<Tariff>) :
    RecyclerView.Adapter<InnerRecyclerAdapter.TabsItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InnerRecyclerAdapter.TabsItemViewHolder {
        val binding = TariffItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TabsItemViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: InnerRecyclerAdapter.TabsItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class TabsItemViewHolder(private val binding: TariffItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tariff: Tariff) {
            binding.apply {
                tvTariffName.text = tariff.name
            }
        }
    }
}
