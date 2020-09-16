package com.manuellugodev.movie.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.manuellugodev.movie.R
import com.manuellugodev.movie.data.home.Movie

class adapterListMovies(val context:Context,private val listMovie:List<Movie>,private val itemClickListener:OnMovieClickListener):RecyclerView.Adapter<adapterListMovies.MyViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): adapterListMovies.MyViewHolder {
       return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false))
    }

    override fun getItemCount(): Int {
      return  listMovie.size
    }

    override fun onBindViewHolder(holder: adapterListMovies.MyViewHolder, position: Int) {
        holder.imageMovie.setOnClickListener {

            itemClickListener.onMovieClick(listMovie[position])
        }

        Glide.with(context).load(listMovie[position].image).into(holder.imageMovie)
    }

    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        var imageMovie:ImageView

        init {
            imageMovie=itemView.findViewById(R.id.coverMovie)

        }


    }

    interface OnMovieClickListener{

        fun onMovieClick(movie:Movie)
    }
}