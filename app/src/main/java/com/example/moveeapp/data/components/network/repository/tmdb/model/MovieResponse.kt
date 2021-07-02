package com.example.moveeapp.data.components.network.repository.tmdb.model

import android.os.Parcelable
import com.example.moveeapp.data.components.network.base.ModelConstants
import com.example.moveeapp.data.model.tmdb.Movie
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class MovieResponse (
    @SerializedName(ModelConstants.PAGE)
    val page: Int,
    @SerializedName(ModelConstants.RESULTS)
    val results: List<Movie>,
    @SerializedName(ModelConstants.TOTAL_PAGES)
    val totalPages: Int,
    @SerializedName(ModelConstants.TOTAL_RESULTS)
    val totalResults: Int
): Parcelable