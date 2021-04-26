package com.example.tutorly

import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList


class FilterActivity : AppCompatActivity() {

    lateinit var subjectAdapter : RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        val availableSubjects: ArrayList<Subject> = ArrayList()
        /*
        TODO:
          * retrieve subjects from database!!!
          * add possibility to get a list of selected subjects
          * add possibility to get radio button status
         */
        availableSubjects.add(Subject("German", "German language"))
        availableSubjects.add(Subject("Physics", "Learn physics"))
        availableSubjects.add(Subject("English", "Learn english"))
        availableSubjects.add(Subject("Maths", "Learn 2+2"))
        availableSubjects.add(Subject("ExampleSubject1", "Learn ExampleSubject1"))
        availableSubjects.add(Subject("ExampleSubject2", "Learn ExampleSubject2"))
        availableSubjects.add(Subject("ExampleSubject3", "Learn ExampleSubject3"))

        val filterRecyclerView = findViewById<RecyclerView>(R.id.filterRecyclerView)
        filterRecyclerView.setHasFixedSize(true)

        filterRecyclerView.layoutManager = LinearLayoutManager(this)
        filterRecyclerView.itemAnimator = DefaultItemAnimator()
        subjectAdapter = RecyclerViewAdapter(this, availableSubjects)
        filterRecyclerView.adapter = subjectAdapter
    }


    fun selectedOnClick(index: Int){
    }

    fun availableOnClick(index: Int){
    }
}



