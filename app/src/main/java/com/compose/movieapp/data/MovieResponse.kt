package com.compose.movieapp.data

data class MovieResponse (
    val page: Int,
    val results: List<Movies>,
    val total_pages: Int,
    val total_results: Int
    )