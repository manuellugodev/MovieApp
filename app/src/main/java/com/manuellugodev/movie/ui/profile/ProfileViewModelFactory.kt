package com.manuellugodev.movie.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manuellugodev.movie.data.profile.RepositoryProfile

class ProfileViewModelFactory(private val repository: RepositoryProfile) :ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       return modelClass.getConstructor(RepositoryProfile::class.java).newInstance(repository)
    }
}