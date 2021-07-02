package com.example.moveeapp.data.model.tmdb

import android.os.Parcelable
import android.view.View
import com.example.moveeapp.R
import com.example.moveeapp.data.components.network.base.ModelConstants
import com.example.moveeapp.ui.components.movierecyclerview.MovieRecyclerViewHolder
import com.example.moveeapp.ui.components.movierecyclerview.genericadapter.BaseRecyclerviewItem
import com.example.moveeapp.ui.components.movierecyclerview.genericadapter.BaseViewHolder
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    @SerializedName(ModelConstants.ADULT)
    val adult: Boolean,
    @SerializedName(ModelConstants.BACKDROP_PATH)
    val backdropPath: String,
    @SerializedName(ModelConstants.GENRE_IDS)
    val genreIds: List<Int>,
    @SerializedName(ModelConstants.ID_POPULAR)
    val id: Int,
    @SerializedName(ModelConstants.ORIGINAL_LANGUAGE)
    val originalLanguage: String,
    @SerializedName(ModelConstants.ORIGINAL_TITLE)
    val originalTitle: String,
    @SerializedName(ModelConstants.OVERVIEW)
    val overview: String,
    @SerializedName(ModelConstants.POPULARITY)
    val popularity: Double,
    @SerializedName(ModelConstants.POSTER_PATH)
    val posterPath: String,
    @SerializedName(ModelConstants.RELEASE_DATE)
    val releaseDate: String,
    @SerializedName(ModelConstants.TITLE)
    val title: String,
    @SerializedName(ModelConstants.VIDEO)
    val video: Boolean,
    @SerializedName(ModelConstants.VOTE_AVERAGE)
    val voteAverage: Double,
    @SerializedName(ModelConstants.VOTE_COUNT)
    val voteCount: Int,
    @SerializedName("rating")
    val rating: Int = ModelConstants.ZERO,
    var isFavorite: Boolean = false
): Parcelable, BaseRecyclerviewItem() {

    override fun getHolder(view: View): BaseViewHolder {
        return MovieRecyclerViewHolder(view, this)
    }

    override fun getLayout(): Int {
        return R.layout.recyclerview_item_movie
    }

}