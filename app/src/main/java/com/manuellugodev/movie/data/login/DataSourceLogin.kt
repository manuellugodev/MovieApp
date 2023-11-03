package com.manuellugodev.movie.data.login

import com.manuellugodev.movie.domain.model.User
import com.manuellugodev.movie.vo.DataResult
import com.manuellugodev.movie.vo.ResultLogin

interface DataSourceLogin {
    suspend fun LoginWithEmailAndPassword(email: String, password: String): ResultLogin<String>
    suspend fun getUser(): ResultLogin<String>
    suspend fun logOutUser(): ResultLogin<String>
    suspend fun signUp(user: User, password: String): DataResult<String?>
}