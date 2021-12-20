package com.manuellugodev.movie.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.manuellugodev.movie.domain.model.Category

class HomeAdapter(fragment: Fragment, private val categorys: Array<Category>) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = categorys.size

    override fun createFragment(position: Int): Fragment {
        val category = CategoryFragment()
        when (categorys[position]) {

            Category.POPULAR -> {
                category.arguments = Bundle().apply {
                    putString("type", Category.POPULAR.name)
                }

            }
            Category.TOP_RATED -> {
                category.arguments = Bundle().apply {
                    putString("type", Category.TOP_RATED.name)
                }

            }

        }
        return category
    }

}