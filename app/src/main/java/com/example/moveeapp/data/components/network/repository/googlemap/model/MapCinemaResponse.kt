package com.example.moveeapp.data.components.network.repository.googlemap.model


import com.example.moveeapp.data.components.network.base.ModelConstants
import com.example.moveeapp.data.model.tmdb.Cinema
import com.google.gson.annotations.SerializedName

data class MapCinemaResponse(
    @SerializedName(ModelConstants.MAP_RESULTS)
    val results: List<Cinema>

)