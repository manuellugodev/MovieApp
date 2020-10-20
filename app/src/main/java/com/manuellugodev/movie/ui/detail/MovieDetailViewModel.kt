package com.manuellugodev.movie.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manuellugodev.movie.data.detail.RepositoryMovieDetail

class MovieDetailViewModel:ViewModel() {



}

class MovieDetailViewModelFactory(val repository:RepositoryMovieDetail):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(RepositoryMovieDetail::class.java).newInstance(repository)
    }
}