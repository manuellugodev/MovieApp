package com.manuellugodev.movie.ui.profile

import android.util.Log
import androidx.lifecycle.*
import com.google.firebase.auth.FirebaseAuth
import com.manuellugodev.movie.data.profile.RepositoryProfile
import com.manuellugodev.movie.vo.DataResult
import com.manuellugodev.movie.vo.ResultLogin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: RepositoryProfile) : ViewModel() {


    val logOutResult = liveData (Dispatchers.IO){
        emit(ResultLogin.Loading())
        val result= repository.logOut()
        emit(result)
    }

    val fetchProfileData= liveData(Dispatchers.IO) {
        emit(DataResult.Loading())

        try {

            val uid=FirebaseAuth.getInstance().currentUser?.uid

            val dataProfile=repository.getProfileUser(uid!!)

            emit(dataProfile)

        }catch (e:Exception){
            Log.e("Exception",e.message.toString())

            emit(DataResult.Failure(e))
        }
    }
}