package com.kazemieh.www.batman.domin.usecase

import android.util.Log
import com.kazemieh.www.batman.data.repository.BatmanRepositoryImpl
import com.kazemieh.www.batman.domin.ApiResult
import com.kazemieh.www.batman.domin.model.AllMoves
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AllMoviesUseCase @Inject constructor(
    private val batmanRepository: BatmanRepositoryImpl
) : BaseUseCase<Unit, List<AllMoves>>() {
    override suspend fun execute(parameters: Unit): Flow<List<AllMoves>>
//        flow {
//            batmanRepository.getAllMovies().collectLatest {
//                Log.e("UseCaseFlowNonSuspend", "all movie usecase : $it")
//                emit(it)
//            }
//        }
    {

//       val b= batmanRepository.getAllMovies().collectLatest {
//            ApiResult.Success(it)
//        }
        return batmanRepository.getAllMovies()
    }
}
