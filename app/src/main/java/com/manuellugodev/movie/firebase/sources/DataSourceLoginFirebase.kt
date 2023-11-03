package com.manuellugodev.movie.firebase.sources

import android.accounts.AuthenticatorException
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.manuellugodev.movie.data.login.DataSourceLogin
import com.manuellugodev.movie.domain.model.User
import com.manuellugodev.movie.vo.DataResult
import com.manuellugodev.movie.vo.ResultLogin
import kotlinx.coroutines.tasks.await


class DataSourceLoginFirebase(private val auth:FirebaseAuth) :
    DataSourceLogin {

     override suspend fun LoginWithEmailAndPassword(email:String, password:String):ResultLogin<String>{
        auth.signInWithEmailAndPassword(email,password).await()
         return ResultLogin.Success(email)
    }

    override suspend fun getUser(): ResultLogin<String> {

        val user = auth.currentUser
        return if (user!=null){
             ResultLogin.Success(userName = user.displayName?:"Usuario")
        }else{
            ResultLogin.Failure(AuthenticatorException("Login Required"))
        }
    }

    override suspend fun logOutUser(): ResultLogin<String> {
        return try {
            auth.signOut()
            ResultLogin.Success("User LogOut")
        }catch (e:Exception){
            ResultLogin.Failure(Exception("Error trying LogOut Session"))
        }

    }

    override suspend fun signUp(user: User, password: String): DataResult<String?> {
        val result = auth.createUserWithEmailAndPassword(user.email,password).await()
        return DataResult.Success(result.user?.uid)
    }
}