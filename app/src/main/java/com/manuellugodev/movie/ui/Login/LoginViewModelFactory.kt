package com.manuellugodev.movie.ui.Login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manuellugodev.movie.data.Login.LoginRepository

class LoginViewModelFactory(private val repository: LoginRepository):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(LoginRepository::class.java).newInstance(repository)
    }
}