package com.demo.com.nyi.listner

import android.support.v7.widget.RecyclerView
import android.view.View

import com.demo.com.nyi.R


/**
 * Created by pawan on 06/08/18.
 */

/*
 * Emulate an "OnItemClickListener" for a RecyclerView.
 *
 * Usage:
 *
 * Create values/ids.xml:
 * <resources>
 *     <item name="item_click_support" type="id" />
 * </resources>
 *
 * In onCreate of activity:
 * RecyclerViewItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new RecyclerViewItemClickSupport.OnItemClickListener() {
 *    @Override
 *    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
 *        // ...
 *    }
 * });
 *
 * Source: http://www.littlerobots.nl/blog/Handle-Android-RecyclerView-Clicks/
 */
class RecyclerViewItemClickSupport private constructor(private val mRecyclerView: RecyclerView) {
    private var mOnItemClickListener: OnItemClickListener? = null
    private var mOnItemLongClickListener: OnItemLongClickListener? = null
    private val mOnClickListener = View.OnClickListener { v ->
        if (mOnItemClickListener != null) {
            val holder = mRecyclerView.getChildViewHolder(v)
            mOnItemClickListener!!.onItemClicked(mRecyclerView, holder.adapterPosition, v)
        }
    }
    private val mOnLongClickListener = View.OnLongClickListener { v ->
        if (mOnItemLongClickListener != null) {
            val holder = mRecyclerView.getChildViewHolder(v)
            return@OnLongClickListener mOnItemLongClickListener!!.onItemLongClicked(mRecyclerView, holder.adapterPosition, v)
        }
        false
    }
    private val mAttachListener = object : RecyclerView.OnChildAttachStateChangeListener {
        override fun onChildViewAttachedToWindow(view: View) {
            if (mOnItemClickListener != null) {
                view.setOnClickListener(mOnClickListener)
            }
            if (mOnItemLongClickListener != null) {
                view.setOnLongClickListener(mOnLongClickListener)
            }
        }

        override fun onChildViewDetachedFromWindow(view: View) {

        }
    }

    init {
        mRecyclerView.setTag(R.id.item_click_support, this)
        mRecyclerView.addOnChildAttachStateChangeListener(mAttachListener)
    }



    fun setOnItemLongClickListener(listener: OnItemLongClickListener): RecyclerViewItemClickSupport {
        mOnItemLongClickListener = listener
        return this
    }

    private fun detach(view: RecyclerView) {
        view.removeOnChildAttachStateChangeListener(mAttachListener)
        view.setTag(R.id.item_click_support, null)
    }

    interface OnItemClickListener {

        fun onItemClicked(recyclerView: RecyclerView, position: Int, v: View)
    }

    interface OnItemLongClickListener {

        fun onItemLongClicked(recyclerView: RecyclerView, position: Int, v: View): Boolean
    }

    companion object {

        fun addTo(view: RecyclerView): RecyclerViewItemClickSupport {
            var support: RecyclerViewItemClickSupport? = view.getTag(R.id.item_click_support) as RecyclerViewItemClickSupport
            if (support == null) {
                support = RecyclerViewItemClickSupport(view)
            }
            return support
        }

        fun removeFrom(view: RecyclerView): RecyclerViewItemClickSupport? {
            val support = view.getTag(R.id.item_click_support) as RecyclerViewItemClickSupport
            support?.detach(view)
            return support
        }
    }
}
