package com.manuellugodev.movie.retrofit.sources

import com.example.android.frameworkretrofit.data.models.movie.ServerMovie
import com.manuellugodev.movie.BuildConfig
import com.manuellugodev.movie.data.home.dataSource.DataSourceMovieDb
import com.manuellugodev.movie.domain.model.Movie
import com.manuellugodev.movie.retrofit.RetrofitClient
import com.manuellugodev.movie.retrofit.data.requests.home.MovieRequest
import com.manuellugodev.movie.vo.DataResult
import com.manuellugodev.movie.vo.safeApiCall
import java.io.IOException

class DataSourceMovieDbImpl(private val request: MovieRequest):
    DataSourceMovieDb {

    override suspend fun getTopRatedListMovies(): DataResult<List<Movie>> {
       return safeApiCall(
           call = {requestGetTopRatedListMovies()},
           errorMessage = "Mensaje Error")
    }

    private suspend fun requestGetTopRatedListMovies(): DataResult<List<Movie>> {

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
