package com.demo.com.nyi.api

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by pawan on 06/08/18.
 */

object ApiServiceSingleton {

    private var mInstance: ApiService? = null

    val instance: ApiService
        get() {
            if (mInstance == null) {
                mInstance = retrofit.create(ApiService::class.java)
            }
            return mInstance!!
        }

    private// Customise Gson instance
    // Append api-key parameter to every query
    // Enable Stetho network inspection
    // Create Retrofit instance
    val retrofit: Retrofit
        get() {
            var gson: Gson? = null
            try {
                gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            val apiKeyInterceptor = Interceptor{ chain ->
                var request = chain.request()
                val url = request.url().newBuilder().addQueryParameter("api-key", ApiService.API_KEY).build()
                request = request.newBuilder().url(url).build()
                chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(apiKeyInterceptor)
                    .addNetworkInterceptor(StethoInterceptor())
                    .build()
            return Retrofit.Builder()
                    .baseUrl(ApiService.API_BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson!!))
                    .build()
        }

}
