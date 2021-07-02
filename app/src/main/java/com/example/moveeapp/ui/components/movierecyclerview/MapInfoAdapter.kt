package com.example.moveeapp.ui.components.movierecyclerview


import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.example.moveeapp.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker


/**
 * Created by Ecem Suren on 29.10.2020.
 */
class MapInfoAdapter(val inflater: LayoutInflater): GoogleMap.InfoWindowAdapter {

    private var myContentsView: View? = null

    override fun getInfoContents(marker: Marker): View? {
        return null
    }

    override fun getInfoWindow(marker: Marker?): View? {

        myContentsView = inflater.inflate(R.layout.custom_map_title_item, null)
        val nameTitle = myContentsView!!.findViewById<TextView>(R.id.map_title)
        nameTitle.text = marker?.title
        return myContentsView
    }
}