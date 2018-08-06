package com.demo.com.nyi.activity

import android.app.Activity
import android.app.ProgressDialog

import android.content.Intent

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import android.support.v7.widget.StaggeredGridLayoutManager
import com.demo.com.nyi.R
import com.demo.com.nyi.activity.MainActivity.FOO.EXTRA_ARTICLE_URL

import com.demo.com.nyi.adapter.ArticleAdapter
import com.demo.com.nyi.api.ApiServiceSingleton
import com.demo.com.nyi.listner.EndlessRecyclerViewScrollListener
import com.demo.com.nyi.listner.RecyclerViewItemClickSupport
import com.demo.com.nyi.model.Example
import com.demo.com.nyi.model.Result
import com.demo.com.nyi.util.Util
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {
    object FOO {
        val EXTRA_ARTICLE_URL: String? = "ArticleUrl"
    }

    // General
    internal var mActivity: Activity? = null


    // RecyclerView
    internal var mArticles: ArrayList<Result>? = null
    internal var mAdapter: ArticleAdapter? = null
    internal var mProgressDialog: ProgressDialog? = null
    internal var mScrollListener: EndlessRecyclerViewScrollListener? = null

    // API requests
    internal var mQuery: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mActivity = this@MainActivity
        mArticles = ArrayList<Result>()
        mProgressDialog = setupProgressDialog()

        query()


    }


    // TODO: check if using ProgressBar is better
    private fun setupProgressDialog(): ProgressDialog {
        val p = ProgressDialog(mActivity)
        p.isIndeterminate = true
        p.setMessage("Loading...")
        return p
    }


    private fun query() {
        mProgressDialog!!.show()
        val call = ApiServiceSingleton.instance.query()
        call.enqueue(object : Callback<Example> {
            override fun onResponse(call: Call<Example>, response: Response<Example>) {
                mArticles = response.body().results as ArrayList<Result>
                mAdapter = ArticleAdapter(mArticles!!, this@MainActivity)
                recyclerView.setAdapter(mAdapter)
                val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
                recyclerView.setLayoutManager(layoutManager)
                mProgressDialog!!.dismiss()
            }

            override fun onFailure(call: Call<Example>, t: Throwable) {
                mProgressDialog!!.dismiss()
                fail(t)
            }

            private fun fail(t: Throwable) {
                Util.toastLong(mActivity, "Query failed: " + t.javaClass.simpleName)
                t.printStackTrace()
            }
        })
    }
}
