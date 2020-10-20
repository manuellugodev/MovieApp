package com.manuellugodev.movie.retrofit.data.models

import com.google.gson.annotations.SerializedName

data class MovieImageResult(
    @SerializedName("id") val id:Int,
    @SerializedName("posters") val listPosters:List<ServerMoviePosters>
)