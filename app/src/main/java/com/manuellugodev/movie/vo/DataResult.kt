package com.manuellugodev.movie.vo

import java.io.IOException
import java.lang.Exception

sealed class DataResult<out T>{

    class Loading<out T>:DataResult<T>()

    data class Success<out T>(val data:T): DataResult<T>()

    data class Failure<out T>(val exception: Exception):DataResult<T>()
}

suspend fun <T : Any> safeApiCall(call: suspend () -> DataResult<T>, errorMessage: String): DataResult<T> = try {
    call.invoke()
} catch (e: Exception) {
    DataResult.Failure(IOException(errorMessage, e))
}