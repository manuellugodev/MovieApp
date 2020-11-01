package com.manuellugodev.movie.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.manuellugodev.movie.data.detail.DataSourceMovieDetail
import com.manuellugodev.movie.data.home.dataSource.DataSourceMovieDb
import com.manuellugodev.movie.data.login.DataSourceLogin
import com.manuellugodev.movie.data.profile.DataSourceProfile
import com.manuellugodev.movie.firebase.sources.DataSourceLoginFirebase
import com.manuellugodev.movie.firebase.sources.DataSourceProfileFirebase
import com.manuellugodev.movie.retrofit.data.requests.home.MovieRequest
import com.manuellugodev.movie.retrofit.data.requests.movieDetail.MovieDetailRequest
import com.manuellugodev.movie.retrofit.sources.RemoteSourceMovieDb
import com.manuellugodev.movie.retrofit.sources.RemoteSourceMovieDetail
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun firebaseAuthProvider() = FirebaseAuth.getInstance()

    @Provides
    fun firestoreProvider() = FirebaseFirestore.getInstance()

    @Provides
    fun dataSourceMovieDbProvider(
        movieRequest: MovieRequest
    ): DataSourceMovieDb = RemoteSourceMovieDb(movieRequest)

    @Provides
    fun dataSourceMovieDetailProvider(
        movieDetailRequest: MovieDetailRequest
    ): DataSourceMovieDetail = RemoteSourceMovieDetail(movieDetailRequest)

    @Provides
    fun dataSourceLoginProvider(auth: FirebaseAuth): DataSourceLogin =
        DataSourceLoginFirebase(auth)

    @Provides
    fun dataSourceProfileProvider(firestore: FirebaseFirestore):DataSourceProfile =
        DataSourceProfileFirebase(firestore)


}