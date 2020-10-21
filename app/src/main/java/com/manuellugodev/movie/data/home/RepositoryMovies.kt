package com.manuellugodev.movie.data.home

import com.manuellugodev.movie.data.home.dataSource.DataSourceMovieDb
import com.manuellugodev.movie.domain.model.Movie
import com.manuellugodev.movie.vo.DataResult

class RepositoryMovies(private val dataSourceMovieDb: DataSourceMovieDb){

    suspend fun getTopRatedMovies() :DataResult<List<Movie>>{
        return dataSourceMovieDb.getTopRatedListMovies()
    }

    suspend fun getPopularMovies():DataResult<List<Movie>>{
        return dataSourceMovieDb.getPopularListMovies()
    }
}