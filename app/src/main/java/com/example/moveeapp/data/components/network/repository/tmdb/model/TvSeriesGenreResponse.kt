package com.example.moveeapp.data.components.network.repository.tmdb.model


import com.example.moveeapp.data.components.network.base.ModelConstants
import com.example.moveeapp.data.model.tmdb.Genre
import com.google.gson.annotations.SerializedName

data class TvSeriesGenreResponse(
    @SerializedName(ModelConstants.GENRES)
    val genres: List<Genre>
)