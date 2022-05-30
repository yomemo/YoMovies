package com.yomemo.yomovies.models

data class Cast(
    val id: Int,
    var name: String = "",
    var original_name: String = "",
    var adult: Boolean = false,
    var profile_path: String = "",
)