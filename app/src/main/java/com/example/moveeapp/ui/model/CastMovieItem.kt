package com.example.moveeapp.ui.model

import android.os.Parcelable
import android.view.View
import com.example.moveeapp.R
import com.example.moveeapp.data.model.tmdb.Movie
import com.example.moveeapp.ui.components.movierecyclerview.MoviesOfCastRecyclerViewHolder
import com.example.moveeapp.ui.components.movierecyclerview.genericadapter.BaseRecyclerviewItem
import com.example.moveeapp.ui.components.movierecyclerview.genericadapter.BaseViewHolder
import kotlinx.android.parcel.Parcelize

/**
 * Created by Ecem Suren on 17.10.2020.
 */
@Parcelize
data class CastMovieItem(val movie: Movie): Parcelable, BaseRecyclerviewItem(){
    override fun getHolder(view: View): BaseViewHolder {
        return MoviesOfCastRecyclerViewHolder(view, this)
    }

    override fun getLayout(): Int {
        return R.layout.recyclerview_item_cast_movies

    }
}