package com.manuellugodev.movie.ui.profile

import android.util.Log
import androidx.lifecycle.*
import com.google.firebase.auth.FirebaseAuth
import com.manuellugodev.movie.data.profile.RepositoryProfile
import com.manuellugodev.movie.vo.DataResult
import kotlinx.coroutines.Dispatchers

class ProfileViewModel(private val repository: RepositoryProfile) : ViewModel() {

    val fetchProfileData= liveData(Dispatchers.IO) {
        emit(DataResult.Loading())

        try {

            val uid=FirebaseAuth.getInstance().currentUser?.uid

            val dataProfile=repository.getProfileUser(uid!!)

            emit(dataProfile)

        }catch (e:Exception){
            Log.e("Exception",e.message)

            emit(DataResult.Failure(e))
        }
    }
}