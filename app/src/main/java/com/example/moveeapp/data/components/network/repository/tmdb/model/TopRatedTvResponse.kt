package com.example.moveeapp.data.components.network.repository.tmdb.model


import android.os.Parcelable
import com.example.moveeapp.data.components.network.base.ModelConstants
import com.example.moveeapp.data.model.tmdb.TvSeries
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TopRatedTvResponse(
    @SerializedName(ModelConstants.TOP_RATED_PAGE)
    val page: Int,
    @SerializedName(ModelConstants.TOP_RATED_RESULT)
    val results: List<TvSeries>,
    @SerializedName(ModelConstants.TOP_RATED_TOTAL_PAGES)
    val totalPages: Int,
    @SerializedName(ModelConstants.TOP_RATED_TOTAL_RESULT)
    val totalResults: Int
): Parcelable