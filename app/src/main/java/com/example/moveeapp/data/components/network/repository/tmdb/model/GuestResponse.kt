package com.example.moveeapp.data.components.network.repository.tmdb.model


import com.example.moveeapp.data.components.network.base.ModelConstants
import com.google.gson.annotations.SerializedName

data class GuestResponse(
    @SerializedName(ModelConstants.EXPIRES_AT)
    val expiresAt: String,
    @SerializedName(ModelConstants.LOGIN_GUEST_SESSION_ID)
    val guestSessionId: String,
    @SerializedName(ModelConstants.SUCCESS)
    val success: Boolean
)