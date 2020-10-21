package com.manuellugodev.movie.usecases

import com.manuellugodev.movie.data.detail.RepositoryMovieDetail
import com.manuellugodev.movie.domain.model.MovieImage
import com.manuellugodev.movie.domain.model.MoviePosters
import com.manuellugodev.movie.vo.DataResult

class GetMovieImagesByIdUseCase(private val repository:RepositoryMovieDetail) {

    suspend fun invoke(idMovie:Int): DataResult<List<MoviePosters>> {

        return repository.getMovieImagePosters(idMovie)
    }
}