package com.example.tutorly

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    lateinit var tutorsAdapter : TutorsRecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val filteredTutors: ArrayList<Tutor> = ArrayList()

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

        val button: Button = findViewById(R.id.btnTownSelection)
        val changeLang: Button = findViewById(R.id.btn_change_lang_main_activity)


        button.setOnClickListener {
            val intent = Intent(this, TownSelection::class.java)
            startActivity(intent);

        }


        changeLang.setOnClickListener {
            val list = arrayOf("English", "Russian")
            val builder = AlertDialog.Builder(this@MainActivity)
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



    fun switchToFilter(view: View) {
        val switchIntent = Intent(this, FilterActivity::class.java).apply() {
        }
        startActivity(switchIntent)
    }

    override fun onBackPressed() {

    }



}