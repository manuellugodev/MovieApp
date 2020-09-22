package com.manuellugodev.movie.data.home

import com.manuellugodev.movie.vo.Resource

interface RepositoryHome {

    suspend fun getMoviesForCategory(category:String):Resource<List<Movie>>

    suspend fun getMoviesPrincipal()

}