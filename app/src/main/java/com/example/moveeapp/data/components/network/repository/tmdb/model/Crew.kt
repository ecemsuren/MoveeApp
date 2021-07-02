package com.example.moveeapp.data.components.network.repository.tmdb.model


import com.example.moveeapp.data.components.network.base.ModelConstants
import com.google.gson.annotations.SerializedName

data class Crew(
    @SerializedName(ModelConstants.CREW_CREDIT_ID)
    val creditId: String,
    @SerializedName(ModelConstants.CREW_DEPARTMENT)
    val department: String,
    @SerializedName(ModelConstants.CREW_GENDER)
    val gender: Int,
    @SerializedName(ModelConstants.CREW_ID)
    val id: Int,
    @SerializedName(ModelConstants.CREW_JOB)
    val job: String,
    @SerializedName(ModelConstants.CREW_NAME)
    val name: String,
    @SerializedName(ModelConstants.CREW_PORFILE_PATH)
    val profilePath: Any
)