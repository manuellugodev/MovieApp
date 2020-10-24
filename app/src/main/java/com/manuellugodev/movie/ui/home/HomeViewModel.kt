package com.manuellugodev.movie.ui.home

import android.util.Log
import androidx.lifecycle.*
import com.manuellugodev.movie.data.home.RepositoryMovies
import com.manuellugodev.movie.usecases.GetPopularMovieUseCase
import com.manuellugodev.movie.usecases.GetTopRatedMovieUseCase
import com.manuellugodev.movie.vo.DataResult
import kotlinx.coroutines.Dispatchers

class HomeViewModel(
    private val getTopRatedMovieUseCase: GetTopRatedMovieUseCase,
    private val getPopularMovieUseCase: GetPopularMovieUseCase
) : ViewModel() {

    /*
        val fetchMovieListComedy= liveData (Dispatchers.IO){

            emit(DataResult.Loading())
                try {
                    val value=repository.getMoviesForCategory("Comedia")

                    emit(value)
                }catch (e:Exception){
                    Log.e("ERROR","Error de Tipo: ${e.message}")
                    emit(DataResult.Failure(e))
                }


        }
        val fetchMovieListDrama= liveData (Dispatchers.IO){

            emit(DataResult.Loading())
            try {
                val value=repository.getMoviesForCategory("Drama")

                emit(value)
            }catch (e:Exception){
                Log.e("ERROR","Error de Tipo: ${e.message}")
                emit(DataResult.Failure(e))
            }


        }
        val fetchMovieListHorror= liveData (Dispatchers.IO){

            emit(DataResult.Loading())
            try {
                val value=repository.getMoviesForCategory("Terror")

                emit(value)
            }catch (e:Exception){
                Log.e("ERROR","Error de Tipo: ${e.message}")
                emit(DataResult.Failure(e))
            }


        }*/
    val fetchMoviePopularList = liveData {

        emit(DataResult.Loading())
        try {
            val value = getPopularMovieUseCase.invoke()

            emit(value)
        } catch (e: Exception) {
            Log.e("ERROR", "Error de Tipo: ${e.message}")
            emit(DataResult.Failure(e))
        }


    }


    val fetchMovieListComedy = liveData {

        emit(DataResult.Loading())
        try {

            val value = getTopRatedMovieUseCase.invoke()

            emit(value)
        } catch (e: Exception) {
            Log.e("ERROR", "Error de Tipo: ${e.message}")
            emit(DataResult.Failure(e))
        }


    }


}

class HomeViewModelFactory(
    private val getTopRatedMovieUseCase: GetTopRatedMovieUseCase,
    private val getPopularMovieUseCase: GetPopularMovieUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return modelClass.getConstructor(
            GetTopRatedMovieUseCase::class.java,
            GetPopularMovieUseCase::class.java
        )
            .newInstance(
                getTopRatedMovieUseCase,
                getPopularMovieUseCase
            )
    }
}