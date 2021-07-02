package com.example.moveeapp.data.model.googlemap


import com.example.moveeapp.data.components.network.base.ModelConstants
import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName(ModelConstants.LAT)
    val lat: Double,
    @SerializedName(ModelConstants.LNG)
    val lng: Double
)