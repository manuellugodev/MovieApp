package com.manuellugodev.movie.data.search

import com.manuellugodev.movie.domain.model.Movie
import com.manuellugodev.movie.retrofit.data.models.home.MovieResult
import com.manuellugodev.movie.vo.DataResult

interface DataSourceSearch {
    suspend fun getMovieBySearch(search:String): DataResult<List<Movie>>
}