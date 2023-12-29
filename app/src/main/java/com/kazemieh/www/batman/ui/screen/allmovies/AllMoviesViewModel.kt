package com.kazemieh.www.batman.ui.screen.allmovies

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kazemieh.www.batman.domin.ApiResult
import com.kazemieh.www.batman.domin.model.AllMoves
import com.kazemieh.www.batman.domin.usecase.AllMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllMoviesViewModel @Inject constructor(private val allMoviesUseCase: AllMoviesUseCase) :
    ViewModel() {

    private var _getAllMovies = MutableStateFlow<List<AllMoves>>(emptyList())
    val getAllMovies: StateFlow<List<AllMoves>> = _getAllMovies

    suspend fun getAll() {

//        _getAllMovies = allMoviesUseCase.invoke(Unit)
        viewModelScope.launch {
            allMoviesUseCase.invoke(Unit).collectLatest  {
                Log.d("949494", "AmazingOfferSection:1 $it")
                _getAllMovies.emit(it)
            }
        }

    }

    init {
//        viewModelScope.launch {
//            _getAllMovies = allMoviesUseCase.invoke(Unit)
//            _getAllMovies.collect(){
//
//                Log.d("949494", "AmazingOfferSection: viewModelScop $it")
//            }
//        }
    }


}