package com.example.moveeapp.data.components.network.repository.tmdb.model


import com.example.moveeapp.data.components.network.base.ModelConstants
import com.google.gson.annotations.SerializedName

data class PeopleDetailsResponse(
    @SerializedName(ModelConstants.DETAILS_ADULT)
    val adult: Boolean,
    @SerializedName(ModelConstants.DETAILS_AKA)
    val alsoKnownAs: List<Any>,
    @SerializedName(ModelConstants.DETAILS_BIOGRAPHY)
    val biography: String,
    @SerializedName(ModelConstants.DETAILS_BIRTHDAY)
    val birthday: String,
    @SerializedName(ModelConstants.DETAILS_DEATHDAY)
    val deathday: Any,
    @SerializedName(ModelConstants.DETAILS_GENDER)
    val gender: Int,
    @SerializedName(ModelConstants.DETAILS_HOMEPAGE)
    val homepage: String,
    @SerializedName(ModelConstants.DETAILS_ID)
    val id: Int,
    @SerializedName(ModelConstants.DETAILS_IMDB_ID)
    val imdbId: String,
    @SerializedName(ModelConstants.DETAILS_KNOWN_FOR_DEPARTMENT)
    val knownForDepartment: String,
    @SerializedName(ModelConstants.DETAILS_NAME)
    val name: String,
    @SerializedName(ModelConstants.DETAILS_BIRTH_PLACE)
    val placeOfBirth: String,
    @SerializedName(ModelConstants.DETAILS_POPULARITY)
    val popularity: Double,
    @SerializedName(ModelConstants.DETAILS_PROFILE_PATH)
    val profilePath: String
)