package com.kazemieh.www.batman.data.db.entity

import com.kazemieh.www.batman.data.remote.model.RatingResponse
import com.kazemieh.www.batman.domin.model.Rating

data class DbRating(
    val Source: String,
    val Value: String
)

fun DbRating.toRating()= Rating(Source, Value)