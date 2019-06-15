package com.example.fetchinguserscurrentlocation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.heatmaps.WeightedLatLng
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import com.google.maps.android.SphericalUtil



class MainActivity : AppCompatActivity() , LocationListener{

var loc =LatLng(0.00,0.00)//currentlocation
    val loc2 =LatLng(28.592544,77.044104)//dashratpuri metro statio
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    val mylocationmanager =getSystemService(Context.LOCATION_SERVICE) as LocationManager

//        btndis.setOnClickListener(object :View.OnClickListener{     code
//            override fun onClick(v: View?) {                         for
//                val dis =distanceBetween(loc,loc2)                    finding distance btw two lat long
//                Log.e("tag",dis.toString())
//            }
//        })

        btnlocate.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                val intent =Intent(baseContext,MapsActivity::class.java)
                intent.putExtra("lat",loc.latitude)
                intent.putExtra("long",loc.longitude)

                startActivity(intent);            }
        })

    BtnFetch.setOnClickListener(object : View.OnClickListener{
        @SuppressLint("MissingPermission")
        override fun onClick(v: View?) {
            if (!mylocationmanager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                //Show an alert dialog here
                AlertDialog.Builder(this@MainActivity)
                    .setTitle("Location Disabled")
                    .setMessage("Please enable the location for the app to work properly!")
                    .setPositiveButton("Ok") { _, _ ->
                        startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                        //Start an intent to the settings page for location
                    }
                    .setNegativeButton("Cancel") { _, _ ->
                        finish()
                    }
                    .show()

            } else {
                mylocationmanager.
                    requestSingleUpdate(LocationManager.GPS_PROVIDER,this@MainActivity,null)

            }

        }

    })

    }


    override fun onLocationChanged(location: Location?) {



        location?.let {
            Toast.makeText(baseContext,""+it.latitude.toString(),Toast.LENGTH_LONG).show()
            Toast.makeText(baseContext,""+it.longitude.toString(),Toast.LENGTH_LONG).show()
            Log.e("tag",it.latitude.toString())
             loc =LatLng(it.latitude,it.longitude)
    //        loc1= loc
            Log.e("Tag",it.longitude.toString())

        }
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
    }

    override fun onProviderEnabled(provider: String?) {
    }

    override fun onProviderDisabled(provider: String?) {
    }

    fun distanceBetween(point1: LatLng?, point2: LatLng?): Double? {
        return if (point1 == null || point2 == null) {
            null
        } else SphericalUtil.computeDistanceBetween(point1, point2)

    }
}
