package com.example.moveeapp.ui.model

import android.view.View
import com.example.moveeapp.R
import com.example.moveeapp.data.model.tmdb.Movie
import com.example.moveeapp.ui.components.movierecyclerview.FavoriteMovieRecyclerViewHolder
import com.example.moveeapp.ui.components.movierecyclerview.genericadapter.BaseRecyclerviewItem
import com.example.moveeapp.ui.components.movierecyclerview.genericadapter.BaseViewHolder

/**
 * Created by Ecem Suren on 3.11.2020.
 */
data class FavoriteMovieItem(val favoriteMovie: Movie): BaseRecyclerviewItem() {
    override fun getHolder(view: View): BaseViewHolder {
        return FavoriteMovieRecyclerViewHolder(view, this)
    }

    override fun getLayout(): Int {
       return R.layout.recyclerview_item_favorite_movie

    }
}