package com.kazemieh.www.batman.ui.screen.allmovies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kazemieh.www.batman.domin.ApiResult
import com.kazemieh.www.batman.domin.model.AllMoves
import com.kazemieh.www.batman.domin.usecase.AllMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllMoviesViewModel @Inject constructor(allMoviesUseCase: AllMoviesUseCase) :
    ViewModel() {

    private var _getAllMovies: Flow<ApiResult<List<AllMoves>>> =
        MutableStateFlow(ApiResult.Loading)
    val getAllMovies: Flow<ApiResult<List<AllMoves>>> = _getAllMovies

    init {
        viewModelScope.launch {
            _getAllMovies=(allMoviesUseCase.invoke(Unit))
        }
    }


}