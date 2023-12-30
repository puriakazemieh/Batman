package com.kazemieh.www.batman.domin.usecase

import android.util.Log
import com.kazemieh.www.batman.data.repository.BatmanRepositoryImpl
import com.kazemieh.www.batman.domin.ApiResult
import com.kazemieh.www.batman.domin.model.AllMoves
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AllMoviesUseCase @Inject constructor(
    private val batmanRepository: BatmanRepositoryImpl
) : BaseUseCase<Unit, ApiResult<List<AllMoves>>>() {
    override suspend fun execute(parameters: Unit): Flow<ApiResult<List<AllMoves>>> =
        channelFlow {
            CoroutineScope(currentCoroutineContext()).launch {
                batmanRepository.getAllMovies().collectLatest {
                    send(ApiResult.Success(it))
                }
            }
        }
}
