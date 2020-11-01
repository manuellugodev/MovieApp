package com.manuellugodev.movie.data.detail

import com.manuellugodev.movie.domain.model.MovieDetail
import com.manuellugodev.movie.domain.model.MoviePosters
import com.manuellugodev.movie.vo.DataResult

interface DataSourceMovieDetail {

    suspend fun getMovieDetailById(id: Int):DataResult<MovieDetail>

    suspend fun getMovieImagesPostersDetailById(id: Int):DataResult<List<MoviePosters>>


}