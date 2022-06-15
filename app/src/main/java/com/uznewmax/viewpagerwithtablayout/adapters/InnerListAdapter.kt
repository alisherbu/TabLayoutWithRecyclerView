package com.uznewmax.viewpagerwithtablayout.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.uznewmax.viewpagerwithtablayout.databinding.TariffItemBinding
import com.uznewmax.viewpagerwithtablayout.utils.Tariff
import com.uznewmax.viewpagerwithtablayout.utils.changeFormat

/**
 * Created by Alisher Kazakbaev on 14.06.2022.
 */
class InnerListAdapter(private val adapter: TabRecyclerAdapter) :
    ListAdapter<Tariff, InnerListAdapter.InnerItemViewHolder>(TariffDiffUtil()) {

    inner class InnerItemViewHolder(private val binding: TariffItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tariff: Tariff) {
            binding.apply {
                tvTariffName.text = tariff.name
                tvEta.text = "${tariff.eta.toString()} min"
                tvPrice.text = tariff.total_price?.changeFormat()
                root.setOnClickListener {
                    adapter.onClick(tariff)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerItemViewHolder {
        val binding = TariffItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InnerItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InnerItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}