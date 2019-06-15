package com.example.fetchinguserscurrentlocation


import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class MapsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_for_map)

        val intent =intent
        val lat =intent.extras.getDouble("lat")
        val long =intent.extras.getDouble("long")

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync{
            it.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style))
            val markerOptionsDelhi = MarkerOptions()
                .position(LatLng(lat, long))
                .draggable(true)
                .title("Current")

            val cameraUpdate = CameraUpdateFactory
                .newLatLngZoom(LatLng(lat, long), 15F)

            it.addMarker(markerOptionsDelhi)
            it.animateCamera(cameraUpdate)

        }
}}