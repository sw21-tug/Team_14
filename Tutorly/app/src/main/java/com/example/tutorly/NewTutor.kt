package com.example.tutorly

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tutorly.database.*

class NewTutor : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var subjectAdapterTutor : RecyclerViewAdapter


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_new_tutor)
        setSupportActionBar(findViewById(R.id.titleToolbar))
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
            println("Hereee: " + UserAutheniticator().getCurrentUser()?.displayName)
            val tutorSubjects = HashMap<String, LvlOfKnowledge>()
            val selectedSubjects = subjectAdapterTutor.getSelectedSubjects()
            for(subject in selectedSubjects)
                tutorSubjects[subject] = LvlOfKnowledge.University
            DatabaseHolder.database.addTutor(Tutor("userID10", "TestUser", "TestSurname", "test@gmail.com", tutorSubjects, phoneNumberView.text.toString()))
            intent.putExtra("activity", "newTutor")
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}