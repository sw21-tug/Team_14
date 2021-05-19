package com.example.tutorly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.app.AlertDialog
import android.content.Intent
import android.media.Image
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList


class TutorListActivity : AppCompatActivity() {

    lateinit var tutorsAdapter : TutorsRecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutor_list)

        val filteredTutors: ArrayList<Tutor> = ArrayList()
        val changeLang: Button = findViewById(R.id.btn_change_lang_tutors)

        //TODO retrieve Tutors from database

        filteredTutors.add(Tutor("Max Mustermann","testID"))
        filteredTutors.add(Tutor("Max Mustermann","testID1"))
        filteredTutors.add(Tutor("Max Mustermann","testID2"))
        filteredTutors.add(Tutor("Max Mustermann","testID3"))
        filteredTutors.add(Tutor("Max Mustermann","testID4"))
        filteredTutors.add(Tutor("Max Mustermann","testID5"))


        val tutorsRecyclerView = findViewById<RecyclerView>(R.id.tutorsRecyclerView)
        tutorsRecyclerView.setHasFixedSize(true)

        tutorsRecyclerView.layoutManager = LinearLayoutManager(this)
        tutorsRecyclerView.itemAnimator = DefaultItemAnimator()
        tutorsAdapter = TutorsRecyclerView(this, filteredTutors)
        tutorsRecyclerView.adapter = tutorsAdapter

        changeLang.setOnClickListener{
            val list = arrayOf("English", "Russian")
            val builder = AlertDialog.Builder(this@TutorListActivity)
            builder.setTitle(R.string.choose_lang)

            builder.setSingleChoiceItems(list, -1) { dialog, which ->
                if (which == 0) {
                    Translation().changeLang("default", this)
                    recreate()
                }
                else if (which == 1) {
                    Translation().changeLang("kv", this)
                    recreate()
                }
                dialog.dismiss()
            }
            builder.show()
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    /*
first create a button in mainActivity to get to the TutorsListActivity
second create the TutorListActivity
third create recyclerview to show the Tutorslist in TutorslistActivity
https://developer.android.com/guide/topics/ui/layout/recyclerview
 */

}