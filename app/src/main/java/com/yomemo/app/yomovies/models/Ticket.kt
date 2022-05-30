package com.yomemo.yomovies.models

import java.util.*

class Ticket(
    var document_id: String? = "",
    val movie_title: String? = "",
    val ticket_id: String? = "",
    val date: Date? = Date(),
)