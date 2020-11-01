package com.manuellugodev.movie.di

import com.manuellugodev.movie.data.detail.DataSourceMovieDetail
import com.manuellugodev.movie.data.detail.RepositoryMovieDetail
import com.manuellugodev.movie.data.home.RepositoryMovies
import com.manuellugodev.movie.data.home.dataSource.DataSourceMovieDb
import com.manuellugodev.movie.data.login.DataSourceLogin
import com.manuellugodev.movie.data.login.RepositoryLogin
import com.manuellugodev.movie.data.profile.DataSourceProfile
import com.manuellugodev.movie.data.profile.RepositoryProfile
import com.manuellugodev.movie.firebase.sources.DataSourceProfileFirebase
import dagger.Module
import dagger.Provides


@Module
class DataModule {


    @Provides
    fun repositoryMoviesProvider(
        dataSourceMovieDb: DataSourceMovieDb) =
        RepositoryMovies(dataSourceMovieDb)

    @Provides
    fun repositoryMovieDetailProvider(
        dataSourceMovieDetail: DataSourceMovieDetail)=
        RepositoryMovieDetail(dataSourceMovieDetail)

    @Provides
    fun repositoryProfileProvider(
        dataSourceProfile: DataSourceProfile)=
        RepositoryProfile(dataSourceProfile)

    @Provides
    fun repositoryLoginProvider(dataSourceLogin: DataSourceLogin)=
        RepositoryLogin(dataSourceLogin)

}