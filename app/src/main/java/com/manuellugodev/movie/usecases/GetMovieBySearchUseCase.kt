package com.manuellugodev.movie.usecases

import com.manuellugodev.movie.data.search.RepositorySearch
import com.manuellugodev.movie.domain.model.Movie
import com.manuellugodev.movie.vo.DataResult

class GetMovieBySearchUseCase(private val repositorySearch: RepositorySearch) {

    suspend operator fun invoke(word:String): DataResult<List<Movie>> {
        return repositorySearch.getMovieBySearch(word)
    }
}