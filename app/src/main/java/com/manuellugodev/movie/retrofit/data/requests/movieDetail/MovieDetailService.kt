package com.manuellugodev.movie.retrofit.data.requests.movieDetail

import com.manuellugodev.movie.retrofit.data.models.MovieDetailResult
import com.manuellugodev.movie.retrofit.data.models.MovieImageResult
import com.manuellugodev.movie.retrofit.utils.Constans
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailService {

    @GET(Constans.REQUEST_GET_MOVIE_DETAIL_BY_ID)
    suspend fun getMovieDetailById(
        @Path(Constans.REQUEST_PARAM_MOVIE_ID)movieId: Int,
        @Query(Constans.REQUEST_PARAM_API_KEY)apiKey: String
    ): Response<MovieDetailResult>

    @GET(Constans.REQUEST_GET_IMAGES_MOVIE_BY_ID)
    suspend fun getImagesMovieDetailById(
        @Path(Constans.REQUEST_PARAM_MOVIE_ID)movieId: Int,
        @Query(Constans.REQUEST_PARAM_API_KEY)apiKey: String
    ): Response<MovieImageResult>

}