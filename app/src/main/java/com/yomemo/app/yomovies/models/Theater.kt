package com.yomemo.yomovies.models

class Theater(
    var document_id: String? = "",
    val name: String? = "",
    val descriptions: String? = "",
    val poster_path: String? = "",
    val backdrop_path: String? = "",
    val location: String? = "0.0,0.0",
)