package com.manuellugodev.movie.vo

import java.lang.Exception

sealed class DataResult<out T>{

    class Loading<out T>:DataResult<T>()

    data class Success<out T>(val data:T): DataResult<T>()

    data class Failure<out T>(val exception: Exception):DataResult<T>()
}