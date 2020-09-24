package com.manuellugodev.movie.data.login

import com.manuellugodev.movie.vo.ResultLogin

interface LoginRepository {

   suspend fun LoginWithEmailAndPassword(email:String,password:String): ResultLogin<String>
}