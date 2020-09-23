package com.manuellugodev.movie.data.Login

import com.google.firebase.auth.FirebaseAuth
import com.manuellugodev.movie.vo.ResultLogin
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.coroutineContext


class DataSourceLoginFirebaseImpl(private val auth:FirebaseAuth) : DataSourceLoginFirebase{

     override suspend fun LoginWithEmailAndPassword(email:String, password:String):ResultLogin<String>{
        auth.signInWithEmailAndPassword(email,password).await()

         return ResultLogin.Success(email)
    }
}