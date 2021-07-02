package com.example.moveeapp.data.components.network.repository.googlemap.api

import com.example.moveeapp.BuildConfig
import com.example.moveeapp.application.MoveeApplication
import com.example.moveeapp.data.components.network.repository.googlemap.base.GoogleApiEndpoint
import com.example.moveeapp.data.components.network.repository.googlemap.base.IGoogleMapApiService
import com.example.moveeapp.data.components.network.repository.googlemap.model.MapCinemaResponse
import com.example.moveeapp.data.components.network.repository.googlemap.model.MapInfoResponse

import com.example.moveeapp.data.util.NetworkUtil
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Ecem Suren on 28.10.2020.
 */
object GoogleMapApi {

    var context = MoveeApplication.appContext
    private val api: IGoogleMapApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.GOOGLE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(IGoogleMapApiService::class.java)

    }

    fun getMapApi(location: String, radius: String, type: String, callBack: Callback<MapCinemaResponse>) {
        if (NetworkUtil.isInternetAvailable(context)) {
            api.getMapCinema(GoogleApiEndpoint.MAP.endpoint,location, radius, type, BuildConfig.GOOGLE_API_KEY)
                .enqueue(callBack)
        }
    }

    fun getMapInfo(place_id: String, fields: String, callBack: Callback<MapInfoResponse>) {
        if (NetworkUtil.isInternetAvailable(context)) {
            api.getMapInfo(GoogleApiEndpoint.MAP_INFO.endpoint, place_id, fields, BuildConfig.GOOGLE_API_KEY)
                .enqueue(callBack)
        }
    }


}

