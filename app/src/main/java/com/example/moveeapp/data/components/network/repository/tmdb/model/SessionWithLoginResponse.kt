package com.example.moveeapp.data.components.network.repository.tmdb.model


import com.example.moveeapp.data.components.network.base.ModelConstants
import com.google.gson.annotations.SerializedName

data class SessionWithLoginResponse(
    @SerializedName(ModelConstants.EXPIRES_AT)
    val expiresAt: String,
    @SerializedName(ModelConstants.REQUEST_TOKEN)
    val requestToken: String,
    @SerializedName(ModelConstants.SUCCESS)
    val success: Boolean
)