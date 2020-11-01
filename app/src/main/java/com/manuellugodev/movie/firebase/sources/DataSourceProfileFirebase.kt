package com.manuellugodev.movie.firebase.sources


import com.google.firebase.firestore.FirebaseFirestore
import com.manuellugodev.movie.data.profile.DataSourceProfile
import com.manuellugodev.movie.domain.model.User
import com.manuellugodev.movie.vo.DataResult
import kotlinx.coroutines.tasks.await

class DataSourceProfileFirebase(private val db:FirebaseFirestore) :
    DataSourceProfile {

    override suspend fun getProfileUser(uid:String):DataResult<User>{
        val resultDocument =db.collection("users").document(uid)

       val user= resultDocument.get().await()


        val resultUser:User?=user.toObject(User::class.java)

        return  DataResult.Success(resultUser!!)
    }
}