package com.example.moveeapp.ui.model

import android.os.Parcelable
import android.view.View
import com.example.moveeapp.R
import com.example.moveeapp.data.model.tmdb.Movie
import com.example.moveeapp.ui.components.movierecyclerview.VisionsFilmsRecyclerViewHolder
import com.example.moveeapp.ui.components.movierecyclerview.genericadapter.BaseRecyclerviewItem
import com.example.moveeapp.ui.components.movierecyclerview.genericadapter.BaseViewHolder
import kotlinx.android.parcel.Parcelize

/**
 * Created by Ecem Suren on 18.10.2020.
 */
@Parcelize
data class CarouselMovieItem (val movie: Movie): Parcelable, BaseRecyclerviewItem() {
    override fun getHolder(view: View): BaseViewHolder {
        return VisionsFilmsRecyclerViewHolder(view, this)
    }

    override fun getLayout(): Int {
        return R.layout.recycleview_item_carousel

    }
}




