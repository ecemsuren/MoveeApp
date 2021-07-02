package com.example.moveeapp.data.components.network.repository.tmdb.api

import com.example.moveeapp.data.model.tmdb.Movie
import com.example.moveeapp.data.model.tmdb.TvSeries

/**
 * Created by Ecem Suren on 8.11.2020.
 */
object TMDBRatedSingleton {

    var ratedMovie: List<Movie> = emptyList()
    var ratedTvSeries: List <TvSeries> = emptyList()
}