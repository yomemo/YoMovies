package com.yomemo.yomovies.models

data class Reviews(
    val id: Int,
    var results: ArrayList<Review> = arrayListOf(),
)