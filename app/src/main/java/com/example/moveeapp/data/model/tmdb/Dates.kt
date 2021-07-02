package com.example.moveeapp.data.model.tmdb


import com.example.moveeapp.data.components.network.base.ModelConstants
import com.google.gson.annotations.SerializedName

data class Dates(
    @SerializedName(ModelConstants.VISION_MAXIMUM)
    val maximum: String,
    @SerializedName(ModelConstants.VISION_MINIMUM)
    val minimum: String
)