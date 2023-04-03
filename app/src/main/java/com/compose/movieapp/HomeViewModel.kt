package com.compose.movieapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.movieapp.data.MovieResponseDetail
import com.compose.movieapp.data.Movies
import com.compose.movieapp.usecase.MovieRepositoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepositoryUseCase: MovieRepositoryUseCase
) : ViewModel() {

    private val _moviesListFlow = MutableStateFlow<Resource<List<Movies>?>?>(null)
    val moviesListFlow : StateFlow<Resource<List<Movies>?>?> = _moviesListFlow

    private val _moviesDetailFlow = MutableStateFlow<Resource<MovieResponseDetail>?>(null)
    val moviesDetailFlow : StateFlow<Resource<MovieResponseDetail>?> = _moviesDetailFlow

    fun getAllMovies()=viewModelScope.launch(Dispatchers.IO){
        Log.e("MoviesApp::","DATa---->${movieRepositoryUseCase.getAllMovies(1)}")
        _moviesListFlow.value = movieRepositoryUseCase.getAllMovies(1)
    }

    fun getMovieDetail(id:Int) = viewModelScope.launch(Dispatchers.IO) {
        Log.e("MoviesApp::","DATass---->${movieRepositoryUseCase.getMovieDetail(id)}")
        _moviesDetailFlow.value = movieRepositoryUseCase.getMovieDetail(id)
    }
}