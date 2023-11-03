package com.manuellugodev.movie.ui.login

import android.util.Log
import androidx.lifecycle.*
import com.manuellugodev.movie.data.login.RepositoryLogin
import com.manuellugodev.movie.domain.model.User
import com.manuellugodev.movie.vo.DataResult
import com.manuellugodev.movie.vo.ResultLogin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(private val repository: RepositoryLogin):ViewModel() {

    private val _resultLoginUser:MutableLiveData<ResultLogin<String>> = MutableLiveData()

    val resultLoginUser:LiveData<ResultLogin<String>> =_resultLoginUser

    private val _resultSignUpUser:MutableLiveData<DataResult<User>> = MutableLiveData()
    val resultSignUpUser:LiveData<DataResult<User>> = _resultSignUpUser
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

    fun getUser() {
        viewModelScope.launch {
            _resultLoginUser.value=ResultLogin.Loading()
            _resultLoginUser.value=repository.getUserLogin()

        }

    }

    fun signIn(user: User, password: String) {
        viewModelScope.launch(Dispatchers.Main) {
            _resultSignUpUser.value =DataResult.Loading()
            try {
                withContext(Dispatchers.IO){
                    val result = repository.signUp(user, password)
                    _resultSignUpUser.postValue(result)
                }
            }catch (e:Exception){
                _resultSignUpUser.value = DataResult.Failure(e)
            }
        }
    }

}