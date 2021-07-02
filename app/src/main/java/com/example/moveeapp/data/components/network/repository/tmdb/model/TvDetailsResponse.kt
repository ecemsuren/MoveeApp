package com.example.moveeapp.data.components.network.repository.tmdb.model


import com.example.moveeapp.data.components.network.base.ModelConstants
import com.example.moveeapp.data.model.tmdb.Genre
import com.google.gson.annotations.SerializedName


data class TvDetailsResponse(
    @SerializedName(ModelConstants.DETAILS_BACKDROP_PATH)
    val backdropPath: String,
    @SerializedName(ModelConstants.DETAILS_EPISODE_RUN_TIME)
    val episodeRunTime: List<Int>,
    @SerializedName(ModelConstants.DETAILS_FIRST_AIR_DATE)
    val firstAirDate: String,
    @SerializedName(ModelConstants.GENRES)
    val genres: List<Genre>,
    @SerializedName(ModelConstants.DETAILS_HOMEPAGE)
    val homepage: String,
    @SerializedName(ModelConstants.DETAILS_ID)
    val id: Int,
    @SerializedName(ModelConstants.DETAILS_IN_PRODUCTION)
    val inProduction: Boolean,
    @SerializedName(ModelConstants.DETAILS_LANGUAGE)
    val languages: List<String>,
    @SerializedName(ModelConstants.DETAILS_LAST_AIR_DATE)
    val lastAirDate: String,
    @SerializedName(ModelConstants.DETAILS_NAME)
    val name: String,
    @SerializedName(ModelConstants.DETAILS_NEXT_EPISODE_AIR)
    val nextEpisodeToAir: Any,
    @SerializedName(ModelConstants.DETAILS_NUMBER_OF_EPISODES)
    val numberOfEpisodes: Int,
    @SerializedName(ModelConstants.DETAILS_NUMBER_OF_SEASONS)
    val numberOfSeasons: Int,
    @SerializedName(ModelConstants.DETAILS_ORIGIN_COUNTRY)
    val originCountry: List<String>,
    @SerializedName(ModelConstants.DETAILS_ORIGIN_LANGUAGE)
    val originalLanguage: String,
    @SerializedName(ModelConstants.DETAILS_ORIGINAL_NAME)
    val originalName: String,
    @SerializedName(ModelConstants.DETAILS_OVERVIEW)
    val overview: String,
    @SerializedName(ModelConstants.DETAILS_POPULARITY)
    val popularity: Double,
    @SerializedName(ModelConstants.DETAILS_POSTER_PATH)
    val posterPath: String,
    @SerializedName(ModelConstants.DETAILS_STATUS)
    val status: String,
    @SerializedName(ModelConstants.DETAILS_TYPE)
    val type: String,
    @SerializedName(ModelConstants.DETAILS_VOTE_AVERAGE)
    val voteAverage: Double,
    @SerializedName(ModelConstants.DETAILS_VOTE_COUNT)
    val voteCount: Int
)