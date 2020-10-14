package com.manuellugodev.movie.retrofit.data.models.home

import com.example.android.frameworkretrofit.data.models.movie.ServerMovie
import com.google.gson.annotations.SerializedName

data class MovieResult (

    val page: Int,
    val results: List<ServerMovie>,
    @SerializedName("total_pages") val totalPages:Int,
    @SerializedName("total_results") val totalResults:Int
)