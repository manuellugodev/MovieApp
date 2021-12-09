package com.manuellugodev.movie.usecases

import com.manuellugodev.movie.data.home.RepositoryMovies
import com.manuellugodev.movie.domain.model.Movie
import com.manuellugodev.movie.vo.DataResult

class GetTopRatedMovieUseCase(private val repository:RepositoryMovies) {

    suspend operator fun invoke():DataResult<List<Movie>> =
        repository.getTopRatedMovies()
}