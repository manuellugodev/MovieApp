package com.manuellugodev.movie.data.home

import com.manuellugodev.movie.data.home.dataSource.DataSourceMovieDb
import com.manuellugodev.movie.model.Movie
import com.manuellugodev.movie.vo.DataResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryHome(
    private val dataSourceMovieDb: DataSourceMovieDb){

    suspend fun getMoviesPrincipal() :DataResult<List<Movie>>{
        return dataSourceMovieDb.getTopRatedListMovies()
    }
}