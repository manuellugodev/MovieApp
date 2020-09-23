package com.manuellugodev.movie.vo

sealed class ResultLogin<out T> {

    class Loading<out T> : ResultLogin<T>()
    data class Success<out T>(val userName:String):ResultLogin<T>()
    data class Failure<out T>(val e:Exception):ResultLogin<T>()
}