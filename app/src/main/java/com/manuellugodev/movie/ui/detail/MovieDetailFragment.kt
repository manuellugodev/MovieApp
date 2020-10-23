package com.manuellugodev.movie.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import com.manuellugodev.movie.R
import com.manuellugodev.movie.data.detail.RepositoryMovieDetail
import com.manuellugodev.movie.domain.model.MovieDetail
import com.manuellugodev.movie.retrofit.data.requests.movieDetail.MovieDetailRequest
import com.manuellugodev.movie.retrofit.sources.SourceMovieDetailImplRetrofit
import com.manuellugodev.movie.usecases.GetMovieDetailByIdUseCase
import com.manuellugodev.movie.usecases.GetMovieImagesByIdUseCase
import com.manuellugodev.movie.vo.DataResult
import kotlinx.android.synthetic.main.fragment_movie_detail.*


class MovieDetailFragment : Fragment() {

    private var idMovie=0

    private val MOVIEID="IDMOVIE"


    private val sourceMovieDetail =
        SourceMovieDetailImplRetrofit(MovieDetailRequest(("https://api.themoviedb.org/3/")))
    private val repository = RepositoryMovieDetail(sourceMovieDetail)

    private val getMovieDetailByIdUseCase = GetMovieDetailByIdUseCase(repository)
    private val getMovieImagesByIdUseCase = GetMovieImagesByIdUseCase(repository)

    private val viewModel by viewModels<MovieDetailViewModel> {
        MovieDetailViewModelFactory(
            getMovieDetailByIdUseCase,
            getMovieImagesByIdUseCase
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireArguments().let {
            idMovie=it.getInt("IDMOVIE")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        attachObserver()

        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    private fun attachObserver(){
        viewModel.fetchMovieDetail(idMovie).observe(viewLifecycleOwner,::updateMovieDetail)
    }

    private fun updateMovieDetail(movie:DataResult<MovieDetail>){

        when(movie){

            is DataResult.Loading->{Toast.makeText(requireContext(),"Cargando...",Toast.LENGTH_LONG).show()}

            is DataResult.Success->{
                val movieData=movie.data

                movieTitle.text=movieData.title
                movieDuration.text=movieData.duration.toString()
                movieGenres.text=movieData.toString()

                Glide.with(requireContext()).load("https://image.tmdb.org/t/p/w500/${movieData.backdrop}").centerCrop().into(fondo)



            }

            is DataResult.Failure->{}
        }

    }


}