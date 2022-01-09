package com.manuellugodev.movie.data.login

import com.manuellugodev.movie.vo.ResultLogin

interface DataSourceLogin {
    suspend fun LoginWithEmailAndPassword(email: String, password: String): ResultLogin<String>
    suspend fun getUser(): ResultLogin<String>
    suspend fun logOutUser(): ResultLogin<String>
}