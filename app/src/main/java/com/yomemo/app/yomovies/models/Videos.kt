package com.yomemo.yomovies.models

data class Videos(
    val id: Int,
    var results: ArrayList<Video> = arrayListOf(),
)