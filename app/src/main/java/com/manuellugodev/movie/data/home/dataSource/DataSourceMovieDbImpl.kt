package com.manuellugodev.movie.data.home.dataSource

import com.example.android.frameworkretrofit.data.models.movie.ServerMovie
import com.manuellugodev.movie.BuildConfig
import com.manuellugodev.movie.model.Movie
import com.manuellugodev.movie.retrofit.RetrofitClient
import com.manuellugodev.movie.vo.DataResult
import java.io.IOException

class DataSourceMovieDbImpl():DataSourceMovieDb {

    override suspend fun getTopRatedListMovies(): DataResult<List<Movie>> {

        val response=RetrofitClient.movieClient.getTopRatedMovies(BuildConfig.MovieDbId)

        if(response.isSuccessful){
            val results=response.body()?.results

            if(!results.isNullOrEmpty()){
                return DataResult.Success(results.map { it.toDomainMovie() })
            }
        }

        return DataResult.Failure(IOException("Se produjo un error al obetener las peliculas"))
    }
}

private fun ServerMovie.toDomainMovie(): Movie =
    Movie(id,title,plotSynopsis,posterPath)
