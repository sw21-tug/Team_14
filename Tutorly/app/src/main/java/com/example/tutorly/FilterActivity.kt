package com.example.tutorly

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList


class FilterActivity : AppCompatActivity() {

    lateinit var selectedSubjectsAdapter : RecyclerViewAdapter
    lateinit var availableSubjectsAdapter : RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        val animalNames: ArrayList<String> = ArrayList()
        animalNames.add("Horse")
        animalNames.add("Cow")

        val animalNames2: ArrayList<String> = ArrayList()
        animalNames2.add("Horse")
        animalNames2.add("Cow")

        val selectedSubjects = findViewById<RecyclerView>(R.id.selectedSubjectsView)
        val availableSubjects = findViewById<RecyclerView>(R.id.availableSubjectsView)

        selectedSubjectsAdapter  = RecyclerViewAdapter(animalNames) { item -> selectedOnClick(item) }
        availableSubjectsAdapter  = RecyclerViewAdapter(animalNames2) { item -> availableOnClick(item) }

        selectedSubjects.adapter = selectedSubjectsAdapter
        selectedSubjects.layoutManager = LinearLayoutManager(this)

        availableSubjects.adapter = availableSubjectsAdapter
        availableSubjects.layoutManager = LinearLayoutManager(this)





    }


    fun selectedOnClick(index: Int){
        val subject = selectedSubjectsAdapter.deleteItem(index)
        availableSubjectsAdapter.insertItem(subject)
    }

    fun availableOnClick(index: Int){
        val subject = availableSubjectsAdapter.deleteItem(index)
        selectedSubjectsAdapter.insertItem(subject)
    }
}



