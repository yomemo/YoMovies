package com.yomemo.yomovies.models

data class Review(
    val id: String,
    val author: String,
    val content: String,
    val url: String,
    val created_at: String,
//    val author_details: ArrayList<AuthorDetail>? = null,
)