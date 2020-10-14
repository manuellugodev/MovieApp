package com.manuellugodev.movie.retrofit.data.models

import com.google.gson.annotations.SerializedName

data class ServerPostersMovie(
    @SerializedName("file_path") val filePathImage:String,
    @SerializedName("vote_average") val voteAverage:Int
)
