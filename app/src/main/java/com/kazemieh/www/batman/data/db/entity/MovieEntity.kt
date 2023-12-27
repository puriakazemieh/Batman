package com.kazemieh.www.batman.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.kazemieh.www.batman.data.db.BatmanRatingConverter

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    val imdbID: String,
    val actors: String? = null,
    val awards: String? = null,
    val boxOffice: String? = null,
    val country: String? = null,
    val dvd: String? = null,
    val director: String? = null,
    val genre: String? = null,
    val language: String? = null,
    val metaScore: String? = null,
    val plot: String? = null,
    val poster: String? = null,
    val production: String? = null,
    val rated: String? = null,
    @TypeConverters(BatmanRatingConverter::class)
    val ratings: List<DbRating>? = null,
    val released: String? = null,
    val response: String? = null,
    val runtime: String? = null,
    val title: String? = null,
    val type: String? = null,
    val website: String? = null,
    val writer: String? = null,
    val year: String? = null,
    val imdbRating: String? = null,
    val imdbVotes: String? = null
)