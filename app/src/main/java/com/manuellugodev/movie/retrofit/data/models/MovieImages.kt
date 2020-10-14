package com.manuellugodev.movie.retrofit.data.models

import com.google.gson.annotations.SerializedName

data class MovieImages(
    @SerializedName("id") val id:Int,
    @SerializedName("posters") val postersList:List<ServerPostersMovie>
)