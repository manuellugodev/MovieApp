package com.manuellugodev.movie.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.manuellugodev.movie.R
import com.manuellugodev.movie.domain.model.Movie
import com.manuellugodev.movie.data.home.RepositoryHome
import com.manuellugodev.movie.retrofit.sources.DataSourceMovieDbImpl
import com.manuellugodev.movie.vo.DataResult
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(),adapterListMovies.OnMovieClickListener {

    private val dataSource=
        DataSourceMovieDbImpl()

    private val repository=RepositoryHome(dataSource)

    private val homeViewModel by viewModels<HomeViewModel> {HomeViewModelFactory(repository)}


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

    private fun attachObserve(){

        homeViewModel.fetchMovieListComedy.observe(viewLifecycleOwner, Observer { listMovie->

            when(listMovie){

                is DataResult.Success -> {

                    progressBar.visibility=View.GONE

                    rvComedia.adapter=adapterListMovies(requireContext(),listMovie.data,this)

                }

                is DataResult.Loading->{
                    progressBar.visibility=View.VISIBLE

                }

                is DataResult.Failure->{
                    Toast.makeText(requireContext(),"Error de Tipo: ${listMovie.exception}",Toast.LENGTH_LONG)
                }
            }

        })

       /* homeViewModel.fetchMovieListDrama.observe(viewLifecycleOwner, Observer { listMovie->

            when(listMovie){

                is DataResult.Success -> {

                    progressBar.visibility=View.GONE

                    rvDrama.adapter=adapterListMovies(requireContext(),listMovie.data,this)

                }

                is DataResult.Loading->{
                    progressBar.visibility=View.VISIBLE

                }

                is DataResult.Failure->{
                    Toast.makeText(requireContext(),"Error de Tipo: ${listMovie.exception}",Toast.LENGTH_LONG)
                }
            }

        })

        homeViewModel.fetchMovieListHorror.observe(viewLifecycleOwner, Observer { listMovie->

            when(listMovie){

                is DataResult.Success -> {

                    progressBar.visibility=View.GONE

                    rvTerror.adapter=adapterListMovies(requireContext(),listMovie.data,this)

                }

                is DataResult.Loading->{
                    progressBar.visibility=View.VISIBLE

                }

                is DataResult.Failure->{
                    Toast.makeText(requireContext(),"Error de Tipo: ${listMovie.exception}",Toast.LENGTH_LONG)
                }
            }

        })

        homeViewModel.fetchMovieListAction.observe(viewLifecycleOwner, Observer { listMovie->

            when(listMovie){

                is DataResult.Success -> {

                    progressBar.visibility=View.GONE

                    rvAccion.adapter=adapterListMovies(requireContext(),listMovie.data,this)

                }

                is DataResult.Loading->{
                    progressBar.visibility=View.VISIBLE

                }

                is DataResult.Failure->{
                    Toast.makeText(requireContext(),"Error de Tipo: ${listMovie.exception}",Toast.LENGTH_LONG)
                }
            }

        })*/


    }
    private fun setupRecyclers() {

        rvAccion.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        rvDrama.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        rvComedia.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        rvTerror.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)


    }



    override fun onMovieClick(movie: Movie) {
        Toast.makeText(requireContext(),"${movie.title}",Toast.LENGTH_LONG).show()
    }
}