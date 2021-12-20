package com.manuellugodev.movie.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.manuellugodev.movie.MovieApplication
import com.manuellugodev.movie.R
import com.manuellugodev.movie.databinding.FragmentHomeBinding
import com.manuellugodev.movie.domain.model.Category
import com.manuellugodev.movie.domain.model.Movie
import com.manuellugodev.movie.usecases.GetPopularMovieUseCase
import com.manuellugodev.movie.usecases.GetTopRatedMovieUseCase
import javax.inject.Inject

class HomeFragment : Fragment(), AdapterListMovies.OnMovieClickListener {

    private var _bindingHome: FragmentHomeBinding? = null
    private val bindingHome get() = _bindingHome!!

    @Inject
    lateinit var getTopRatedMovieUseCase: GetTopRatedMovieUseCase

    @Inject
    lateinit var getPopularMovieUseCase: GetPopularMovieUseCase


     val homeViewModel by viewModels<HomeViewModel> {
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
        _bindingHome= FragmentHomeBinding.inflate(inflater,container,false)
        return bindingHome.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
    }


    private fun setupViewPager(){
        val categorys=arrayOf(Category.POPULAR, Category.TOP_RATED)
        val adapterCategory=HomeAdapter(this, categorys)
        bindingHome.viewPagerHome.adapter=adapterCategory
        TabLayoutMediator(bindingHome.tabPage,bindingHome.viewPagerHome){
            tab, position ->
            tab.text= categorys[position].title
        }.attach()

    }

    override fun onMovieClick(movie: Movie) {
        val bundle = Bundle()

        bundle.putInt("IDMOVIE", movie.id)

        findNavController().navigate(R.id.action_navigation_home_to_movieDetailFragment, bundle)
    }
}