package com.manuellugodev.movie.data.profile


import com.google.firebase.firestore.FirebaseFirestore
import com.manuellugodev.movie.domain.model.User
import com.manuellugodev.movie.vo.DataResult
import kotlinx.coroutines.tasks.await

class DataSourceProfileFirebaseImpl(private val db:FirebaseFirestore) :DataSourceProfileFirebase{

    override suspend fun getProfileUser(uid:String):DataResult<User>{
        val resultDocument =db.collection("users").document(uid)

       val user= resultDocument.get().await()


        val resultUser:User?=user.toObject(User::class.java)

        return  DataResult.Success(resultUser!!)
    }
}