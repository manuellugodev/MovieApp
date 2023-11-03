package com.manuellugodev.movie.data.login

import com.manuellugodev.movie.data.profile.DataSourceProfile
import com.manuellugodev.movie.domain.model.User
import com.manuellugodev.movie.vo.DataResult
import com.manuellugodev.movie.vo.ResultLogin


class RepositoryLogin(private val dataSource:DataSourceLogin,private val dataSourceProfile: DataSourceProfile) {

    suspend fun LoginWithEmailAndPassword(email:String,password:String):ResultLogin<String>{

        return dataSource.LoginWithEmailAndPassword(email, password)

    }

    suspend fun getUserLogin():ResultLogin<String>{
        return dataSource.getUser()
    }

    suspend fun signUp(user: User, password: String):DataResult<User>{
       val result =dataSource.signUp(user,password)

        if(result is DataResult.Success){
            return try {
                dataSourceProfile.saveProfileNewUser(user,result.data.toString())
                DataResult.Success(user)
            }catch (e:Exception){
                DataResult.Failure(e)
            }

        }
        return DataResult.Failure(Exception("Error SignIn"))
    }
}