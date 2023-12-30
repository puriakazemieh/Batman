package com.kazemieh.www.batman.domin.usecase

import com.kazemieh.www.batman.data.repository.BatmanRepositoryImpl
import com.kazemieh.www.batman.domin.ApiResult
import com.kazemieh.www.batman.domin.model.AllMoves
import com.kazemieh.www.batman.domin.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieUseCase @Inject constructor(
    private val batmanRepository: BatmanRepositoryImpl
) : BaseUseCase<String, ApiResult<Movie>>() {
    override suspend fun execute(parameters: String): Flow<ApiResult<Movie>> =
    channelFlow {
        CoroutineScope(currentCoroutineContext()).launch {
            batmanRepository.getMovieById(parameters).collectLatest {
                send(ApiResult.Success(it))
            }
        }
    }
}
