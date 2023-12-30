package com.kazemieh.www.batman.data.remote.model

import com.kazemieh.www.batman.data.db.entity.DbRating
import com.kazemieh.www.batman.data.db.entity.MovieEntity
import com.kazemieh.www.batman.domin.model.AllMoves
import com.kazemieh.www.batman.domin.model.Movie

data class MovieResponse(
    val imdbID: String,
    val Actors: String? = null,
    val Awards: String? = null,
    val BoxOffice: String? = null,
    val Country: String? = null,
    val DVD: String? = null,
    val Director: String? = null,
    val Genre: String? = null,
    val Language: String? = null,
    val Metascore: String? = null,
    val Plot: String? = null,
    val Poster: String? = null,
    val Production: String? = null,
    val Rated: String? = null,
    val Ratings: List<RatingResponse>? = null,
    val Released: String? = null,
    val Response: String? = null,
    val Runtime: String? = null,
    val Title: String? = null,
    val Type: String? = null,
    val Website: String? = null,
    val Writer: String? = null,
    val Year: String? = null,
    val imdbRating: String? = null,
    val imdbVotes: String? = null
)

fun MovieResponse.toMovieEntity() = MovieEntity(
    imdbID = imdbID ?: "",
    actors = Actors,
    awards = Awards,
    boxOffice = BoxOffice,
    country = Country,
    dvd = DVD,
    director = Director,
    genre = Genre,
    language = Language,
    metaScore = Metascore,
    plot = Plot,
    poster = Poster,
    production = Production,
    rated = Rated,
    ratings = Ratings?.map { it.toDpRating() },
    released = Released,
    response = Response,
    runtime = Runtime,
    title = Title,
    type = Type,
    website = Website,
    writer = Writer,
    year = Year,
    imdbRating = imdbRating,
    imdbVotes = imdbVotes,
)