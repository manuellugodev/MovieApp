package com.manuellugodev.movie.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.firebase.firestore.FirebaseFirestore
import com.manuellugodev.movie.data.profile.RepositoryProfile
import com.manuellugodev.movie.databinding.FragmentProfileBinding
import com.manuellugodev.movie.firebase.sources.DataSourceProfileFirebase
import com.manuellugodev.movie.vo.DataResult
class ProfileFragment : Fragment() {


    private var _bindingProfile: FragmentProfileBinding? = null
    private val bindingProfile get() = _bindingProfile!!

    val db=FirebaseFirestore.getInstance()
    val dataSource= DataSourceProfileFirebase(db)
    val repository=RepositoryProfile(dataSource)

    private val profileViewModel by viewModels<ProfileViewModel> { ProfileViewModelFactory(repository) }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _bindingProfile = FragmentProfileBinding.inflate(inflater,container,false)
        return bindingProfile.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileViewModel.fetchProfileData.observe(viewLifecycleOwner, Observer {result->

            when(result){

                is DataResult.Loading ->
                    showProgress()
                is DataResult.Success ->{
                    val profile=result.data
                    hideProgress()
                    bindingProfile.apply {
                        txtNameProfile.text="${profile.name} ${profile.lastname}"
                        textEmail.text=profile.email
                        textCountry.text=profile.country
                        textPreferences.text=profile.preferences
                    }

                }

                is DataResult.Failure ->{
                    hideProgress()
                    showMessage(result.exception.message.toString())
                }
            }


        })
    }

    private fun showProgress(){

        bindingProfile.progressProfile.visibility=View.VISIBLE
    }

    private fun hideProgress(){

        bindingProfile.progressProfile.visibility=View.GONE
    }

    private fun showMessage(message:String){
        Toast.makeText(requireContext(),message,Toast.LENGTH_LONG).show()
    }
}