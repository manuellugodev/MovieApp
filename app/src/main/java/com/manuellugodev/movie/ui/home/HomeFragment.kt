package com.manuellugodev.movie.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.manuellugodev.movie.R
import com.manuellugodev.movie.data.home.Movie
import com.manuellugodev.movie.data.home.interactorHome
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(),adapterListMovies.OnMovieClickListener {

    private val interactor=interactorHome()

    private val homeViewModel by viewModels<HomeViewModel> {HomeViewModelFactory(interactor)}


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel.listMovie.observe(viewLifecycleOwner,::updateListMovie)



        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclers();

        homeViewModel.getListMovie()
    }

    private fun setupRecyclers() {

        rvRecomendadas.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        rvAccion.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        rvDrama.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        rvRomance.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        rvTerror.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)


    }

    private fun updateListMovie(list:List<Movie>){

        rvRecomendadas.adapter=adapterListMovies(requireContext(),list,this)
        rvAccion.adapter=adapterListMovies(requireContext(),list,this)
        rvDrama.adapter=adapterListMovies(requireContext(),list,this)
        rvRomance.adapter=adapterListMovies(requireContext(),list,this)
        rvTerror.adapter=adapterListMovies(requireContext(),list,this)


    }

    override fun onMovieClick(movie: Movie) {
        Toast.makeText(requireContext(),"${movie.name}",Toast.LENGTH_LONG).show()
    }
}