package com.manuellugodev.movie.usecases

import com.manuellugodev.movie.data.detail.RepositoryMovieDetail
import com.manuellugodev.movie.domain.model.MovieDetail
import com.manuellugodev.movie.vo.DataResult

class GetMovieDetailByIdUseCase(private val repository: RepositoryMovieDetail) {

    suspend fun invoke(idMovie:Int):DataResult<MovieDetail> =
        repository.getMovieDetail(idMovie)

}