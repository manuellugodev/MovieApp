package com.manuellugodev.movie.data.login

import com.manuellugodev.movie.vo.ResultLogin

interface DataSourceLoginFirebase {
    suspend fun LoginWithEmailAndPassword(email: String, password: String): ResultLogin<String>
}