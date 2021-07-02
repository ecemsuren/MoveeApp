package com.example.moveeapp.ui.modules.map.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.moveeapp.R
import com.example.moveeapp.application.MoveeApplication
import com.example.moveeapp.data.components.network.repository.googlemap.api.GoogleMapApi
import com.example.moveeapp.data.components.network.repository.googlemap.model.MapCinemaResponse
import com.example.moveeapp.data.components.network.repository.googlemap.model.MapInfoResponse
import com.example.moveeapp.ui.modules.map.base.MapBaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Ecem Suren on 28.10.2020.
 */
class MapViewModel: MapBaseViewModel() {

    val cinemaListResponse : MutableLiveData<MapCinemaResponse> = MutableLiveData()
    val errorMessage : MutableLiveData<String> = MutableLiveData()
    val mapInfoListResponse : MutableLiveData<MapInfoResponse> = MutableLiveData()


    private var mapCinemaCallBack = object : Callback<MapCinemaResponse> {
        override fun onResponse(call: Call<MapCinemaResponse>, response: Response<MapCinemaResponse>) {
            if (response.isSuccessful) {
                cinemaListResponse.value = response.body()
            } else{
                errorMessage.value = MoveeApplication.appContext.getString(R.string.failed)
            }
        }

        override fun onFailure(call: Call<MapCinemaResponse>, t: Throwable) {
            errorMessage.value = MoveeApplication.appContext.getString(R.string.failed)
        }
    }

    fun getCinemaList(location: String, radius: String, type: String){
        GoogleMapApi.getMapApi(location, radius, type, mapCinemaCallBack)
    }

    private var mapInfoCallBack = object : Callback<MapInfoResponse> {
        override fun onResponse(call: Call<MapInfoResponse>, response: Response<MapInfoResponse>) {
            if (response.isSuccessful) {
                mapInfoListResponse.value = response.body()
            } else{
                errorMessage.value = MoveeApplication.appContext.getString(R.string.failed)
            }
        }

        override fun onFailure(call: Call<MapInfoResponse>, t: Throwable) {
            errorMessage.value = MoveeApplication.appContext.getString(R.string.failed)
        }
    }

    fun getMapInfo (place_id: String, fields: String){
        GoogleMapApi.getMapInfo(place_id,fields, mapInfoCallBack)

    }
}