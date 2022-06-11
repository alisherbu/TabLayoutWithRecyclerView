package com.uznewmax.viewpagerwithtablayout.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HorizontalSpaceItemDecoration(
    private val spaceHeight: Int,
    private val startWidth: Int,
    private val endWidth: Int
) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
//        outRect.bottom = spaceHeight
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.left = startWidth
        }
        if (parent.getChildAdapterPosition(view) == 1){
            outRect.left = startWidth
        }

        if (parent.getChildAdapterPosition(view) == 2){
            outRect.left = startWidth
        }
        if (parent.getChildAdapterPosition(view) == state.itemCount - 1) {
            outRect.right = endWidth
        }
    }
}
