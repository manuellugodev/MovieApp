package com.manuellugodev.movie.ui.search

import androidx.lifecycle.*
import com.manuellugodev.movie.domain.model.Movie
import com.manuellugodev.movie.usecases.GetMovieBySearchUseCase
import com.manuellugodev.movie.vo.DataResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(private val getMovieBySearchUseCase: GetMovieBySearchUseCase) : ViewModel() {

    private val _searchResult = MutableLiveData<DataResult<List<Movie>>>()
    val searchResult: LiveData<DataResult<List<Movie>>>  get()= _searchResult

    fun search(word:String){
        _searchResult.value=DataResult.Loading()
        viewModelScope.launch (Dispatchers.IO){

            try {
                val result=getMovieBySearchUseCase(word)
                _searchResult.postValue(result)

            }catch (e:Exception){
                _searchResult.postValue(DataResult.Failure(e))
            }



        }
    }
}

class SearchViewModelFactory(private val getMovieBySearchUseCase: GetMovieBySearchUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(GetMovieBySearchUseCase::class.java)
            .newInstance(getMovieBySearchUseCase)
    }


}