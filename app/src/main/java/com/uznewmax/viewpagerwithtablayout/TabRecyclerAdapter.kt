package com.uznewmax.viewpagerwithtablayout

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uznewmax.viewpagerwithtablayout.databinding.ItemRvBinding
import com.uznewmax.viewpagerwithtablayout.utils.Tariff
import com.uznewmax.viewpagerwithtablayout.utils.dpToPx


class TabRecyclerAdapter(private val data: List<List<Tariff>>) :
    RecyclerView.Adapter<TabRecyclerAdapter.TabsItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TabRecyclerAdapter.TabsItemViewHolder {
        val binding = ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TabsItemViewHolder(binding)
    }

    var onClick: (tariff: Tariff) -> Unit = {}
    fun onItemClick(onClick: (tariff: Tariff) -> Unit) {
        this.onClick = onClick
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: TabRecyclerAdapter.TabsItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class TabsItemViewHolder(private val binding: ItemRvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tariffs: List<Tariff>) {
            binding.apply {
                recyclerView.adapter = InnerRecyclerAdapter(this@TabRecyclerAdapter, tariffs)
                recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
                    override fun getItemOffsets(
                        outRect: Rect,
                        view: View,
                        parent: RecyclerView,
                        state: RecyclerView.State
                    ) {
                        when (parent.getChildAdapterPosition(view)) {
                            0 -> {
                                outRect.left = dpToPx(16)
                            }
                            state.itemCount - 1->{
                                outRect.right = dpToPx(16)
                            }
                        }
                    }
                }
                )

            }
        }
    }
}