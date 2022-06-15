package com.uznewmax.viewpagerwithtablayout.adapters

import androidx.recyclerview.widget.DiffUtil
import com.uznewmax.viewpagerwithtablayout.utils.Tariff

class TariffDiffUtil : DiffUtil.ItemCallback<Tariff>() {

    override fun areItemsTheSame(oldItem: Tariff, newItem: Tariff): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Tariff, newItem: Tariff): Boolean {
        return oldItem == newItem
    }
}