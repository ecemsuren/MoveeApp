package com.example.moveeapp.data.components.network.repository.googlemap.base

/**
 * Created by Ecem Suren on 28.10.2020.
 */

enum class GoogleApiEndpoint (val endpoint: String){
    MAP("maps/api/place/nearbysearch/json"),
    MAP_INFO("maps/api/place/details/json")
}