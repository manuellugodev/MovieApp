package com.manuellugodev.movie.di

import android.app.Application
import com.manuellugodev.movie.ui.home.HomeFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,DataModule::class,RequestModule::class,UseCaseModule::class])
interface MovieComponent {

    fun inject(homeFragment: HomeFragment)


}