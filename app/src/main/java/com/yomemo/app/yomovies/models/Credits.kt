package com.yomemo.yomovies.models

data class Credits(
    val id: Int,
    var cast: ArrayList<Cast> = arrayListOf(),
    var crew: ArrayList<Crew> = arrayListOf(),
)