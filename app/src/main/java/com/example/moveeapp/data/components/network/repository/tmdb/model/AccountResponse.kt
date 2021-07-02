package com.example.moveeapp.data.components.network.repository.tmdb.model


import com.example.moveeapp.data.components.network.base.ModelConstants
import com.google.gson.annotations.SerializedName

data class AccountResponse(
    @SerializedName(ModelConstants.ACCOUNT_ID)
    val id: Int,
    @SerializedName(ModelConstants.ACCOUNT_NAME)
    val name: String,
    @SerializedName(ModelConstants.ACCOUNT_USERNAME)
    val username: String
)