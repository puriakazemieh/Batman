package com.kazemieh.www.batman.data.remote.model

import com.kazemieh.www.batman.data.db.entity.MovieEntity
import com.kazemieh.www.batman.domin.model.AllMoves

data class MoviesResponse(
    val Poster: String,
    val Title: String,
    val Type: String,
    val Year: String,
    val imdbID: String
)

fun MoviesResponse.toMovieEntity() =
    MovieEntity(title = Title, imdbID = imdbID, poster = Poster, type = Type, year = Year)