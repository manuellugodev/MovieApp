package com.manuellugodev.movie.data.home

import com.manuellugodev.movie.vo.Resource

class interactorHome {
    fun getMovies(): Resource<List<Movie>> {

        val movies: List<Movie>
        var movie1 = Movie().apply {

            name = "Son como niños 2"

            image =
                "https://vignette.wikia.nocookie.net/doblaje/images/3/39/Son_Como_Ni_os_2_Poster_Latino_Cine_1.jpg/revision/latest?cb=20130507201021&path-prefix=es"

            imagePreview =
                "https://occ-0-2431-3934.1.nflxso.net/dnm/api/v6/X194eJsgWBDE2aQbaNdmCXGUP-Y/AAAABWPxa8gtdCqDHnf1Kcp95oi4L0GSbbiqOAQ7CMafKSeBIOgsIyJMN8OE_PCvVVgHLSLQR_hVAEhEnMloE9G5Ex8Z-1Du.jpg?r=2e2"

            sipnosis =
                "Lenny (Adam Sandler) se ha mudado con su familia a la pequeña ciudad donde tanto él como sus amigos se criaron. En esta ocasión, los adultos serán quienes reciban toda una lección de sus propios hijos en un día que estará lleno de sorpresas: el último día de clases, con una súper fiesta."

            category = "Comedia"


        }

       movies = listOf(movie1, movie1, movie1, movie1, movie1, movie1, movie1, movie1)


        return Resource.Success(movies)
    }


}