package com.compose.movieapp.usecase

import com.compose.movieapp.Resource
import com.compose.movieapp.data.MovieResponseDetail
import com.compose.movieapp.data.Movies

interface MovieRepositoryUseCase {
    suspend fun getAllMovies(page:Int) : Resource<List<Movies>>
    suspend fun getMovieDetail(id:Int) : Resource<MovieResponseDetail>
}