package com.manuellugodev.movie.retrofit.data.models

import com.google.gson.annotations.SerializedName

data class MovieDetailResult(
    @SerializedName("id") val id: Int,
    val title: String,
    @SerializedName("overview") val overview:String,
    @SerializedName("poster_path") val poster:String,
    @SerializedName("backdrop_path") val backdrop:String,
    @SerializedName("runtime") val duration: Int,
    @SerializedName("genres") val genres: List<ServerGenre>,
    @SerializedName("release_date") val releaseDate:String,
    @SerializedName("popularity") val popularity:String,
    @SerializedName("vote_average") val voteAverage:Double,
    @SerializedName("status") val status:String

)