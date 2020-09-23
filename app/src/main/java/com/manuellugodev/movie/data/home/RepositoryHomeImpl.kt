package com.manuellugodev.movie.data.home

import com.manuellugodev.movie.data.home.dataSource.DataSourceFirebase
import com.manuellugodev.movie.model.Movie
import com.manuellugodev.movie.vo.Resource

class RepositoryHomeImpl(private val dataSourceFirebase: DataSourceFirebase):RepositoryHome{

    override suspend fun getMoviesForCategory(category: String):Resource<List<Movie>> {

       return dataSourceFirebase.getListMovieCategory(category)
    }

    override suspend fun getMoviesPrincipal() {

         dataSourceFirebase.getListMoviePrincipal()
    }
}