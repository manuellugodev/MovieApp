package com.manuellugodev.movie.retrofit.data.requests.home

import com.manuellugodev.movie.retrofit.data.models.MovieDetailResult
import com.manuellugodev.movie.retrofit.data.models.MovieImageResult
import com.manuellugodev.movie.retrofit.data.models.home.MovieResult
import com.manuellugodev.movie.retrofit.utils.Constans
import com.manuellugodev.movie.retrofit.utils.Constans.REQUEST_GET_IMAGES_MOVIE_BY_ID
import com.manuellugodev.movie.retrofit.utils.Constans.REQUEST_GET_MOVIE_BY_SEARCH
import com.manuellugodev.movie.retrofit.utils.Constans.REQUEST_GET_MOVIE_DETAIL_BY_ID
import com.manuellugodev.movie.retrofit.utils.Constans.REQUEST_GET_POPULAR_MOVIE_LIST
import com.manuellugodev.movie.retrofit.utils.Constans.REQUEST_GET_TOP_RATED_MOVIE_LIST
import com.manuellugodev.movie.retrofit.utils.Constans.REQUEST_PARAM_API_KEY
import com.manuellugodev.movie.retrofit.utils.Constans.REQUEST_PARAM_MOVIE_ID
import com.manuellugodev.movie.retrofit.utils.Constans.REQUEST_PARAM_QUERY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET(REQUEST_GET_TOP_RATED_MOVIE_LIST)
    suspend fun getTopRatedMovies(
        @Query(REQUEST_PARAM_API_KEY)apiKey:String
    ):Response<MovieResult>

    @GET(REQUEST_GET_POPULAR_MOVIE_LIST)
    suspend fun getPopularMovies(
        @Query(REQUEST_PARAM_API_KEY)apiKey: String
    ):Response<MovieResult>

    @GET(REQUEST_GET_MOVIE_BY_SEARCH)
    suspend fun getMovieBySearch(
        @Query(REQUEST_PARAM_API_KEY)apiKey: String,
        @Query(REQUEST_PARAM_QUERY)search:String
    ):Response<MovieResult>


}