package com.example.moveeapp.data.model.tmdb


import com.example.moveeapp.data.components.network.base.ModelConstants
import com.example.moveeapp.data.model.googlemap.Geometry
import com.google.gson.annotations.SerializedName

data class Cinema(
    @SerializedName(ModelConstants.GEOMETRY)
    val geometry: Geometry,
    @SerializedName(ModelConstants.MAP_NAME)
    val name: String,
    @SerializedName(ModelConstants.MAP_PLACE_ID)
    val placeId: String

)