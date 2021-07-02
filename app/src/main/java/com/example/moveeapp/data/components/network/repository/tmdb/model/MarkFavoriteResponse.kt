package com.example.moveeapp.data.components.network.repository.tmdb.model


import com.example.moveeapp.data.components.network.base.ModelConstants
import com.google.gson.annotations.SerializedName


data class MarkFavoriteResponse(
    @SerializedName(ModelConstants.SUCCESS)
    val success: Boolean,
    @SerializedName(ModelConstants.STATUS_CODE)
    val statusCode: Int,
    @SerializedName(ModelConstants.STATUS_MESSAGE_STRING)
    val statusMessage: String
)