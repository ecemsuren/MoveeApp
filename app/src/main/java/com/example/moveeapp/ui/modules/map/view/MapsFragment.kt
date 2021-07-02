package com.example.moveeapp.ui.modules.map.view

import android.Manifest
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.moveeapp.R
import com.example.moveeapp.application.MoveeApplication
import com.example.moveeapp.data.model.googlemap.MapInfo
import com.example.moveeapp.data.model.tmdb.Cinema
import com.example.moveeapp.ui.base.UIConstants
import com.example.moveeapp.ui.base.ViewModelFactory
import com.example.moveeapp.ui.components.movierecyclerview.MapInfoAdapter
import com.example.moveeapp.ui.modules.map.base.MapBaseFragment
import com.example.moveeapp.ui.modules.map.viewmodel.MapViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_maps.*
import java.util.*


class MapsFragment : MapBaseFragment(), OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private lateinit var googleMap: GoogleMap
    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener
    var myMarker = MarkerOptions()
    private var locationlist: ArrayList<Cinema> = arrayListOf()
    private lateinit var mark: Marker
    private lateinit var userLocation: LatLng

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_maps, container, false)

        mapViewModel = ViewModelProvider(this, ViewModelFactory()).get(MapViewModel::class.java)

        registerLiveData()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapView.onCreate(savedInstanceState)
        mapView.onResume()
        mapView.getMapAsync(this)

    }


    private fun registerLiveData() {

        mapViewModel.cinemaListResponse.observe(viewLifecycleOwner, Observer {
            for (place in it.results) {
                myMarker.position(LatLng(place.geometry.location.lat, place.geometry.location.lng))
                    .title(place.name)
                mark = googleMap.addMarker(myMarker)
                locationlist.add(place)
            }

        })
        mapViewModel.mapInfoListResponse.observe(viewLifecycleOwner, Observer { mapInfoResponse ->
            mapInfoResponse?.let { mapInfo ->
                showInfoView(mapInfo.result)
            }
        })

    }

    override fun onInfoWindowClick(mark: Marker?) {
        locationlist.forEach { place ->
            if (place.name == mark?.title) {
                mapViewModel.getMapInfo(place.placeId, UIConstants.MAP_FIELDS)

            }
        }
    }

    override fun onMapReady(map: GoogleMap?) {
        map?.let {
            googleMap = it
            googleMap.setInfoWindowAdapter(MapInfoAdapter(layoutInflater))
            googleMap.setOnInfoWindowClickListener(this)
        }

        locationManager =
            (requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager)

        locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location?) {

                if (location != null) {
                    userLocation = LatLng(location.latitude, location.longitude)
                    mapViewModel.getCinemaList(
                        userLocation.latitude.toString()
                                + UIConstants.MAP_COMMA
                                + userLocation.longitude.toString(),
                        UIConstants.RADIUS,
                        UIConstants.THEATER
                    )
                    googleMap.moveCamera(
                        CameraUpdateFactory.newLatLngZoom(
                            userLocation,
                            UIConstants.LONG_15
                        )
                    )
                }
            }

            override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {}

            override fun onProviderEnabled(p0: String?) {}

            override fun onProviderDisabled(p0: String?) {}


        }

        if (ContextCompat.checkSelfPermission(
                MoveeApplication.appContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), UIConstants.ONE)
        } else {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                UIConstants.LONG_TWO,
                UIConstants.FLOAT_TWO,
                locationListener
            )
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        if (requestCode == UIConstants.ONE) {
            if (grantResults.size > UIConstants.ZERO) {

                if (ContextCompat.checkSelfPermission(
                        MoveeApplication.appContext,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        UIConstants.LONG_TWO,
                        UIConstants.FLOAT_TWO,
                        locationListener
                    )
                }
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun showInfoView(mapInfo: MapInfo) {
        val dialog = Dialog(this.requireContext())
        val layoutParam = dialog.window!!.attributes
        layoutParam.gravity = Gravity.TOP

        dialog.setContentView(R.layout.custom_map_item)

        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        val title = dialog.findViewById<TextView>(R.id.location_txt_name)
        title.text = mapInfo.name
        val address = dialog.findViewById<TextView>(R.id.location_txt_adress)
        address.text = mapInfo.formattedAddress
        val website = dialog.findViewById<TextView>(R.id.location_txt_website)
        website.text = mapInfo.website
        val goButton = dialog.findViewById<Button>(R.id.location_button_go)

        website.setOnClickListener {
            navigateToWebsite(mapInfo)

        }
        goButton.setOnClickListener {
            navigateToGoogleMap(mapInfo)

        }
        dialog.show()
    }

    private fun navigateToWebsite(mapInfo: MapInfo) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(mapInfo.website)))
    }

    private fun navigateToGoogleMap(mapInfo: MapInfo) {
        locationlist.forEach { place ->
            if (place.name == mapInfo.name) {
                val uri =
                    UIConstants.MAP_URI + userLocation.latitude.toString() + UIConstants.COMMA + userLocation.longitude.toString() + UIConstants.MAP_ADDRESS + place.geometry.location.lat.toString() + UIConstants.COMMA + place.geometry.location.lng.toString()
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                intent.setPackage(UIConstants.MAP_PACKAGE_NAME)

                if (intent.resolveActivity(requireContext().packageManager) != null) {
                    requireContext().startActivity(intent)
                }

            }

        }
    }

}






