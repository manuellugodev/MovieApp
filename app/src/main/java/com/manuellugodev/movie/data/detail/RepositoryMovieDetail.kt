package com.manuellugodev.movie.data.detail

import com.manuellugodev.movie.domain.model.MovieDetail
import com.manuellugodev.movie.domain.model.MoviePosters
import com.manuellugodev.movie.vo.DataResult

class RepositoryMovieDetail(private val dataSource:DataSourceMovieDetail){

    suspend fun getMovieDetail(idMovie:Int):DataResult<MovieDetail> =
    dataSource.getMovieDetailById(idMovie)

    suspend fun getMovieImagePosters(idMovie: Int):DataResult<List<MoviePosters>> =
    dataSource.getMovieImagesPostersDetailById(idMovie)


}