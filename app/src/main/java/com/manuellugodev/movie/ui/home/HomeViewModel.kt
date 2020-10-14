package com.manuellugodev.movie.ui.home

import android.util.Log
import androidx.lifecycle.*
import com.manuellugodev.movie.data.home.RepositoryHome
import com.manuellugodev.movie.vo.DataResult
import kotlinx.coroutines.Dispatchers

class HomeViewModel(private val  repository:RepositoryHome) : ViewModel() {

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


    }
    val fetchMovieListAction= liveData (Dispatchers.IO){

        emit(DataResult.Loading())
        try {
            val value=repository.getMoviesForCategory("Accion")

            emit(value)
        }catch (e:Exception){
            Log.e("ERROR","Error de Tipo: ${e.message}")
            emit(DataResult.Failure(e))
        }


    }
*/

    val fetchMovieListComedy= liveData (Dispatchers.IO){

        emit(DataResult.Loading())
        try {
            val value=repository.getMoviesPrincipal()

            emit(value)
        }catch (e:Exception){
            Log.e("ERROR","Error de Tipo: ${e.message}")
            emit(DataResult.Failure(e))
        }


    }




}

class HomeViewModelFactory(val repository: RepositoryHome):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       return modelClass.getConstructor(RepositoryHome::class.java).newInstance(repository)
    }
}