package com.example.tutorly

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tutorly.database.DatabaseHolder
import com.example.tutorly.database.LvlOfKnowledge
import java.util.*
import kotlin.collections.ArrayList


class FilterActivity : AppCompatActivity() {

    //var selected_level_knowledge: LvlOfKnowledge = LvlOfKnowledge.University
    var selected_level_knowledge : String = "University"
    lateinit var subjectAdapter : RecyclerViewAdapter
    lateinit var filterData : FilterData;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        val availableSubjects: ArrayList<Subject> = ArrayList()
        val changeLang: Button = findViewById(R.id.btn_change_lang_filter)

        val filterRecyclerView = findViewById<RecyclerView>(R.id.filterRecyclerView)
        filterRecyclerView.setHasFixedSize(true)

        filterRecyclerView.layoutManager = LinearLayoutManager(this)
        filterRecyclerView.itemAnimator = DefaultItemAnimator()
        subjectAdapter = RecyclerViewAdapter(this, availableSubjects)
        val database = DatabaseHolder.database
        database.getSubjectsList(subjectAdapter::updateSubjects)
        filterRecyclerView.adapter = subjectAdapter

        changeLang.setOnClickListener{
            val list = arrayOf("English", "Russian")
            val builder = AlertDialog.Builder(this@FilterActivity)
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


        var radioGroup = findViewById<RadioGroup>(R.id.filterDifficultyRadioGroup)
        var defaultRadioButton = radioGroup.findViewById<RadioButton>(R.id.rbUniversity)
        defaultRadioButton.isChecked = true

        var btnDone = findViewById<Button>(R.id.btnDone)

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            var selectedId = findViewById<RadioButton>(checkedId)
            selected_level_knowledge = selectedId.text as String
        }

        btnDone.setOnClickListener{
            startMainActivity()
        }
    }


    fun startMainActivity () {
        //TODO: add list of selected things
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("filterLok", selected_level_knowledge)
        intent.putExtra("filterSub", subjectAdapter.getSelectedSubjects())
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun onBackPressed() {
        startMainActivity()
    }


    fun selectedOnClick(index: Int){
    }

    fun availableOnClick(index: Int){
    }
}



