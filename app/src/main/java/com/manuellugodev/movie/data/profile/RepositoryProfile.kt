package com.manuellugodev.movie.data.profile

import com.manuellugodev.movie.data.login.DataSourceLogin
import com.manuellugodev.movie.domain.model.User
import com.manuellugodev.movie.firebase.sources.DataSourceProfileFirebase
import com.manuellugodev.movie.vo.DataResult
import com.manuellugodev.movie.vo.ResultLogin

class RepositoryProfile(private val dataSource: DataSourceProfile,private val dataSourceLogin:DataSourceLogin) {

    suspend fun getProfileUser(uid:String):DataResult<User>{

        return dataSource.getProfileUser(uid)
    }
    suspend fun logOut():ResultLogin<String>{
        return dataSourceLogin.logOutUser()
    }
}