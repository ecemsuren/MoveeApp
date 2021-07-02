package com.example.moveeapp.data.components.network.repository.tmdb.model


import com.example.moveeapp.data.components.network.base.ModelConstants
import com.example.moveeapp.data.model.tmdb.Cast
import com.google.gson.annotations.SerializedName

data class TvCreditResponse(
    @SerializedName(ModelConstants.CAST)
    val cast: List<Cast>,
    @SerializedName(ModelConstants.CREW)
    val crew: List<Crew>,
    @SerializedName(ModelConstants.CREDIT_ID)
    val id: Int
)