package com.example.moveeapp.data.components.network.repository.tmdb.model


import com.example.moveeapp.data.components.network.base.ModelConstants
import com.example.moveeapp.data.model.tmdb.Search
import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName(ModelConstants.PAGE)
    val page: Int,
    @SerializedName(ModelConstants.RESULTS)
    val results: List<Search>,
    @SerializedName(ModelConstants.TOTAL_PAGES)
    val totalPages: Int,
    @SerializedName(ModelConstants.TOTAL_RESULTS)
    val totalResults: Int
)