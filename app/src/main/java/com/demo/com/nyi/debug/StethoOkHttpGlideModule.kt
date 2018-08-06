package com.demo.com.nyi.debug

import android.content.Context

import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.GlideModule
import com.facebook.stetho.okhttp3.StethoInterceptor

import java.io.InputStream

import okhttp3.OkHttpClient

/**
 * GlideModule for making Glide perform all HTTP operations with OkHttp 3, and registering the
 * used OkHttp client with Stetho. In this way, all the image loads done with Glide can be
 * monitored with the Stetho network inspector.
 *
 * Requirements:
 * - Gradle: compile 'com.github.bumptech.glide:okhttp3-integration:1.4.0@jar'
 * - Manifest: <meta-data android:name="org.weibeld.flicks.util.StethoOkHttpGlideModule" android:value="GlideModule"></meta-data>
 * See http://stackoverflow.com/questions/36642052/network-inspection-in-stetho-with-glide-and-okhttp3
 *
 * Note that for only using Glide with OkHttp 3 (no Stetho integration) a predefined GlideModule
 * class can be used and no <meta-data> tag needs to be manually added to the Manifest. Just add
 * in Gradle:
 * compile 'com.github.bumptech.glide:okhttp3-integration:1.4.0@jar'
 * See https://github.com/bumptech/glide/wiki/Integration-Libraries
</meta-data> */
class StethoOkHttpGlideModule : GlideModule {

    override fun applyOptions(context: Context, builder: GlideBuilder) {}

    override fun registerComponents(context: Context, glide: Glide) {
        val client = OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())
                .build()
        glide.register(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory(client))
    }
}
