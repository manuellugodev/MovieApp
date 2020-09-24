package com.manuellugodev.movie.data.login

import com.google.firebase.auth.FirebaseAuth
import com.manuellugodev.movie.vo.ResultLogin
import kotlinx.coroutines.tasks.await


class DataSourceLoginFirebaseImpl(private val auth:FirebaseAuth) : DataSourceLoginFirebase{

     override suspend fun LoginWithEmailAndPassword(email:String, password:String):ResultLogin<String>{
        auth.signInWithEmailAndPassword(email,password).await()

         return ResultLogin.Success(email)
    }
}