package com.kazemieh.www.batman.domin

import com.kazemieh.www.batman.domin.model.Movie
import com.kazemieh.www.batman.domin.model.AllMoves
import kotlinx.coroutines.flow.Flow


interface BatmanRepository {

    suspend fun getAllMovies(): Flow<List<AllMoves>>
    suspend fun getMovieById(id: String):  Flow<ApiResult<Movie>>

}
