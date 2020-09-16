package com.manuellugodev.movie.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manuellugodev.movie.data.home.Movie
import com.manuellugodev.movie.data.home.interactorHome
import com.manuellugodev.movie.vo.Resource

class HomeViewModel(private val  interactor:interactorHome) : ViewModel() {



    private val _listMovie = MutableLiveData<List<Movie>>()


    val listMovie:LiveData<List<Movie>> =_listMovie

    fun getListMovie(){

        _listMovie.value= interactor.getMovies();

    }


}

class HomeViewModelFactory(val interactorHome: interactorHome):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       return modelClass.getConstructor(interactorHome::class.java).newInstance(interactorHome)
    }
}