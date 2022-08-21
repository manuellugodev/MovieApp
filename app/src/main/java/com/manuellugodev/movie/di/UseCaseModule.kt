package com.manuellugodev.movie.di

import com.manuellugodev.movie.data.home.RepositoryMovies
import com.manuellugodev.movie.data.search.RepositorySearch
import com.manuellugodev.movie.usecases.GetMovieBySearchUseCase
import com.manuellugodev.movie.usecases.GetPopularMovieUseCase
import com.manuellugodev.movie.usecases.GetTopRatedMovieUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun getPopularMovieUseCaseProvider(repositoryMovies: RepositoryMovies)=
        GetPopularMovieUseCase(repositoryMovies)

    @Provides
    fun getTopRatedMovieUseCaseProvider(repositoryMovies: RepositoryMovies)=
        GetTopRatedMovieUseCase(repositoryMovies)

    @Provides
    fun getMovieBySearchUseCase(repositorySearch: RepositorySearch)=
        GetMovieBySearchUseCase(repositorySearch)
}