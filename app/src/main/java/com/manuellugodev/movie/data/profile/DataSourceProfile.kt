package com.manuellugodev.movie.data.profile

import com.manuellugodev.movie.domain.model.User
import com.manuellugodev.movie.vo.DataResult

interface DataSourceProfile {

    suspend fun getProfileUser(uid:String): DataResult<User>
}