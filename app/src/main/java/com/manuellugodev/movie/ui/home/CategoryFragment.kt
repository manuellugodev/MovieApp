package com.manuellugodev.movie.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.manuellugodev.movie.databinding.FragmentCategoryBinding
import com.manuellugodev.movie.domain.model.Category
import com.manuellugodev.movie.domain.model.Movie
import com.manuellugodev.movie.vo.DataResult


class CategoryFragment : Fragment() {

    private lateinit var bindingCategory:FragmentCategoryBinding
    private lateinit var title:String
    private lateinit var parentHomeFragment:HomeFragment
    private lateinit var adapterMovies:AdapterListMovies

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingCategory= FragmentCategoryBinding.inflate(inflater,container,false)
        return bindingCategory.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        title= arguments?.get("type").toString()
        parentHomeFragment=parentFragment as HomeFragment
        setupRecyclers()
        when(title){
            Category.TOP_RATED.name->{
                parentHomeFragment.homeViewModel.fetchMovieTopRated.observe(viewLifecycleOwner,::updateAdapter)
            }
            Category.POPULAR.name->{
                parentHomeFragment.homeViewModel.fetchMoviePopularList.observe(viewLifecycleOwner,::updateAdapter)

            }
        }


    }

    private fun setupRecyclers() {
        bindingCategory.rvCategory.layoutManager =
            GridLayoutManager(requireContext(), 2)
        adapterMovies=AdapterListMovies(requireContext(), listOf(),parentHomeFragment)
        bindingCategory.rvCategory.adapter=adapterMovies
    }

    private fun updateAdapter(listMovie: DataResult<List<Movie>>) {
        when (listMovie) {
            is DataResult.Success -> {
                adapterMovies.updateData(listMovie.data)
                hideProgress()
            }
            is DataResult.Loading -> {
                showProgress()
            }
            is DataResult.Failure -> {
                showError("Error de Tipo: ${listMovie.exception}")
            }
        }
    }

    private fun showError(messageError: String) {
        Toast.makeText(requireContext(), messageError, Toast.LENGTH_LONG).show()
    }

    private fun showProgress() {
        bindingCategory.progressCategory.visibility=View.VISIBLE
    }

    private fun hideProgress() {
        bindingCategory.progressCategory.visibility=View.GONE
    }
}