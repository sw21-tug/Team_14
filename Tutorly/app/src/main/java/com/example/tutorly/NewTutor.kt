package com.example.tutorly

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tutorly.database.*
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener

class NewTutor : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var subjectAdapterTutor : RecyclerViewAdapter
    var city = ""
    lateinit var placesClient: PlacesClient
    private var placeFields = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS)

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_new_tutor)
        setTitle(R.string.title_activity_new_tutor)

        val availableSubjects: ArrayList<Subject> = ArrayList()

        val newTutorRecyclerView = findViewById<RecyclerView>(R.id.newTutorRecyclerView)
        val phoneNumberView = findViewById<EditText>(R.id.phoneNum)
        newTutorRecyclerView.setHasFixedSize(true)

        newTutorRecyclerView.layoutManager = LinearLayoutManager(this)
        newTutorRecyclerView.itemAnimator = DefaultItemAnimator()


        subjectAdapterTutor = RecyclerViewAdapter(this, availableSubjects)

        val database = DatabaseHolder.database
        database.getSubjectsList(subjectAdapterTutor::updateSubjects)
        newTutorRecyclerView.adapter = subjectAdapterTutor

        placesAutocomplete()

        val changeLang: Button = findViewById(R.id.btn_change_lang_new_tutor)
        changeLang.setOnClickListener {
            val list = arrayOf("English", "Russian")
            val builder = AlertDialog.Builder(this@NewTutor)
            builder.setTitle(R.string.choose_lang)

            builder.setSingleChoiceItems(list, -1) { dialog, which ->
                if (which == 0) {
                    Translation.changeLang("default", this)
                    recreate()
                } else if (which == 1) {
                    Translation.changeLang("kv", this)
                    recreate()
                }
                dialog.dismiss()
            }
            builder.show()
        }

        val btnApply : Button = findViewById(R.id.btnApply)
        btnApply.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            val tutorSubjects = HashMap<String, LvlOfKnowledge>()
            val selectedSubjects = subjectAdapterTutor.getSelectedSubjects()
            for(subject in selectedSubjects)
                tutorSubjects[subject] = LvlOfKnowledge.University
            DatabaseHolder.database.addTutor(Tutor("userID10", "TestUser", "TestSurname", "test@gmail.com", tutorSubjects, phoneNumberView.text.toString(), city))
            intent.putExtra("activity", "newTutor")
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    private fun placesAutocomplete(){
        val autocompleteFragment = supportFragmentManager.findFragmentById(R.id.townAutocomplete_fragment) as AutocompleteSupportFragment

        Places.initialize(this, getString(R.string.API))
        placesClient = Places.createClient(this)

        autocompleteFragment.setPlaceFields(placeFields)

        autocompleteFragment.setHint(resources.getString(R.string.city_hint))

        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(p0: Place) {
                city = p0.address.toString()
            }

            override fun onError(p0: Status) {
            }

        })

    }

}