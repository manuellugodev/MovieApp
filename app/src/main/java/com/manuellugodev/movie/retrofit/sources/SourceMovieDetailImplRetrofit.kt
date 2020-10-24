package com.manuellugodev.movie.retrofit.sources

import com.manuellugodev.movie.BuildConfig
import com.manuellugodev.movie.data.detail.SourceMovieDetail
import com.manuellugodev.movie.domain.model.Genres
import com.manuellugodev.movie.domain.model.MovieDetail
import com.manuellugodev.movie.domain.model.MoviePosters
import com.manuellugodev.movie.retrofit.data.models.MovieDetailResult
import com.manuellugodev.movie.retrofit.data.models.ServerGenres
import com.manuellugodev.movie.retrofit.data.models.ServerMoviePosters
import com.manuellugodev.movie.retrofit.data.requests.movieDetail.MovieDetailRequest
import com.manuellugodev.movie.vo.DataResult
import com.manuellugodev.movie.vo.safeApiCall

class SourceMovieDetailImplRetrofit(private val request: MovieDetailRequest) : SourceMovieDetail {


    override suspend fun getMovieDetailById(id: Int): DataResult<MovieDetail> {
        return safeApiCall(
            call = { requestGetMovieDetailById(id) },
            errorMessage = "Mensaje de Error"
        )
    }

    override suspend fun getMovieImagesPostersDetailById(id: Int): DataResult<List<MoviePosters>> {
        return safeApiCall(
            call = { requestGetImagesDetailById(id) },
            errorMessage = "Mensae de Error"
        )
    }


    private suspend fun requestGetMovieDetailById(id: Int): DataResult<MovieDetail> {

        val response = request.service.getMovieDetailById(id, BuildConfig.MovieDbId)

        if (response.isSuccessful) {
            val result = response.body()

            if (result != null) {
                return DataResult.Success(result.toDomainMovieDetail())
            }
        }

        return DataResult.Failure(Exception("Error con los detalles de la pelicula"))

    }

    private suspend fun requestGetImagesDetailById(id: Int): DataResult<List<MoviePosters>> {

        val response = request.service.getImagesMovieDetailById(id, BuildConfig.MovieDbId)

        if (response.isSuccessful) {
            val result = response.body()?.listPosters

            if (result != null) {
                return DataResult.Success(result.map { it.toDomainMoviePosters() })
            }
        }

        return DataResult.Failure(Exception("Error con los detalles de la pelicula"))

    }

}

fun MovieDetailResult.toDomainMovieDetail(): MovieDetail = MovieDetail(
    id,
    title,
    overview,
    poster,
    backdrop,
    duration,
    genres.map { genres-> genres.toDomainGenres() },
    releaseDate,
    popularity,
    voteAverage,
    status
)

fun ServerGenres.toDomainGenres():Genres =
    Genres(idGenre,nameGenre)

fun ServerMoviePosters.toDomainMoviePosters(): MoviePosters =
    MoviePosters(filePathImage, voteAverage)