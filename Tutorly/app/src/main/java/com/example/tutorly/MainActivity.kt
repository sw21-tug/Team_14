package com.example.tutorly

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.tutorly.database.LvlOfKnowledge
import kotlin.system.exitProcess

lateinit var selectedLevelOfKnowledge : String
lateinit var selectedSubjects : ArrayList<String>

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data != null) {
            selectedSubjects = data.getStringArrayListExtra("filterSub") as ArrayList<String>
            selectedLevelOfKnowledge = data.getStringExtra("filterLok").toString()
            println(selectedSubjects)
            println(selectedLevelOfKnowledge)
        }
    }

    fun switchToFilter(view: View) {
        val switchIntent = Intent(this, FilterActivity::class.java).apply() {
        }
        startActivityForResult(switchIntent, 1)
    }

    override fun onBackPressed() {

    }



}