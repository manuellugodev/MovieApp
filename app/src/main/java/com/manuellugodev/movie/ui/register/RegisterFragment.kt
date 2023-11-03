package com.manuellugodev.movie.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.manuellugodev.movie.databinding.FragmentRegisterBinding
import com.manuellugodev.movie.domain.model.User
import com.manuellugodev.movie.ui.login.LoginViewModel

class RegisterFragment : Fragment() {

    private val viewModelLogin by activityViewModels<LoginViewModel>()


    private var _bindingRegister: FragmentRegisterBinding? = null
    private val bindingRegister get() = _bindingRegister!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bindingRegister = FragmentRegisterBinding.inflate(inflater, container, false)
        return bindingRegister.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingRegister.regButton.setOnClickListener {
            if (fieldsIsValid()) {
                with(bindingRegister) {
                    val user = User(
                        name = regName.text.toString(),
                        lastname = regLastname.text.toString(),
                        email = regEmail.text.toString(),
                        preferences = regFavorite.text.toString()
                    )
                    viewModelLogin.signIn(user,regPass.text.toString())

                }
            }
        }
    }

    private fun fieldsIsValid(): Boolean {
        with(bindingRegister) {
            if (regEmail.text.toString().isEmpty() ||
                regPass.text.toString().isEmpty() ||
                regPassConfirm.text.toString().isEmpty()||
                regName.text.toString().isEmpty() ||
                regLastname.text.toString().isEmpty() ||
                regFavorite.text.toString().isEmpty()
            ) {
                showMessage("Please fill fields")
                return false
            }
            if (regPass.text.toString() != regPassConfirm.text.toString()) {
                showMessage("Please verify your Password")
                return false
            }


        }
        return true
    }

    private fun showMessage(message: String) {
        Toast.makeText(requireContext(), "Please verify your Password", Toast.LENGTH_LONG).show()
    }


}