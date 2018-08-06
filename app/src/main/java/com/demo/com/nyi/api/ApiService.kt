package com.demo.com.nyi.api

import com.demo.com.nyi.model.Example
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by pawan on 06/08/18.
 */

interface ApiService {
    @GET("mostviewed/all-sections/7.json")
    fun query(): Call<Example>

    companion object {

        val API_KEY = "84440fbe844d4ddf9795de5c761ebdfa"
        val API_BASE_URL = "http://api.nytimes.com/svc/mostpopular/v2/"
        val API_IMAGE_BASE_URL = "http://www.nytimes.com/"
    }
}
