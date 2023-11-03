package com.manuellugodev.movie.ui.login

import android.accounts.AuthenticatorException
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.lifecycle.observe
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.manuellugodev.movie.MainActivity
import com.manuellugodev.movie.R
import com.manuellugodev.movie.data.login.RepositoryLogin
import com.manuellugodev.movie.databinding.ActivityLoginBinding
import com.manuellugodev.movie.domain.model.User
import com.manuellugodev.movie.firebase.sources.DataSourceLoginFirebase
import com.manuellugodev.movie.firebase.sources.DataSourceProfileFirebase
import com.manuellugodev.movie.ui.register.RegisterFragment
import com.manuellugodev.movie.vo.DataResult
import com.manuellugodev.movie.vo.ResultLogin

class LoginActivity : AppCompatActivity() {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val dataSource = DataSourceLoginFirebase(firebaseAuth)
    private val dataSourceProfile = DataSourceProfileFirebase(FirebaseFirestore.getInstance())
    private val repository = RepositoryLogin(dataSource,dataSourceProfile)

    private val loginViewModel by viewModels<LoginViewModel> { LoginViewModelFactory(repository) }

    private lateinit var bindingLogin: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingLogin = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(bindingLogin.root)

        loginViewModel.resultLoginUser.observe(this, ::updateUiLogin)
        loginViewModel.getUser()

        loginViewModel.resultSignUpUser.observe(this,::updateRegisterUI)
        bindingLogin.login.setOnClickListener {
            LoginEmailAndPassword()
        }

        bindingLogin.txtRegister.setOnClickListener{
            showFragmentRegister()
        }
    }

    override fun onBackPressed() {
        modeRegister(false)
    }
    private fun updateRegisterUI(dataResult: DataResult<User>?) {

        when(dataResult){
            is DataResult.Success->{
                hideProgress()
                modeRegister(false)
                showMessage("Sign In Successfull ,continue Log in")
            }

            is DataResult.Failure -> {
                hideProgress()
                showMessage("Ocurred some wrong with the sign In")
            }
            is DataResult.Loading -> {showProgress()}
        }
    }

    private fun showFragmentRegister() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            val fragment =RegisterFragment()
            add(R.id.fragment_register,fragment)
            modeRegister(true)
        }
    }
    private fun LoginEmailAndPassword() {
        bindingLogin.apply {
            var email = username.text.toString()
            var password = password.text.toString()

            loginViewModel.loginWithUserAndPassword(email, password)
        }
    }

    private fun updateUiLogin(result: ResultLogin<String>) {

        when (result) {

            is ResultLogin.Success -> {
                hideProgress()
                startActivity(Intent(this, MainActivity::class.java))

            }

            is ResultLogin.Loading -> {
                showProgress()
                showMessage("Loading...")
            }

            is ResultLogin.Failure -> {
                when (result.e) {
                    is AuthenticatorException -> {
                        showMessage(result.e.message.toString())
                    }
                    else -> {
                        showMessage("Error Tipo: ${result.e.message}")
                    }
                }
                hideProgress()

            }
        }

    }

    private fun modeRegister(active:Boolean){

        if(active){
            bindingLogin.cardEditView.visibility=View.GONE
            bindingLogin.login.visibility=View.INVISIBLE
            bindingLogin.txtRegister.visibility=View.INVISIBLE
            bindingLogin.fragmentRegister.visibility=View.VISIBLE
        }else{
            bindingLogin.cardEditView.visibility=View.VISIBLE
            bindingLogin.login.visibility=View.VISIBLE
            bindingLogin.txtRegister.visibility=View.VISIBLE
            bindingLogin.fragmentRegister.visibility=View.GONE
        }

    }

    private fun showProgress() {
        bindingLogin.loading.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        bindingLogin.loading.visibility = View.GONE
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }


}