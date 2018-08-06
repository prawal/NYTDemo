package com.demo.com.nyi.decor

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View


class SpacesItemDecoration(private val mSpace: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        outRect.left = mSpace
        outRect.right = mSpace
        outRect.bottom = mSpace
        outRect.top = mSpace

    }
}
