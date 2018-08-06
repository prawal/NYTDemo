package com.demo.com.nyi.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Result {

    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("adx_keywords")
    @Expose
    var adxKeywords: String? = null
    @SerializedName("column")
    @Expose
    var column: Any? = null
    @SerializedName("section")
    @Expose
    var section: String? = null
    @SerializedName("byline")
    @Expose
    var byline: String? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("abstract")
    @Expose
    var abstract: String? = null
    @SerializedName("published_date")
    @Expose
    var publishedDate: String? = null
    @SerializedName("source")
    @Expose
    var source: String? = null
    @SerializedName("id")
    @Expose
    var id: Long? = null
    @SerializedName("asset_id")
    @Expose
    var assetId: Long? = null
    @SerializedName("views")
    @Expose
    var views: Int? = null


    @SerializedName("media")
    @Expose
    var media: List<Medium>? = null

}