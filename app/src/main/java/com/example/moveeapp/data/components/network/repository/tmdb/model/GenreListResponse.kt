package com.example.moveeapp.data.components.network.repository.tmdb.model

import android.os.Parcelable
import com.example.moveeapp.data.components.network.base.ModelConstants
import com.example.moveeapp.data.model.tmdb.Genre
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GenreListResponse (
    @SerializedName(ModelConstants.GENRES)
    val genres: List<Genre>
): Parcelable