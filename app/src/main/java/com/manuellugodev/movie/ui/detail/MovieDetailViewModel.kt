package com.manuellugodev.movie.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.manuellugodev.movie.usecases.GetMovieDetailByIdUseCase
import com.manuellugodev.movie.usecases.GetMovieImagesByIdUseCase
import com.manuellugodev.movie.vo.DataResult

class MovieDetailViewModel(private val getMovieDetailByIdUseCase: GetMovieDetailByIdUseCase,
                           private val getMovieImagesByIdUseCase: GetMovieImagesByIdUseCase) : ViewModel() {


    fun fetchMovieDetail(idMovie:Int) = liveData{

        emit(DataResult.Loading())

        val result= getMovieDetailByIdUseCase.invoke(idMovie)

        emit(result)

    }






}

class MovieDetailViewModelFactory(
    private val getMovieDetailByIdUseCase: GetMovieDetailByIdUseCase,
    private val getMovieImagesByIdUseCase: GetMovieImagesByIdUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            GetMovieDetailByIdUseCase::class.java,
            GetMovieImagesByIdUseCase::class.java
        ).newInstance(
            getMovieDetailByIdUseCase,
            getMovieImagesByIdUseCase
        )
    }
}