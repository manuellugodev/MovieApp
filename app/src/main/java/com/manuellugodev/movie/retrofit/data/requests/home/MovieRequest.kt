package com.manuellugodev.movie.retrofit.data.requests.home

import com.manuellugodev.movie.retrofit.BaseRequest

class MovieRequest(baseUrl:String):BaseRequest<MovieService>(baseUrl) {

    val service= getService<MovieService>()
}