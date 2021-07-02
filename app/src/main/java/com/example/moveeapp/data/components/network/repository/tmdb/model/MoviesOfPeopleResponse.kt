package com.example.moveeapp.data.components.network.repository.tmdb.model


import com.example.moveeapp.data.components.network.base.ModelConstants
import com.example.moveeapp.data.model.tmdb.Movie
import com.google.gson.annotations.SerializedName

data class MoviesOfPeopleResponse(
    @SerializedName(ModelConstants.MOVIES_CAST)
    val cast: List<Movie>,
    @SerializedName(ModelConstants.MOVIES_CREW)
    val crew: List<Crew>,
    @SerializedName(ModelConstants.MOVIES_PEOPLE_ID)
    val id: Int
)