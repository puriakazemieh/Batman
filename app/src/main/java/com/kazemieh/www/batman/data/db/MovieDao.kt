package com.kazemieh.www.batman.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kazemieh.www.batman.data.db.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllMovie(item: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(item: MovieEntity)

    @Query("select * from movie ")
    fun getAllMovie(): Flow<List<MovieEntity>>

   /* @Query(
        "update movie SET " +
                "Poster=:Poster, " +
                "Title=:Title, " +
                "Title=:Title, " +
                "Title=:Title, " +
                "Title=:Title, " +
                "Title=:Title, " +
                "Title=:Title, " +
                "Title=:Title, " +
                "Title=:Title, " +
                "Title=:Title, " +
                "Title=:Title, " +
                "Title=:Title  " +
                "WHERE imdbID = :id")
    fun getUpdateMove(
        id:Int,
        Poster: String,
        Title: String,
        Poster: String,
        Poster: String,
        Poster: String,
        Poster: String,
        Poster: String,
        Poster: String,
        Poster: String,
        Poster: String,
        Poster: String,
        Poster: String,
        Poster: String,
        Poster: String,
        Poster: String,
        Poster: String,
    )

    val Title: String,
    val Type: String,
    val Year: String,

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
    val Production: String? = null,
    val Rated: String? = null,
    @TypeConverters(BatmanRatingConverter::class)
    val Ratings: List<DbRating>? = null,
    val Released: String? = null,
    val Response: String? = null,
    val Runtime: String? = null,
    val Title: String? = null,
    val Type: String? = null,
    val Website: String? = null,
    val Writer: String? = null,
    val Year: String? = null,
    val imdbRating: String? = null,
    val imdbVotes: String? = null*/

    @Query("select * from movie where imdbID=:id")
    fun getMovie(id: String): Flow<MovieEntity>

    @Delete
    suspend fun removeMovie(movie: MovieEntity)


}