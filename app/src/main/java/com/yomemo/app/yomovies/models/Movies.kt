package com.yomemo.yomovies.models

import com.google.gson.annotations.SerializedName

class Movies {
    @SerializedName("page")
    var page: Int = 0
    @SerializedName("results")
    var results: MutableList<Movie> = mutableListOf()
    @SerializedName("total_results")
    var totalResults: Int = 0
    @SerializedName("total_pages")
    var totalPages: Int = 0
}