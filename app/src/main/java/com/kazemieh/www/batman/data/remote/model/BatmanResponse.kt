package com.kazemieh.www.batman.data.remote.model

data class BatmanResponse(
    val Response: String,
    val Search: List<MoviesResponse>,
    val totalResults: String
)