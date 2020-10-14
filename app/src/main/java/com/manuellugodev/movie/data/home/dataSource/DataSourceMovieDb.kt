package com.manuellugodev.movie.data.home.dataSource

import com.manuellugodev.movie.model.Movie
import com.manuellugodev.movie.vo.DataResult

interface DataSourceMovieDb {

    suspend fun getTopRatedListMovies():DataResult<List<Movie>>
}