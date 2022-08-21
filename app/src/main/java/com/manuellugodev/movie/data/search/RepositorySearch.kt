package com.manuellugodev.movie.data.search

import com.manuellugodev.movie.domain.model.Movie
import com.manuellugodev.movie.vo.DataResult

class RepositorySearch(private val sourceSearch: DataSourceSearch) {

    suspend fun getMovieBySearch(string:String): DataResult<List<Movie>> {
        return sourceSearch.getMovieBySearch(string)
    }
}