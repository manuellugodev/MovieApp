package com.manuellugodev.movie.retrofit.data.models

import com.google.gson.annotations.SerializedName

data class ServerGenres(
    @SerializedName("id") val idGenre: Int,
    @SerializedName("name") val nameGenre: String
)
