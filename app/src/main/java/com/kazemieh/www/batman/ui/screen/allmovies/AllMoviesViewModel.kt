package com.kazemieh.www.batman.ui.screen.allmovies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kazemieh.www.batman.domin.ApiResult
import com.kazemieh.www.batman.domin.model.AllMoves
import com.kazemieh.www.batman.domin.usecase.AllMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllMoviesViewModel @Inject constructor(private val allMoviesUseCase: AllMoviesUseCase) :
    ViewModel() {

    private var _getAllMovies = MutableStateFlow<ApiResult<List<AllMoves>>> (ApiResult.Loading)
    val getAllMovies: StateFlow<ApiResult<List<AllMoves>>> = _getAllMovies


    init {
        viewModelScope.launch {
            allMoviesUseCase.invoke(Unit).collectLatest  {
                _getAllMovies.emit(it)
            }
        }
    }


}