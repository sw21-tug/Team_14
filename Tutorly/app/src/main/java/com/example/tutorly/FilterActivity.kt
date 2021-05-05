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
        availableSubjects.add(Subject(resources.getString(R.string.german), resources.getString(R.string.german_lang)))
        availableSubjects.add(Subject(resources.getString(R.string.pyhsics), resources.getString(R.string.lear_physics)))
        availableSubjects.add(Subject(resources.getString(R.string.english), resources.getString(R.string.learn_english)))
        availableSubjects.add(Subject(resources.getString(R.string.maths), resources.getString(R.string.learn_maths)))
        availableSubjects.add(Subject(resources.getString(R.string.example_subject), resources.getString(R.string.learn_example_subject)))
        availableSubjects.add(Subject(resources.getString(R.string.example_subject), resources.getString(R.string.learn_example_subject)))
        availableSubjects.add(Subject(resources.getString(R.string.example_subject), resources.getString(R.string.learn_example_subject)))

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



