package com.kazemieh.www.batman.data.remote.model

import com.kazemieh.www.batman.data.db.entity.DbRating
import com.kazemieh.www.batman.domin.model.Rating

data class RatingResponse(
    val Source: String,
    val Value: String
)

fun RatingResponse.toRating()= Rating(Source, Value)
fun RatingResponse.toDpRating()= DbRating(Source, Value)