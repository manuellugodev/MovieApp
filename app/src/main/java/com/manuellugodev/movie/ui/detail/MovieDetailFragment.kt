package com.manuellugodev.movie.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import com.manuellugodev.movie.data.detail.RepositoryMovieDetail
import com.manuellugodev.movie.databinding.FragmentMovieDetailBinding
import com.manuellugodev.movie.domain.model.MovieDetail
import com.manuellugodev.movie.retrofit.data.requests.movieDetail.MovieDetailRequest
import com.manuellugodev.movie.retrofit.sources.RemoteSourceMovieDetail
import com.manuellugodev.movie.usecases.GetMovieDetailByIdUseCase
import com.manuellugodev.movie.usecases.GetMovieImagesByIdUseCase
import com.manuellugodev.movie.vo.DataResult
import java.lang.StringBuilder


class MovieDetailFragment : Fragment() {


    private var _bindingDetail: FragmentMovieDetailBinding? = null
    private val bindingDetail get() = _bindingDetail!!

    private var idMovie = 0

    private val MOVIEID = "IDMOVIE"


    private val sourceMovieDetail =
        RemoteSourceMovieDetail(MovieDetailRequest(("https://api.themoviedb.org/3/")))
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
            idMovie = it.getInt("IDMOVIE")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        attachObserver()

        _bindingDetail = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return bindingDetail.root
    }


    private fun attachObserver() {
        viewModel.fetchMovieDetail(idMovie).observe(viewLifecycleOwner, ::updateMovieDetail)
    }

    private fun updateMovieDetail(movie: DataResult<MovieDetail>) {

        when (movie) {

            is DataResult.Loading -> {
                bindingDetail.progressMovieDetail.visibility = View.VISIBLE
                bindingDetail.ScrollMovieDetail.visibility = View.INVISIBLE
            }

            is DataResult.Success -> {

                bindingDetail.progressMovieDetail.visibility = View.GONE
                bindingDetail.ScrollMovieDetail.visibility = View.VISIBLE
                val movieData = movie.data
                val listGenres: StringBuilder = StringBuilder()
                movieData.genres.map { listGenres.append("-${it.name}") }

                Glide.with(requireContext())
                    .load("https://image.tmdb.org/t/p/w1280/${movieData.backdrop}").centerCrop()
                    .into(bindingDetail.fondo)


                bindingDetail.apply {
                    movieTitle.text = movieData.title
                    movieStatus.text = movieData.status
                    movieAverage.text = movieData.voteAverage.toString()
                    movieSypnosis.text = movieData.overview
                    movieDuration.text = movieData.duration.toString() + " m"
                    movieGenres.text = listGenres.toString()
                    movieDate.text = movieData.releaseDate
                }

            }

            is DataResult.Failure -> {
                bindingDetail.progressMovieDetail.visibility = View.GONE
            }
        }

    }


}