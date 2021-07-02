package com.example.moveeapp.ui.model

import android.os.Parcelable
import android.view.View
import com.example.moveeapp.R
import com.example.moveeapp.data.model.tmdb.TvSeries
import com.example.moveeapp.ui.components.movierecyclerview.VisionsFilmsRecyclerViewHolder
import com.example.moveeapp.ui.components.movierecyclerview.genericadapter.BaseRecyclerviewItem
import com.example.moveeapp.ui.components.movierecyclerview.genericadapter.BaseViewHolder
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CarauselTvSeriesItem(val tvSeries: TvSeries): Parcelable, BaseRecyclerviewItem() {
    override fun getHolder(view: View): BaseViewHolder {
        return VisionsFilmsRecyclerViewHolder(view, this)
    }

    override fun getLayout(): Int {
       return R.layout.recycleview_item_carousel
    }
}