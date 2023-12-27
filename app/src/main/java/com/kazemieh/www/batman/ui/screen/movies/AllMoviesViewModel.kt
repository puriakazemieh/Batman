package com.kazemieh.www.batman.ui.screen.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kazemieh.www.batman.domin.ApiResult
import com.kazemieh.www.batman.domin.model.AllMoves
import com.kazemieh.www.batman.domin.usecase.AllMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllMoviesViewModel @Inject constructor(allMoviesUseCase: AllMoviesUseCase) :
    ViewModel() {



//    private val _getAllMovies: ApiResult<List<AllMoves>> = allMoviesUseCase(Unit)
//
//    private val _getAllMovies: ApiResult<List<AllMoves>> =(allMoviesUseCase.invoke(Unit))
//        MutableStateFlow(ApiResult.Loading)
//    val getAllMovies: ApiResult<List<AllMoves>> = _getAllMovies
//
//    init {
//        viewModelScope.launch {
//            _getAllMovies=(allMoviesUseCase.invoke(Unit))
//        }
//    }


}