package com.example.moveeapp.data.model.googlemap


import com.example.moveeapp.data.components.network.base.ModelConstants
import com.google.gson.annotations.SerializedName

data class MapInfo(
    @SerializedName(ModelConstants.MAP_FORMATTED_ADDRESS)
    val formattedAddress: String,
    @SerializedName(ModelConstants.MAP_NAME)
    val name: String,
    @SerializedName(ModelConstants.MAP_WEBSITE )
    val website: String
)