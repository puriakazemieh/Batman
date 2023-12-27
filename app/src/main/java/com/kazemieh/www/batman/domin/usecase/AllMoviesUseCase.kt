package com.kazemieh.www.batman.domin.usecase

import com.kazemieh.www.batman.data.repository.BatmanRepositoryImpl
import com.kazemieh.www.batman.domin.ApiResult
import com.kazemieh.www.batman.domin.model.AllMoves
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AllMoviesUseCase @Inject constructor(
    private val batmanRepository: BatmanRepositoryImpl
) : BaseUseCase<Unit, ApiResult<List<AllMoves>>> () {
    override suspend fun execute(parameters: Unit): Flow<ApiResult<List<AllMoves>> {
        return batmanRepository.getAllMovies()
    }
}
