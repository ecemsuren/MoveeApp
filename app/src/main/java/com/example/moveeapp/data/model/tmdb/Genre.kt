package com.example.moveeapp.data.model.tmdb

import android.os.Parcelable
import com.example.moveeapp.data.components.network.base.ModelConstants
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Genre (
    @SerializedName(ModelConstants.GENRE_ID)
    val id: Int,
    @SerializedName(ModelConstants.NAME)
    val name: String
) : Parcelable
