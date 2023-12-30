package com.kazemieh.www.batman.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kazemieh.www.batman.data.db.entity.DbRating
import com.kazemieh.www.batman.data.db.entity.MovieEntity
import com.kazemieh.www.batman.domin.model.AllMoves
import com.kazemieh.www.batman.domin.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllMovie(item: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(item: MovieEntity)

    @Query(
        "UPDATE movie SET " +
                " actors=:actors, " +
                " awards=:awards, " +
                " boxOffice=:boxOffice, " +
                " country=:country, " +
                " dvd=:dvd, " +
                " director=:director, " +
                " genre=:genre, " +
                " language=:language, " +
                " metaScore=:metaScore, " +
                " plot=:plot, " +
                " poster=:poster, " +
                " production=:production, " +
                " rated=:rated, " +
                " ratings=:ratings, " +
                " released=:released, " +
                " response=:response, " +
                " runtime=:runtime, " +
                " title=:title, " +
                " type=:type, " +
                " website=:website, " +
                " writer=:writer, " +
                " year=:year, " +
                " imdbRating=:imdbRating, " +
                " imdbVotes=:imdbVotes " +
                " WHERE imdbID=:imdbID "
    )
    fun updateMovie(
        imdbID: String,
        actors: String? = null,
        awards: String? = null,
        boxOffice: String? = null,
        country: String? = null,
        dvd: String? = null,
        director: String? = null,
        genre: String? = null,
        language: String? = null,
        metaScore: String? = null,
        plot: String? = null,
        poster: String? = null,
        production: String? = null,
        rated: String? = null,
        ratings: List<DbRating>? = null,
        released: String? = null,
        response: String? = null,
        runtime: String? = null,
        title: String? = null,
        type: String? = null,
        website: String? = null,
        writer: String? = null,
        year: String? = null,
        imdbRating: String? = null,
        imdbVotes: String? = null,
    )


    @Query("select * from movie ")
    fun getAllMovie(): Flow<List<AllMoves>>

    @Query("select * from movie where imdbID=:id")
    fun getMovie(id: String): Flow<MovieEntity>

    @Delete
    suspend fun removeMovie(movie: MovieEntity)


}