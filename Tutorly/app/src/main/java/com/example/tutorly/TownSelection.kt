package com.example.tutorly

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import javax.net.ssl.SSLEngineResult

class TownSelection : AppCompatActivity() {

    private val mTopToolbar: Toolbar? = null
    lateinit var mFusedLocationClient: FusedLocationProviderClient

    lateinit var placesClient:PlacesClient
    private var placeFields = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS)

    //lateinit var placesClient:PlacesClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_town_selection)
        setSupportActionBar(findViewById(R.id.titleToolbar))

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)


        findViewById<Button>(R.id.btnGetlocation).setOnClickListener {
            getLocation()
        }

        placesAutocomplete()
    }

    private fun getLocation() {

        val task = mFusedLocationClient.lastLocation

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 101
            )
            return
        }

        task.addOnSuccessListener {
            if (it != null) {
                Toast.makeText(applicationContext, "${it.longitude} ${it.latitude}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun placesAutocomplete(){
        val autocompleteFragment = supportFragmentManager.findFragmentById(R.id.autocomplete_fragment) as AutocompleteSupportFragment

        Places.initialize(this, getString(R.string.API))
        placesClient = Places.createClient(this)

        autocompleteFragment.setPlaceFields(placeFields)

        autocompleteFragment.setHint("f.e Graz")

        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(p0: Place) {
                //Log.i(TAG, "Place: ${place.name}, ${place.id}")
                Toast.makeText(this@TownSelection, ""+p0.address, Toast.LENGTH_SHORT).show()
                val text = findViewById<TextView>(R.id.textView)
                text.setText(p0.address)
            }

            override fun onError(p0: Status) {
                Toast.makeText(this@TownSelection, ""+p0.statusMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }




}