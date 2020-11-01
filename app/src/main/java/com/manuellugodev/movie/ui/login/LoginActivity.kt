package com.manuellugodev.movie.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.google.firebase.auth.FirebaseAuth
import com.manuellugodev.movie.MainActivity
import com.manuellugodev.movie.R
import com.manuellugodev.movie.data.login.RepositoryLogin
import com.manuellugodev.movie.firebase.sources.DataSourceLoginFirebase
import com.manuellugodev.movie.vo.ResultLogin
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity:AppCompatActivity() {

    private val firebaseAuth=FirebaseAuth.getInstance()
    private val dataSource= DataSourceLoginFirebase(firebaseAuth)
    private val repository=RepositoryLogin(dataSource)

    private val loginViewModel by  viewModels<LoginViewModel> {LoginViewModelFactory(repository) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



        loginViewModel.resultLoginUser.observe(this,::updateUiLogin)

        login.setOnClickListener {
            LoginEmailAndPassword()
        }
    }


    private fun LoginEmailAndPassword(){
        var email=username.text.toString()
        var password=password.text.toString()

        loginViewModel.loginWithUserAndPassword(email,password)


    }

    private fun updateUiLogin(result:ResultLogin<String>){

        when(result) {

            is ResultLogin.Success -> {
                hideProgress()
                startActivity(Intent(this, MainActivity::class.java))

            }

            is ResultLogin.Loading-> {
                showProgress()
                showMessage("Loading...")
            }
            
            is ResultLogin.Failure->{
                hideProgress()
                showMessage("Error Tipo: ${result.e.message}")
            }
        }

    }


    private fun showProgress(){
        loading.visibility= View.VISIBLE
    }

    private fun hideProgress(){
        loading.visibility= View.GONE
    }

    private fun showMessage(message:String){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }


}