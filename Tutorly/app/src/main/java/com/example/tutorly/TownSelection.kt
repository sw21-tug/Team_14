package com.example.tutorly

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.tutorly.database.Database
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import java.util.*
import javax.net.ssl.SSLEngineResult
import kotlin.system.exitProcess


class TownSelection : AppCompatActivity() {



    lateinit var placesClient:PlacesClient
    private var placeFields = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_town_selection)
        setSupportActionBar(findViewById(R.id.titleToolbar))

        var changeLang: Button = findViewById(R.id.btn_change_lang_town_selection)

        placesAutocomplete()

        changeLang.setOnClickListener{
            val list = arrayOf("English", "Russian")
            val builder = AlertDialog.Builder(this@TownSelection)
            builder.setTitle(R.string.choose_lang)

            builder.setSingleChoiceItems(list, -1) { dialog, which ->
                if (which == 0) {
                    Translation.changeLang("default", this)
                    recreate()
                }
                else if (which == 1) {
                    Translation.changeLang("kv", this)
                    recreate()
                }
                dialog.dismiss()
            }
            builder.show()
        }

    }


    private fun placesAutocomplete(){
        val autocompleteFragment = supportFragmentManager.findFragmentById(R.id.autocomplete_fragment) as AutocompleteSupportFragment

        Places.initialize(this, getString(R.string.API))
        placesClient = Places.createClient(this)

        autocompleteFragment.setPlaceFields(placeFields)

        autocompleteFragment.setHint(resources.getString(R.string.city_hint))

        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(p0: Place) {
                Toast.makeText(this@TownSelection, "You have selected "+p0.address, Toast.LENGTH_SHORT).show()
                val text = findViewById<TextView>(R.id.textView)
                text.setText(p0.address)
            }

            override fun onError(p0: Status) {
                Toast.makeText(this@TownSelection, ""+p0.statusMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}

