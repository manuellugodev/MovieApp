package com.manuellugodev.movie.data.detail

import com.manuellugodev.movie.domain.model.MovieDetail
import com.manuellugodev.movie.domain.model.MovieImage
import com.manuellugodev.movie.domain.model.MoviePosters
import com.manuellugodev.movie.vo.DataResult

class RepositoryMovieDetail(private val source:SourceMovieDetail){

    suspend fun getMovieDetail(idMovie:Int):DataResult<MovieDetail> =
    source.getMovieDetailById(idMovie)

    suspend fun getMovieImagePosters(idMovie: Int):DataResult<List<MoviePosters>> =
    source.getMovieImagesPostersDetailById(idMovie)


}