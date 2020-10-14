package com.manuellugodev.movie.retrofit.data.models

import com.google.gson.annotations.SerializedName

data class MovieDetail(
    @SerializedName("id") val id: Int,
    val title: String,
    @SerializedName("runtime") val duration: Int,
    @SerializedName("genres") val genres: List<ServerGenre>

)