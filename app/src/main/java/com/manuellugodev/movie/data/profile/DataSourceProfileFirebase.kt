package com.manuellugodev.movie.data.profile

import com.manuellugodev.movie.model.User
import com.manuellugodev.movie.vo.DataResult

interface DataSourceProfileFirebase {

    suspend fun getProfileUser(uid:String): DataResult<User>
}