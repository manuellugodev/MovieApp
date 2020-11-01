package com.manuellugodev.movie.ui.login

import android.util.Log
import androidx.lifecycle.*
import com.manuellugodev.movie.data.login.RepositoryLogin
import com.manuellugodev.movie.vo.ResultLogin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(private val repository: RepositoryLogin):ViewModel() {

    private val _resultLoginUser:MutableLiveData<ResultLogin<String>> = MutableLiveData()

    val resultLoginUser:LiveData<ResultLogin<String>> =_resultLoginUser


    fun loginWithUserAndPassword(email:String,password:String){
        viewModelScope.launch(Dispatchers.Main){

            _resultLoginUser.value=ResultLogin.Loading()

            try {

                withContext(Dispatchers.IO) {
                   val  name=repository.LoginWithEmailAndPassword(email, password)
                    _resultLoginUser.postValue(name)
                }

            }catch (e:Exception){

                Log.e("ERROR","Error : ${e.message}")
                _resultLoginUser.value=ResultLogin.Failure(e)

            }

        }

    }

}