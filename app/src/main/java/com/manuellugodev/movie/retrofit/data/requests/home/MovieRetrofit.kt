package com.manuellugodev.movie.retrofit.data.requests.home

import com.example.android.frameworkretrofit.data.models.movie.ServerMovie
import com.manuellugodev.movie.retrofit.data.models.MovieDetail
import com.manuellugodev.movie.retrofit.data.models.MovieImages
import com.manuellugodev.movie.retrofit.data.models.home.MovieResult
import com.manuellugodev.movie.retrofit.utils.Constans.REQUEST_GET_IMAGES_MOVIE_BY_ID
import com.manuellugodev.movie.retrofit.utils.Constans.REQUEST_GET_MOVIE_DETAIL_BY_ID
import com.manuellugodev.movie.retrofit.utils.Constans.REQUEST_GET_POPULAR_MOVIE_LIST
import com.manuellugodev.movie.retrofit.utils.Constans.REQUEST_GET_TOP_RATED_MOVIE_LIST
import com.manuellugodev.movie.retrofit.utils.Constans.REQUEST_PARAM_API_KEY
import com.manuellugodev.movie.retrofit.utils.Constans.REQUEST_PARAM_MOVIE_ID
import com.manuellugodev.movie.retrofit.utils.Constans.REQUEST_PARAM_REGION
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieRetrofit {

    @GET(REQUEST_GET_TOP_RATED_MOVIE_LIST)
    suspend fun getTopRatedMovies(
        @Query(REQUEST_PARAM_API_KEY)apiKey:String
    ):Response<MovieResult>

    @GET(REQUEST_GET_POPULAR_MOVIE_LIST)
    suspend fun getPopularMovies(
        @Query(REQUEST_PARAM_API_KEY)apiKey: String
    ):Response<MovieResult>

    @GET(REQUEST_GET_MOVIE_DETAIL_BY_ID)
    suspend fun getMovieDetailById(
        @Path(REQUEST_PARAM_MOVIE_ID)movieId: Int,
        @Query(REQUEST_PARAM_API_KEY)apiKey: String
    ):Response<MovieDetail>

    @GET(REQUEST_GET_IMAGES_MOVIE_BY_ID)
    suspend fun getImagesMovieDetailById(
        @Path(REQUEST_PARAM_MOVIE_ID)movieId: Int,
        @Query(REQUEST_PARAM_API_KEY)apiKey: String
    ):Response<MovieImages>

}