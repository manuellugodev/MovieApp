package com.manuellugodev.movie.data.home.dataSource

import com.manuellugodev.movie.domain.model.Movie
import com.manuellugodev.movie.vo.DataResult

interface DataSourceFirebase {

    suspend fun getListMovieCategory(category:String):DataResult<List<Movie>>

    suspend fun getListMoviePrincipal()

}