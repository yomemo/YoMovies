package com.yomemo.yomovies.models

data class Person(
    var page: Int,
    var personDetail: PersonDetail? = null,
    val profile_path: String?,
    val adult: Boolean,
    val id: Long,
    val name: String,
    val popularity: Float,
)