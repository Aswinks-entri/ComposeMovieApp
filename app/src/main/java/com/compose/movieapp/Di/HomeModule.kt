package com.compose.movieapp.Di

import com.compose.movieapp.usecase.MovieRepositoryUseCase
import com.compose.movieapp.usecase.MovieRepositoryUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class HomeModule {

    @Binds
   abstract fun bindMovieRepositoryUseCase(
        movieRepositoryUseCaseImpl: MovieRepositoryUseCaseImpl
    ) :MovieRepositoryUseCase
}