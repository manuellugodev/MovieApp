package com.manuellugodev.movie.data.profile

import com.manuellugodev.movie.domain.model.User
import com.manuellugodev.movie.firebase.sources.DataSourceProfileFirebase
import com.manuellugodev.movie.vo.DataResult

class RepositoryProfile(private val dataSource: DataSourceProfile) {

    suspend fun getProfileUser(uid:String):DataResult<User>{

        return dataSource.getProfileUser(uid)
    }
}