package com.manuellugodev.movie.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manuellugodev.movie.data.login.RepositoryLogin

class LoginViewModelFactory(private val repository: RepositoryLogin):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(RepositoryLogin::class.java).newInstance(repository)
    }
}