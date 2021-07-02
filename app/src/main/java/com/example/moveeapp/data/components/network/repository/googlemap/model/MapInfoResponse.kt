package com.example.moveeapp.data.components.network.repository.googlemap.model


import com.example.moveeapp.data.components.network.base.ModelConstants
import com.example.moveeapp.data.model.googlemap.MapInfo
import com.google.gson.annotations.SerializedName

data class MapInfoResponse(
    @SerializedName(ModelConstants.MAP_INFO_RESULT)
    val result: MapInfo
)
