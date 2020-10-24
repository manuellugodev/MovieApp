package com.manuellugodev.movie.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.manuellugodev.movie.R
import com.manuellugodev.movie.domain.model.Movie
import com.manuellugodev.movie.data.home.RepositoryMovies
import com.manuellugodev.movie.retrofit.data.requests.home.MovieRequest
import com.manuellugodev.movie.retrofit.sources.DataSourceMovieDbImpl
import com.manuellugodev.movie.usecases.GetPopularMovieUseCase
import com.manuellugodev.movie.usecases.GetTopRatedMovieUseCase
import com.manuellugodev.movie.vo.DataResult
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), adapterListMovies.OnMovieClickListener {

    private val dataSource =
        DataSourceMovieDbImpl(MovieRequest("https://api.themoviedb.org/3/"))

    private val repository = RepositoryMovies(dataSource)

    private val getTopRatedMoviesUseCase = GetTopRatedMovieUseCase(repository)

    private val getPopularMovieUseCase = GetPopularMovieUseCase(repository)

    private val homeViewModel by viewModels<HomeViewModel> {
        HomeViewModelFactory(
            getTopRatedMoviesUseCase,
            getPopularMovieUseCase
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        attachObserve()



        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclers();


    }

    private fun attachObserve() {

    /*    homeViewModel.fetchMovieListComedy.observe(viewLifecycleOwner, Observer { listMovie ->

            when (listMovie) {

                is DataResult.Success -> {

                    progressBar.visibility = View.GONE

                    rvComedia.adapter = adapterListMovies(requireContext(), listMovie.data, this)

                }

                is DataResult.Loading -> {
                    progressBar.visibility = View.VISIBLE

                }

                is DataResult.Failure -> {
                    Toast.makeText(
                        requireContext(),
                        "Error de Tipo: ${listMovie.exception}",
                        Toast.LENGTH_LONG
                    )
                }
            }

        })*/


        homeViewModel.fetchMoviePopularList.observe(viewLifecycleOwner, Observer { listMovie ->

            when (listMovie) {

                is DataResult.Success -> {

                    progressBar.visibility = View.GONE

                    rvHome.adapter = adapterListMovies(requireContext(), listMovie.data, this)

                }

                is DataResult.Loading -> {
                    progressBar.visibility = View.VISIBLE

                }

                is DataResult.Failure -> {
                    Toast.makeText(
                        requireContext(),
                        "Error de Tipo: ${listMovie.exception}",
                        Toast.LENGTH_LONG
                    )
                }
            }

        })


    }

    private fun setupRecyclers() {

        rvHome.layoutManager =
            GridLayoutManager(requireContext(), 2)


    }


    override fun onMovieClick(movie: Movie) {
        Toast.makeText(requireContext(), "${movie.title}", Toast.LENGTH_LONG).show()

        val bundle = Bundle()

        bundle.putInt("IDMOVIE", movie.id)

        findNavController().navigate(R.id.movieDetailFragment, bundle)
    }
}