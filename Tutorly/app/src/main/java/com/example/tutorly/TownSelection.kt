package com.example.tutorly

import android.os.Bundle
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity

class TownSelection : AppCompatActivity() {

    private val mTopToolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_town_selection)
        setSupportActionBar(findViewById(R.id.titleToolbar))


    }
}