package com.kazemieh.www.batman.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kazemieh.www.batman.data.db.entity.MovieEntity
import com.kazemieh.www.batman.domin.model.AllMoves
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllMovie(item: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(item: MovieEntity)

    @Query("select * from movie ")
    fun getAllMovie(): Flow<List<AllMoves>>

    @Query("select * from movie where imdbID=:id")
    fun getMovie(id: String): Flow<MovieEntity>

    @Delete
    suspend fun removeMovie(movie: MovieEntity)


}