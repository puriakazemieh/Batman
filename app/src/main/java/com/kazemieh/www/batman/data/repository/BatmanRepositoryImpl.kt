package com.kazemieh.www.batman.data.repository

import android.util.Log
import com.kazemieh.www.batman.data.db.MovieDao
import com.kazemieh.www.batman.data.remote.BatmanApiService
import com.kazemieh.www.batman.data.remote.model.toMovieEntity
import com.kazemieh.www.batman.domin.ApiResult
import com.kazemieh.www.batman.domin.BatmanRepository
import com.kazemieh.www.batman.domin.model.AllMoves
import com.kazemieh.www.batman.domin.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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


    override suspend fun getAllMovies(): Flow<List<AllMoves>> =
        flow<List<AllMoves>> {
//
//            Log.d("949494", "AmazingOfferSection: flow 1")
            val data = batmanDp.getAllMovie()
//            data.collectLatest {
////                CoroutineScope(Dispatchers.IO).launch {
////                    this@flow.emit(ApiResult.Success(it))
////                }
//                emit(it)
//                Log.d("949494", "AmazingOfferSection: data= $it")
//            }
//            val b=ApiResult.Success(data)
            data
//            emit(data)
        }.onStart {
//            Log.d("949494", "AmazingOfferSection: onStart 1")
//            doWithNetwork {
//                Log.d("949494", "AmazingOfferSection: onStart 2")
            CoroutineScope(currentCoroutineContext()).launch {
//                    Log.d("949494", "AmazingOfferSection: onStart 3")
                batmanApiService.getAllBatmanMovies("3e974fca", "batman").Search.map {
//                        Log.d("949494", "AmazingOfferSection: it= $it")
                    batmanDp.insertAllMovie(it.toMovieEntity())
                }
//                }
            }
        }.catch {
            Log.d("949494", "AmazingOfferSection: exeption= $it")
//            ApiResult.Error(Exception(it))
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

