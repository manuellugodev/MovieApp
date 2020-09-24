package com.manuellugodev.movie.data.profile

import com.manuellugodev.movie.model.User
import com.manuellugodev.movie.vo.Resource

class RepositoryProfileImpl(private val dataSource:DataSourceProfileFirebase):RepositoryProfile {

    override suspend fun getProfileUser(uid:String):Resource<User>{

        return dataSource.getProfileUser(uid)
    }
}