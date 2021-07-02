package com.example.moveeapp.data.components.network.repository.googlemap.base

import com.example.moveeapp.data.components.network.repository.googlemap.model.MapCinemaResponse
import com.example.moveeapp.data.components.network.repository.googlemap.model.MapInfoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Created by Ecem Suren on 28.10.2020.
 */
interface IGoogleMapApiService {

    @GET
    fun getMapCinema (
        @Url endPoint: String,
        @Query (GoogleMapApiConstants.LOCATION) location: String,
        @Query (GoogleMapApiConstants.RADIUS) radius: String,
        @Query (GoogleMapApiConstants.TYPE) type: String,
        @Query(GoogleMapApiConstants.API_KEY_PARAMETER) apiKey: String
    ): Call<MapCinemaResponse>


    @GET
    fun getMapInfo (
        @Url endPoint: String,
        @Query (GoogleMapApiConstants.PLACE_ID) place_id: String,
        @Query (GoogleMapApiConstants.FIELDS) fields: String,
        @Query(GoogleMapApiConstants.API_KEY_PARAMETER) apiKey: String
    ): Call<MapInfoResponse>
}