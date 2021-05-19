package com.example.tutorly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class TutorProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutor_profile)

        //  Create Subject List
        val arrayAdapter: ArrayAdapter<*>
        val subjects = arrayOf("Programming[Kotlin, Java]", "Android Studio", "LGBTQ+ Kunde", "Math",
            "English", "German", "ASD", "OAD", "Geo", "Geschichte", "Biologie")

        // access the listView from xml file
        var subjectListView = findViewById<ListView>(R.id.subjectslist)
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, subjects)
        subjectListView.adapter = arrayAdapter



    }
}