package com.manuellugodev.movie.domain.model

data class MovieDetail(
    val id:Int,
    val title:String,
    val duration:Int,
    val genres:List<Genres>
)