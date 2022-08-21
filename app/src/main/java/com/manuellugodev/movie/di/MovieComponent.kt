package com.manuellugodev.movie.di

import com.manuellugodev.movie.ui.search.SearchFragment
import com.manuellugodev.movie.ui.home.HomeFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,DataModule::class,RequestModule::class,UseCaseModule::class])
interface MovieComponent {

    fun inject(homeFragment: HomeFragment)
    fun inject(searchFragment: SearchFragment)


}