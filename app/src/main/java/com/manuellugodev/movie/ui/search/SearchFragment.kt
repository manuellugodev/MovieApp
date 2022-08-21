package com.manuellugodev.movie.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.manuellugodev.movie.MovieApplication
import com.manuellugodev.movie.R
import com.manuellugodev.movie.databinding.FragmentSearchBinding
import com.manuellugodev.movie.domain.model.Movie
import com.manuellugodev.movie.ui.SearchListener
import com.manuellugodev.movie.ui.home.AdapterListMovies
import com.manuellugodev.movie.usecases.GetMovieBySearchUseCase
import com.manuellugodev.movie.vo.DataResult
import javax.inject.Inject

class SearchFragment : Fragment(), AdapterListMovies.OnMovieClickListener {

    private var _bindingSearch:FragmentSearchBinding ?= null
    private val bindingSearch get() = _bindingSearch
    private lateinit var adapterMovies:AdapterListMovies

    @Inject
    lateinit var getMovieBySearchUseCase:GetMovieBySearchUseCase

    val searchViewModel by viewModels<SearchViewModel> {
        SearchViewModelFactory(getMovieBySearchUseCase)
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

        _bindingSearch = FragmentSearchBinding.inflate(inflater, container, false)


        return bindingSearch?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSearch()
        setupRecyclers()
        setupObservers()
    }

    private fun setupSearch() {

        val listener=SearchListener(searchViewModel::search)
        bindingSearch?.searchView?.setOnQueryTextListener(listener)
    }

    private fun setupRecyclers() {
        bindingSearch?.rvMovies?.layoutManager =
            GridLayoutManager(requireContext(), 2)
        adapterMovies= AdapterListMovies(requireContext(), listOf(),this)
        bindingSearch?.rvMovies?.adapter=adapterMovies
    }

    private fun setupObservers() {
        searchViewModel.searchResult.observe(viewLifecycleOwner,::updateListMovie)
    }

    private fun updateListMovie(dataResult: DataResult<List<Movie>>?) {
        when(dataResult){
            is DataResult.Loading->{
                showLoader()
            }
            is DataResult.Success->{
                hideLoader()
                showResults(dataResult.data)
            }
            is DataResult.Failure->{
                hideLoader()
            }
        }
    }

    private fun showResults(movies:List<Movie>) {
        adapterMovies.updateData(movies)
    }

    private fun hideLoader() {
        bindingSearch?.progresSearch?.visibility=View.GONE
    }

    private fun showLoader() {
        bindingSearch?.progresSearch?.visibility=View.VISIBLE
    }

    override fun onMovieClick(movie: Movie) {
        val bundle = Bundle()

        bundle.putInt("IDMOVIE", movie.id)

        findNavController().navigate(R.id.action_navigation_dashboard_to_movieDetailFragment, bundle)    }
}