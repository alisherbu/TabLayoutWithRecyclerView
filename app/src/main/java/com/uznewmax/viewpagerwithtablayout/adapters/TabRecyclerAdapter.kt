package com.uznewmax.viewpagerwithtablayout.adapters

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.uznewmax.viewpagerwithtablayout.databinding.ItemRvBinding
import com.uznewmax.viewpagerwithtablayout.utils.Tariff
import com.uznewmax.viewpagerwithtablayout.utils.dpToPx


class TabRecyclerAdapter(private val data: List<List<Tariff>>, val lifecycleOwner: LifecycleOwner) :
    RecyclerView.Adapter<TabRecyclerAdapter.TabsItemViewHolder>() {
    val scrolledPosition = MutableLiveData<Int>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TabsItemViewHolder {
        val binding = ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
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
                    state.itemCount - 1 -> {
                        outRect.right = dpToPx(16)
                    }
                }
            }

        }
        )
        return TabsItemViewHolder(binding)
    }

    var onClick: (tariff: Tariff) -> Unit = {}
    fun onItemClick(onClick: (tariff: Tariff) -> Unit) {
        this.onClick = onClick
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: TabsItemViewHolder, position: Int) {
        holder.bind(data[position])
    }


    inner class TabsItemViewHolder(private val binding: ItemRvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tariffs: List<Tariff>) {
            binding.apply {
                val adapter = InnerListAdapter(this@TabRecyclerAdapter)
                recyclerView.adapter = adapter
                scrolledPosition.observe(lifecycleOwner) {
                    recyclerView.smoothScrollToPosition(it)
                }
                adapter.submitList(tariffs)
            }
        }
    }


    fun smoothScrollToPosition(position: Int) {
        scrolledPosition.postValue(position)
    }
}