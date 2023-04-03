package com.compose.movieapp

import com.compose.movieapp.data.MovieResponse
import com.compose.movieapp.data.MovieResponseDetail
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/now_playing")
    suspend fun getMovies(@Query("api_key") api_key:String,
                          @Query("language") language:String): MovieResponse

    @GET("movie/now_playing")
    suspend fun getAllMovies(@Query("api_key") api_key:String,
                             @Query("language") language:String,
                             @Query("page") page:Int): Response<MovieResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path(value = "movie_id", encoded = false) key: Int,
        @Query("api_key") api_key:String,
        @Query("language") language:String
    ) : MovieResponseDetail
}