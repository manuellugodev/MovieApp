package com.manuellugodev.movie

import android.app.Application
import com.manuellugodev.movie.di.DaggerMovieComponent
import com.manuellugodev.movie.di.MovieComponent

class MovieApplication:Application() {

    lateinit var appComponent:MovieComponent

    override fun onCreate() {
        super.onCreate()

        appComponent=DaggerMovieComponent.create()
    }
}





