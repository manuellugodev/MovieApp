package com.manuellugodev.movie.data.login

import com.manuellugodev.movie.vo.ResultLogin


class LoginRepositoryImpl(private val dataSource:DataSourceLoginFirebase) : LoginRepository {

    override suspend fun LoginWithEmailAndPassword(email:String,password:String):ResultLogin<String>{

        return dataSource.LoginWithEmailAndPassword(email, password)

    }
}