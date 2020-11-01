package com.manuellugodev.movie.di

import com.manuellugodev.movie.retrofit.data.requests.home.MovieRequest
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RequestModule {

    @Provides
    @Singleton
    @Named("baseUrl")
    fun baseUrlProvider()="https://api.themoviedb.org/3/"

    @Provides
    fun movieRequestProvider(@Named("baseUrl")baseUrl:String)=MovieRequest(baseUrl)
}