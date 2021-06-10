package com.example.tutorly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.example.tutorly.database.Tutor
import java.io.Serializable

class TutorProfile : AppCompatActivity() {

    companion object{
        var tutorName : String = "Ivan"
        var tutorSurnamne : String = "Mic"
        var tutorPhone: String = "01234567"
        var tutorMail : String = "ivan@tugraz.at"

        val subjects = arrayOf("Programming[Kotlin, Java]", "Android Studio", "LGBTQ+ Kunde", "Math",
            "English", "German", "ASD", "OAD", "Geo", "Geschichte", "Biologie")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutor_profile)

        val tutorInfo = intent.getSerializableExtra("Tutor") as Tutor
        if(tutorInfo != null)
        {
            tutorName = tutorInfo.firstName
            tutorSurnamne = tutorInfo.lastName
            tutorMail = tutorInfo.email
            tutorPhone = tutorInfo.phoneNumber
        }



        // Show tutor name
        val tutorNameView = findViewById<View>(R.id.txtTutorName) as TextView
        tutorNameView.text = getString(R.string.tutorName) + " " + tutorName

        // Show tutor age
        val tutorAgeView = findViewById<View>(R.id.txtTutorAge) as TextView
        tutorAgeView.text = getString(R.string.tutorSurname) + " " + tutorSurnamne

        // Show tutor gender
        val tutorGenderView = findViewById<View>(R.id.txtTutorGender) as TextView
        tutorGenderView.text = getString(R.string.tutorPhone) + " " + tutorPhone

        // Show tutor gender
        val tutorMailView = findViewById<View>(R.id.txtTutorMail) as TextView
        tutorMailView.text = getString(R.string.tutorEmail) + " " + tutorMail

        // create Array adapter
        val arrayAdapter: ArrayAdapter<*>

        // access the listView from xml file
        var subjectListView = findViewById<ListView>(R.id.subjectslist)
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, ArrayList(tutorInfo.subjectIDs.keys))
        subjectListView.adapter = arrayAdapter
    }



}