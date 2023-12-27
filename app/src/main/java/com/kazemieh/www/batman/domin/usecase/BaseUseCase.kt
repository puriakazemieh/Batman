package com.kazemieh.www.batman.domin.usecase

import android.util.Log
import com.kazemieh.www.batman.domin.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext


abstract class BaseUseCase<in P, R> {

    suspend operator fun invoke(parameters: P): Flow<R> {
        return execute(parameters)
            .flowOn(Dispatchers.IO)
            .onStart {
                ApiResult.Loading
            }.catch {
                Log.e("UseCaseFlowNonSuspend", "invoke: $it", it)
                it.printStackTrace()
            }
    }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(parameters: P): Flow<R>
}
