package com.kazemieh.www.batman.domin.usecase

import com.kazemieh.www.batman.data.repository.BatmanRepositoryImpl
import com.kazemieh.www.batman.domin.model.AllMoves
import com.kazemieh.www.batman.domin.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieUseCase @Inject constructor(
    private val batmanRepository: BatmanRepositoryImpl
) : BaseUseCase<String, Movie>() {
    override suspend fun execute(parameters: String): Flow<Movie> {
        return batmanRepository.getMovieById(parameters)
    }
}
