package com.demo.com.nyi.activity


import android.os.Bundle

import android.support.v7.app.AppCompatActivity

import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar

import com.demo.com.nyi.R

import com.demo.com.nyi.activity.MainActivity.FOO.EXTRA_ARTICLE_URL


class DetailActivity : AppCompatActivity() {


    internal var mUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Detail"
        mUrl = intent.getStringExtra(MainActivity.FOO.EXTRA_ARTICLE_URL)
        setContentView(R.layout.activity_detail)
        val progressBar = findViewById<View>(R.id.progressBar) as ProgressBar
        val webView = findViewById<View>(R.id.webView) as WebView

        // Display ProgressBar until page is loaded
        progressBar.isIndeterminate = true
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                progressBar.visibility = View.GONE
                webView.visibility = View.VISIBLE
                super.onPageFinished(view, url)
            }
        }
        webView.loadUrl(mUrl)
    }

    // Make navigation button in app bar behave like the device back button, i.e. return to previous
    // activity without calling onCreate, in this way, search results in MainActivity are maintained
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}
