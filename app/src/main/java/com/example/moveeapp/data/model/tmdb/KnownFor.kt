package com.example.moveeapp.data.model.tmdb

import android.os.Parcelable
import android.view.View
import com.example.moveeapp.R
import com.example.moveeapp.data.components.network.base.ModelConstants
import com.example.moveeapp.ui.components.movierecyclerview.SearchRecyclerViewHolder
import com.example.moveeapp.ui.components.movierecyclerview.genericadapter.BaseRecyclerviewItem
import com.example.moveeapp.ui.components.movierecyclerview.genericadapter.BaseViewHolder
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class KnownFor(
    @SerializedName(ModelConstants.SEARCH_ADULT)
    val adult: Boolean,
    @SerializedName(ModelConstants.SEARCH_BACKDROP_PATH)
    val backdropPath: String,
    @SerializedName(ModelConstants.SEARCH_FIRST_AIR_DATE)
    val firstAirDate: String?,
    @SerializedName(ModelConstants.SEARCH_GENRE)
    val genreIds: List<Int>,
    @SerializedName(ModelConstants.SEARCH_ID)
    val id: Int,
    @SerializedName(ModelConstants.SEARCH_MEDIA_TYPE)
    val mediaType: String,
    @SerializedName(ModelConstants.SEARCH_NAME)
    val name: String,
    @SerializedName(ModelConstants.SEARCH_COUNTRY)
    val originCountry: List<String>,
    @SerializedName(ModelConstants.SEARCH_LANGUAGE)
    val originalLanguage: String,
    @SerializedName(ModelConstants.SEARCH_ORIGINAL_NAME)
    val originalName: String,
    @SerializedName(ModelConstants.SEARCH_ORIGINAL_TITLE)
    val originalTitle: String,
    @SerializedName(ModelConstants.SEARCH_OVERVIEW)
    val overview: String,
    @SerializedName(ModelConstants.SEARCH_POPULARITY)
    val popularity: Double,
    @SerializedName(ModelConstants.SEARCH_POSTER)
    val posterPath: String,
    @SerializedName(ModelConstants.SEARCH_RELEASE_DATE)
    val releaseDate: String?,
    @SerializedName(ModelConstants.SEARCH_TITLE)
    val title: String,
    @SerializedName(ModelConstants.SEARCH_VIDEO)
    val video: Boolean,
    @SerializedName(ModelConstants.SEARCH_VOTE_AVERAGE)
    val voteAverage: Double,
    @SerializedName(ModelConstants.SEARCH_VOTE_COUNT)
    val voteCount: Int
): Parcelable, BaseRecyclerviewItem() {
    override fun getHolder(view: View): BaseViewHolder {
        return SearchRecyclerViewHolder(view, null, this)
    }

    override fun getLayout(): Int {
        return R.layout.recyclerview_item_search
    }
}