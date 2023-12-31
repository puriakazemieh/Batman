package com.kazemieh.www.batman.ui.screen.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kazemieh.www.batman.domin.ApiResult
import com.kazemieh.www.batman.domin.model.AllMoves
import com.kazemieh.www.batman.domin.model.Movie
import com.kazemieh.www.batman.domin.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieUseCase: MovieUseCase) :
    ViewModel() {

    private var _getMovieById: MutableStateFlow<ApiResult<Movie>> =
        MutableStateFlow(ApiResult.Loading)
    val getMovieById: StateFlow<ApiResult<Movie>> = _getMovieById

    fun getMovieById(id: String) {
        viewModelScope.launch {
            movieUseCase.invoke(id).collectLatest {
                _getMovieById.emit(ApiResult.Loading)
                _getMovieById.emit(it)
            }
        }
    }
}