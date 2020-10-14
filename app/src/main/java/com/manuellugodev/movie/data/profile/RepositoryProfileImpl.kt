package com.manuellugodev.movie.data.profile

import com.manuellugodev.movie.model.User
import com.manuellugodev.movie.vo.DataResult

class RepositoryProfileImpl(private val dataSource:DataSourceProfileFirebase):RepositoryProfile {

    override suspend fun getProfileUser(uid:String):DataResult<User>{

        return dataSource.getProfileUser(uid)
    }
}