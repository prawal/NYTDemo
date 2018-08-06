package com.demo.com.nyi.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.support.v4.content.ContextCompat.startActivities
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.demo.com.nyi.R
import com.demo.com.nyi.R.id.container
import com.demo.com.nyi.activity.DetailActivity
import com.demo.com.nyi.activity.MainActivity
import com.demo.com.nyi.model.Result
import com.demo.com.nyi.util.MyDate

import java.util.ArrayList


/**
 * Created by Pawan on 06/08/18.
 */
class ArticleAdapter(private val mData: ArrayList<Result>, private val mContext: Context) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    // Called when a new view for an item must be created. This method does not return the view of
    // the item, but a ViewHolder, which holds references to all the elements of the view.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // The view for the item
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        // Create a ViewHolder for this view and return it
        return ViewHolder(itemView)
    }

    // Populate the elements of the passed view (represented by the ViewHolder) with the data of
    // the item at the specified position.
    override fun onBindViewHolder(vh: ViewHolder, position: Int) {
        val article = mData[position]
        vh.tvTitle.text = getSafeString(article.title)
        vh.tvTitle.setTypeface(null, Typeface.BOLD_ITALIC)
        vh.tvByLine.text = getSafeString(article.byline)
        if (article.publishedDate != null) {
            vh.tvDate.visibility = View.VISIBLE
            val date = MyDate(article.publishedDate)
            vh.tvDate.text = date.format1()
        } else
            vh.tvDate.visibility = View.GONE

        vh.container.setOnClickListener({
            val intent = Intent(mContext, DetailActivity::class.java)
            intent.putExtra(MainActivity.FOO.EXTRA_ARTICLE_URL, mData!![position.toInt()].url)
            mContext.startActivity(intent)
        })
        val thumbUrl = article.media!![0].mediaMetadata!![0].url

        if (!thumbUrl!!.isEmpty())
        // TODO: Glide seems to not cache most of these images but load them from the URL each time
            Glide.with(mContext)
                    .load(thumbUrl)
                    // Save original image in cache (less fetching from server)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .into(vh.ivThumb)

    }

    override fun getItemCount(): Int {
        return mData.size
    }

    private fun getSafeString(str: String?): String {
        return str ?: ""
    }

    fun clearArticles() {
        mData.clear()
        notifyItemRangeRemoved(0, itemCount)
    }


    inner class ViewHolder// Create a viewHolder for the passed view (item view)
    (view: View) : RecyclerView.ViewHolder(view) {
        var ivThumb: ImageView
        var tvDate: TextView
        var tvTitle: TextView
        var tvByLine: TextView
        var container: CardView

        init {
            ivThumb = view.findViewById<View>(R.id.ivThumb) as ImageView
            tvDate = view.findViewById<View>(R.id.tvDate) as TextView
            tvTitle = view.findViewById<View>(R.id.tvTitle) as TextView
            tvByLine = view.findViewById<View>(R.id.tvByLine) as TextView
            container = view.findViewById<View>(R.id.container) as CardView

        }
    }

}
