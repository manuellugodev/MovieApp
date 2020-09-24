package com.manuellugodev.movie.data.profile


import com.google.firebase.firestore.FirebaseFirestore
import com.manuellugodev.movie.model.User
import com.manuellugodev.movie.vo.Resource
import kotlinx.coroutines.tasks.await

class DataSourceProfileFirebaseImpl(private val db:FirebaseFirestore) :DataSourceProfileFirebase{

    override suspend fun getProfileUser(uid:String):Resource<User>{
        val resultDocument =db.collection("users").document(uid)

       val user= resultDocument.get().await()


        val resultUser:User?=user.toObject(User::class.java)

        return  Resource.Success(resultUser!!)
    }
}