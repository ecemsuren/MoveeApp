package com.example.moveeapp.data.model.googlemap


import com.example.moveeapp.data.components.network.base.ModelConstants
import com.example.moveeapp.data.model.googlemap.Location
import com.google.gson.annotations.SerializedName

data class Geometry(
    @SerializedName(ModelConstants.LOCATION)
    val location: Location
)