package com.example.moveeapp.data.components.network.repository.tmdb.model


import com.example.moveeapp.data.components.network.base.ModelConstants
import com.example.moveeapp.data.model.tmdb.Dates
import com.example.moveeapp.data.model.tmdb.Movie
import com.google.gson.annotations.SerializedName

data class VisionsMovieResponse(
    @SerializedName(ModelConstants.VISIONS_DATE)
    val dates: Dates,
    @SerializedName(ModelConstants.VISIONS_PAGE)
    val page: Int,
    @SerializedName(ModelConstants.VISIONS_RESULTS)
    val results: List<Movie>,
    @SerializedName(ModelConstants.VISIONS_TOTAL_PAGES)
    val totalPages: Int,
    @SerializedName(ModelConstants.VISIONS_TOTAL_RESULT)
    val totalResults: Int
)