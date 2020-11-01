package com.manuellugodev.movie.firebase.sources

import com.google.firebase.auth.FirebaseAuth
import com.manuellugodev.movie.data.login.DataSourceLogin
import com.manuellugodev.movie.vo.ResultLogin
import kotlinx.coroutines.tasks.await


class DataSourceLoginFirebase(private val auth:FirebaseAuth) :
    DataSourceLogin {

     override suspend fun LoginWithEmailAndPassword(email:String, password:String):ResultLogin<String>{
        auth.signInWithEmailAndPassword(email,password).await()

         return ResultLogin.Success(email)
    }
}