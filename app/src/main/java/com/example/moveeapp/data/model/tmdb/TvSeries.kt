package com.example.moveeapp.data.model.tmdb


import android.os.Parcelable
import android.view.View
import com.example.moveeapp.R
import com.example.moveeapp.data.components.network.base.ModelConstants
import com.example.moveeapp.ui.components.movierecyclerview.TopRatedViewHolder
import com.example.moveeapp.ui.components.movierecyclerview.genericadapter.BaseRecyclerviewItem
import com.example.moveeapp.ui.components.movierecyclerview.genericadapter.BaseViewHolder
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvSeries(
    @SerializedName(ModelConstants.TV_SERIES_BACKDROP_PATH)
    val backdropPath: String,
    @SerializedName(ModelConstants.TV_SERIES_FIRST_AIR_DATE)
    val firstAirDate: String,
    @SerializedName(ModelConstants.TV_SERIES_GENRE_IDS)
    val genreIds: List<Int>,
    @SerializedName(ModelConstants.TV_SERIES_ID)
    val id: Int,
    @SerializedName(ModelConstants.TV_SERIES_NAME)
    val name: String,
    @SerializedName(ModelConstants.TV_SERIES_ORIGIN_COUNTRY)
    val originCountry: List<String>,
    @SerializedName(ModelConstants.TV_SERIES_ORIGINAL_LANGUAGE)
    val originalLanguage: String,
    @SerializedName(ModelConstants.TV_SERIES_ORIGINAL_NAME)
    val originalName: String,
    @SerializedName(ModelConstants.TV_SERIES_OVERVIEW)
    val overview: String,
    @SerializedName(ModelConstants.TV_SERIES_POPULARITY)
    val popularity: Double,
    @SerializedName(ModelConstants.TV_SERIES_POSTER_PATH)
    val posterPath: String,
    @SerializedName(ModelConstants.TV_SERIES_VOTE_AVERAGE)
    val voteAverage: Double,
    @SerializedName(ModelConstants.TV_SERIES_VOTE_COUNT)
    val voteCount: Int,
    val rating: Int = ModelConstants.ZERO
): Parcelable, BaseRecyclerviewItem() {
    override fun getHolder(view: View): BaseViewHolder {
        return TopRatedViewHolder(
            view,
            this
        )
    }

    override fun getLayout(): Int {
       return R.layout.recyclerview_item_tv_series
    }
}