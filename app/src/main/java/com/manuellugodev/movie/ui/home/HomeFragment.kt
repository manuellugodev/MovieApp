package com.manuellugodev.movie.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.manuellugodev.movie.MovieApplication
import com.manuellugodev.movie.R
import com.manuellugodev.movie.databinding.FragmentHomeBinding
import com.manuellugodev.movie.domain.model.Movie
import com.manuellugodev.movie.usecases.GetPopularMovieUseCase
import com.manuellugodev.movie.usecases.GetTopRatedMovieUseCase
import com.manuellugodev.movie.vo.DataResult
import javax.inject.Inject

class HomeFragment : Fragment(), adapterListMovies.OnMovieClickListener {

    private var _bindingHome: FragmentHomeBinding? = null
    private val bindingHome get() = _bindingHome!!

    @Inject
    lateinit var getTopRatedMovieUseCase: GetTopRatedMovieUseCase

    @Inject
    lateinit var getPopularMovieUseCase: GetPopularMovieUseCase


    private val homeViewModel by viewModels<HomeViewModel> {
        HomeViewModelFactory(
            getTopRatedMovieUseCase,
            getPopularMovieUseCase
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as MovieApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        attachObserve()

        _bindingHome= FragmentHomeBinding.inflate(inflater,container,false)
        return bindingHome.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclers();
    }

    private fun attachObserve() {
        homeViewModel.fetchMoviePopularList.observe(viewLifecycleOwner, ::updateAdapter)
    }

    private fun setupRecyclers() {
        bindingHome.rvHome.layoutManager =
            GridLayoutManager(requireContext(), 2)
    }

    private fun updateAdapter(listMovie: DataResult<List<Movie>>) {
        when (listMovie) {
            is DataResult.Success -> {
                hideProgress()
                bindingHome.rvHome.adapter = adapterListMovies(requireContext(), listMovie.data, this)
            }
            is DataResult.Loading -> {
                showProgress()
            }
            is DataResult.Failure -> {
                showError("Error de Tipo: ${listMovie.exception}")
            }
        }
    }

    private fun showProgress(){
        bindingHome.progressBar.visibility=View.VISIBLE
    }
    private fun hideProgress(){
        bindingHome.progressBar.visibility=View.GONE
    }
    private fun showError(messageError:String){
        Toast.makeText(requireContext(), messageError, Toast.LENGTH_LONG).show()
    }

    override fun onMovieClick(movie: Movie) {
        val bundle = Bundle()

        bundle.putInt("IDMOVIE", movie.id)

        findNavController().navigate(R.id.action_navigation_home_to_movieDetailFragment, bundle)
    }
}