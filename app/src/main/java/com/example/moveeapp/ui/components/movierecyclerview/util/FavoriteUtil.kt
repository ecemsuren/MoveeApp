package com.example.moveeapp.ui.components.movierecyclerview.util

import android.view.View
import com.example.moveeapp.data.components.network.repository.tmdb.api.TMDBApiFavoriteMovieListSingleton

/**
 * Created by Ecem Suren on 5.11.2020.
 */
object FavoriteUtil {

    fun checkFavoriteVisibility(id: Int): Int{
        TMDBApiFavoriteMovieListSingleton.favoriteMovieList.forEach { favoriteItem ->
            if (id == favoriteItem.favoriteMovie.id) {
                return View.VISIBLE
            }
        }
       return View.INVISIBLE
    }
}