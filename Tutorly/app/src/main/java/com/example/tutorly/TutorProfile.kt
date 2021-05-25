package com.example.tutorly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView

class TutorProfile : AppCompatActivity() {

    companion object{
        val tutorName : String = "Ivan"
        val tutorAge : String = "20"
        val tutorGender : String = "Male"
        val tutorMail : String = "ivan@tugraz.at"

        val subjects = arrayOf("Programming[Kotlin, Java]", "Android Studio", "LGBTQ+ Kunde", "Math",
            "English", "German", "ASD", "OAD", "Geo", "Geschichte", "Biologie")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutor_profile)

        // Show tutor name
        val tutorNameView = findViewById<View>(R.id.txtTutorName) as TextView
        tutorNameView.text = "Name: $tutorName"

        // Show tutor age
        val tutorAgeView = findViewById<View>(R.id.txtTutorAge) as TextView
        tutorAgeView.text = "Age: $tutorAge"

        // Show tutor gender
        val tutorGenderView = findViewById<View>(R.id.txtTutorGender) as TextView
        tutorGenderView.text = "Gender: $tutorGender"

        // Show tutor gender
        val tutorMailView = findViewById<View>(R.id.txtTutorMail) as TextView
        tutorMailView.text = "E-Mail: $tutorMail"

        // create Array adapter
        val arrayAdapter: ArrayAdapter<*>

        // access the listView from xml file
        var subjectListView = findViewById<ListView>(R.id.subjectslist)
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, subjects)
        subjectListView.adapter = arrayAdapter
    }



}