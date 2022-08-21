package com.manuellugodev.movie.ui


class SearchListener(private val action:(String)-> Unit):android.widget.SearchView.OnQueryTextListener{

    private var word=""
    override fun onQueryTextSubmit(query: String?): Boolean {

        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if(newText!==word){
            word=newText.toString()
            action(newText?:"")
        }
        return true
    }


}