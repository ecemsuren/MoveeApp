package com.example.moveeapp.data.components.network.repository.tmdb.model


import com.example.moveeapp.data.components.network.base.ModelConstants
import com.google.gson.annotations.SerializedName

data class CreateSessionResponse(
    @SerializedName(ModelConstants.LOGIN_SESSION_ID)
    val sessionId: String,
    @SerializedName(ModelConstants.SUCCESS)
    val success: Boolean
)