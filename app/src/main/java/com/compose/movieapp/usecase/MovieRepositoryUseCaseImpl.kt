package com.compose.movieapp.usecase

import com.compose.movieapp.ApiService
import com.compose.movieapp.Constants.API_KEY
import com.compose.movieapp.Constants.LANGUAGE_KEY
import com.compose.movieapp.Resource
import com.compose.movieapp.data.MovieResponseDetail
import com.compose.movieapp.data.Movies
import javax.inject.Inject

class MovieRepositoryUseCaseImpl @Inject constructor(private val apiService: ApiService) : MovieRepositoryUseCase {
    override suspend fun getAllMovies(page: Int): Resource<List<Movies>> {
        return try {
            val response = apiService.getMovies(API_KEY, LANGUAGE_KEY)
            Resource.Success(response.results)
        }catch (e:Exception){
            Resource.Failure(e)
        }

    }

    override suspend fun getMovieDetail(id: Int): Resource<MovieResponseDetail> {
        return try {
            val response = apiService.getMovieDetail(id,API_KEY, LANGUAGE_KEY)
            Resource.Success(response)
        }catch (e:Exception){
            Resource.Failure(e)
        }
    }
}