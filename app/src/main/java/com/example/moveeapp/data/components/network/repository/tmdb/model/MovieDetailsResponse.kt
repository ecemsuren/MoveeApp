package com.example.moveeapp.data.components.network.repository.tmdb.model


import com.example.moveeapp.data.components.network.base.ModelConstants
import com.example.moveeapp.data.model.tmdb.Genre
import com.google.gson.annotations.SerializedName

data class MovieDetailsResponse(
    @SerializedName(ModelConstants.DETAILS_ADULT)
    val adult: Boolean,
    @SerializedName(ModelConstants.DETAILS_BACKDROP_PATH)
    val backdropPath: String,
    @SerializedName(ModelConstants.DETAILS_BELONGS_COLLECTION)
    val belongsToCollection: Any,
    @SerializedName(ModelConstants.DETAILS_BUDGET)
    val budget: Int,
    @SerializedName(ModelConstants.GENRES)
    val genres: List<Genre>,
    @SerializedName(ModelConstants.DETAILS_HOMEPAGE)
    val homepage: String,
    @SerializedName(ModelConstants.DETAILS_ID)
    val id: Int,
    @SerializedName(ModelConstants.DETAILS_IMDB_ID)
    val imdbId: String,
    @SerializedName(ModelConstants.DETAILS_ORIGIN_LANGUAGE)
    val originalLanguage: String,
    @SerializedName(ModelConstants.DETAILS_ORIGINAL_TITLE)
    val originalTitle: String,
    @SerializedName(ModelConstants.DETAILS_OVERVIEW)
    val overview: String,
    @SerializedName(ModelConstants.DETAILS_POPULARITY)
    val popularity: Double,
    @SerializedName(ModelConstants.DETAILS_POSTER_PATH)
    val posterPath: String,
    @SerializedName(ModelConstants.DETAILS_RELEASE_DATE)
    val releaseDate: String,
    @SerializedName(ModelConstants.DETAILS_REVENUE)
    val revenue: Int,
    @SerializedName(ModelConstants.DETAILS_RUN_TIME)
    val runtime: Int,
    @SerializedName(ModelConstants.DETAILS_STATUS)
    val status: String,
    @SerializedName(ModelConstants.DETAILS_TAGLINE)
    val tagline: String,
    @SerializedName(ModelConstants.DETAILS_TITLE)
    val title: String,
    @SerializedName(ModelConstants.DETAILS_VIDEO)
    val video: Boolean,
    @SerializedName(ModelConstants.DETAILS_VOTE_AVERAGE)
    val voteAverage: Double,
    @SerializedName(ModelConstants.DETAILS_VOTE_COUNT)
    val voteCount: Int
)