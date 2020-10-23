package com.manuellugodev.movie.domain.model



data class MovieDetail(
    val id:Int,
    val title:String,
    val overview:String,
    val poster:String,
    val backdrop:String,
    val duration:Int,
    val genres:List<Genres>,
    val releaseDate:String,
    val popularity:String,
    val voteAverage:Double,
    val status:String

)