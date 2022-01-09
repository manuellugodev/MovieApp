package com.manuellugodev.movie.ui.profile

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.manuellugodev.movie.R
import com.manuellugodev.movie.data.profile.RepositoryProfile
import com.manuellugodev.movie.databinding.FragmentProfileBinding
import com.manuellugodev.movie.domain.model.User
import com.manuellugodev.movie.firebase.sources.DataSourceLoginFirebase
import com.manuellugodev.movie.firebase.sources.DataSourceProfileFirebase
import com.manuellugodev.movie.ui.login.LoginActivity
import com.manuellugodev.movie.vo.DataResult
import com.manuellugodev.movie.vo.ResultLogin

class ProfileFragment : Fragment() {


    private var _bindingProfile: FragmentProfileBinding? = null
    private val bindingProfile get() = _bindingProfile!!

    val db = FirebaseFirestore.getInstance()
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val dataSourceLogin = DataSourceLoginFirebase(firebaseAuth)
    val dataSource = DataSourceProfileFirebase(db)
    val repository = RepositoryProfile(dataSource, dataSourceLogin)

    private val profileViewModel by viewModels<ProfileViewModel> {
        ProfileViewModelFactory(
            repository
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bindingProfile = FragmentProfileBinding.inflate(inflater, container, false)
        return bindingProfile.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileViewModel.fetchProfileData.observe(viewLifecycleOwner, ::updateUiProfile)

        bindingProfile.exitSession.setOnClickListener {
            showAlertExitSession()
        }
    }

    private fun showAlertExitSession() {
        val alertBuilder = AlertDialog.Builder(requireContext())

        alertBuilder.setMessage(getString(R.string.alert_exit_session_message))
        alertBuilder.setPositiveButton(
            getString(R.string.alert_exit_session_message_possitive)
        ) { _, _ ->
            profileViewModel.logOutResult.observe(viewLifecycleOwner, ::updateExitSession)
        }
        alertBuilder.setNegativeButton(
            getString(R.string.alert_exit_session_message_negative)
        ) { dialog, _ ->
            dialog.dismiss()
        }

        alertBuilder.create().show()
    }

    private fun updateExitSession(result: ResultLogin<String>) {

        when (result) {
            is ResultLogin.Loading -> {
                showProgress()
            }
            is ResultLogin.Success -> {
                hideProgress()
                navigateToLogin()
            }

            is ResultLogin.Failure -> {
                hideProgress()
                showMessage("Error ${result.e.message}")
            }
        }

    }

    private fun navigateToLogin() {
        val intentToLogin = Intent(requireContext(), LoginActivity::class.java)
        intentToLogin.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intentToLogin)
    }

    private fun updateUiProfile(result: DataResult<User>) {
        when (result) {
            is DataResult.Loading ->
                showProgress()
            is DataResult.Success -> {
                val profile = result.data
                hideProgress()
                bindingProfile.apply {
                    txtNameProfile.text = "${profile.name} ${profile.lastname}"
                    textEmail.text = profile.email
                    textCountry.text = profile.country
                    textPreferences.text = profile.preferences
                }

            }

            is DataResult.Failure -> {
                hideProgress()
                showMessage(result.exception.message.toString())
            }
        }
    }

    private fun showProgress() {

        bindingProfile.progressProfile.visibility = View.VISIBLE
    }

    private fun hideProgress() {

        bindingProfile.progressProfile.visibility = View.GONE
    }

    private fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }
}