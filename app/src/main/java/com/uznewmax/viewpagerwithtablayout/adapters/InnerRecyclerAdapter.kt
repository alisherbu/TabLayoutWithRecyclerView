package com.uznewmax.viewpagerwithtablayout.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uznewmax.viewpagerwithtablayout.databinding.TariffItemBinding
import com.uznewmax.viewpagerwithtablayout.utils.Tariff
import com.uznewmax.viewpagerwithtablayout.utils.changeFormat

class InnerRecyclerAdapter(
    private val adapter: TabRecyclerAdapter,
    private val data: List<Tariff>
) :
    RecyclerView.Adapter<InnerRecyclerAdapter.InnerItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InnerRecyclerAdapter.InnerItemViewHolder {
        val binding = TariffItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InnerItemViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: InnerRecyclerAdapter.InnerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

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
}