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
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() , LocationListener{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    val mylocationmanager =getSystemService(Context.LOCATION_SERVICE) as LocationManager

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
                mylocationmanager
            }}
    })

    }
    override fun onLocationChanged(location: Location?) {



        location?.let {
            Toast.makeText(baseContext,""+it.latitude.toString(),Toast.LENGTH_LONG).show()
            Toast.makeText(baseContext,""+it.longitude.toString(),Toast.LENGTH_LONG).show()
            Log.e("tag",it.latitude.toString())
            Log.e("Tag",it.longitude.toString())

        }
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
    }

    override fun onProviderEnabled(provider: String?) {
    }

    override fun onProviderDisabled(provider: String?) {
    }


}
