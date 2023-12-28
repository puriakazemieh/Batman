package com.kazemieh.www.batman.data.repository

import com.kazemieh.www.batman.data.db.MovieDao
import com.kazemieh.www.batman.data.remote.BatmanApiService
import com.kazemieh.www.batman.data.remote.model.toMovieEntity
import com.kazemieh.www.batman.domin.ApiResult
import com.kazemieh.www.batman.domin.BatmanRepository
import com.kazemieh.www.batman.domin.model.AllMoves
import com.kazemieh.www.batman.domin.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class BatmanRepositoryImpl @Inject constructor(
    private val batmanApiService: BatmanApiService,
    private val batmanDp: MovieDao
) : BatmanRepository {


    private val isNetWorkAvailable = MutableStateFlow(false)
    private suspend inline fun <T> doWithNetwork(fetch: () -> T): T {
        return isNetWorkAvailable.first { it }.let { fetch() }
    }


    override suspend fun getAllMovies(): Flow<ApiResult<List<AllMoves>>> =
        flow<ApiResult<List<AllMoves>>> {
            ApiResult.Success(batmanDp.getAllMovie())
        }.onStart {
            doWithNetwork {
                CoroutineScope(currentCoroutineContext()).launch {
                    batmanApiService.getAllBatmanMovies("3e974fca", "batman").Search.map {
                        batmanDp.insertAllMovie(it.toMovieEntity())
                    }
                }
            }
        }.catch {
            ApiResult.Error(Exception(it))
        }


    override suspend fun getMovieById(id: String): Flow<ApiResult<Movie>> {
        return flow<ApiResult<Movie>> {
            ApiResult.Success(batmanDp.getMovie(id))
        }.onStart {
            doWithNetwork {
                CoroutineScope(currentCoroutineContext()).launch {
                    batmanApiService.getMoveById(apikey = "3e974fca", id = id).let {
                        batmanDp.insertMovie(it.toMovieEntity())
                    }
                }
            }
        }.catch {
            ApiResult.Error(Exception(it))
        }
    }

}

