package com.manuellugodev.movie.retrofit.data.requests.movieDetail

import com.manuellugodev.movie.retrofit.BaseRequest

class MovieDetailRequest(baseUrl:String) :BaseRequest<MovieDetailService>(baseUrl){

    val service= getService<MovieDetailService>()
}