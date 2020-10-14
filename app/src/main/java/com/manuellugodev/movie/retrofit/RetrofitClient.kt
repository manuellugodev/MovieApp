package com.manuellugodev.movie.retrofit

import com.google.gson.GsonBuilder
import com.manuellugodev.movie.retrofit.data.requests.home.MovieRetrofit
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val movieClient by lazy{
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(MovieRetrofit::class.java)

    }

}